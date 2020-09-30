/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.daos;

import fortaleza.ce.gov.br.acesso.models.Autorizacao;
import fortaleza.ce.gov.br.acesso.models.Usuario;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author everton
 */
@Repository
public class UsuarioDaoImpl implements UsuarioDao, Serializable {

    private static final long serialVersionUID = 1L;
    private JdbcTemplate template;
    private final Logger logger;
    private ConversionService cs;
    private final static String QUERY = "SELECT usuario_nm, usuario_ap, u.cpf_num, birth_data, email, senha, recupera_cod, ativo, autoriza_cod"
            + "FROM usuarios u" + "LEFT JOIN permissoes p ON u.cpf_num = p.cpf_num";

    public UsuarioDaoImpl(DataSource ds, final Logger logger, ConversionService cs) {
        this.template = new JdbcTemplate(ds);
        this.logger = logger;
        this.cs = cs;
    }

    @Override
    public String persistUser(Usuario usuario) {
        final String sql = "INSERT INTO usuarios (usuario_nm, usuario_ap, cpf_num, birth_data, email, senha, recupera_cod, ativo)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.BOOLEAN);
        pscFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(
                Arrays.asList(usuario.getNome(), usuario.getApelido(), usuario.getCpf(),
                        usuario.getDataNascimento(),
                        usuario.getEmail(), usuario.getSenha(), usuario.isAtivo()));

        template.update(psc, keyHolder);

        Long novoId;
        if (keyHolder.getKeys().size() > 1) {
            novoId = (Long) keyHolder.getKeys().get("usuario_id");
        } else {
            novoId = keyHolder.getKey().longValue();
        }

        return novoId;

    }

    @Override
    public Usuario getByCpf(String cpf) {
        final String sql = QUERY + "WHERE cpf_num = ?";

        List<Usuario> usuarios = template.query(sql, this::extractUsers, cpf);
        logger.info("Buscando usuário: " + cpf);
        return usuarios.get(0);

    }

    @Override
    public void gravaCodRecuperacao(String codigo, String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCodRecuperacao(String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePassword(String senha, String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCodRecuperacaoToNull(String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Usuario> extractUsers(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Usuario> userMap = new HashMap<>();
        Usuario usuarioAtual = null;

        while (rs.next()) {

            String id = rs.getString("cpf_num");
            usuarioAtual = userMap.get(id);

            if (usuarioAtual == null) {
                usuarioAtual = new Usuario();
                usuarioAtual.setNome(rs.getString("usuario_nm"));
                usuarioAtual.setApelido(rs.getString("usuario_ap"));
                usuarioAtual.setCpf(rs.getString("cpf_num"));
                usuarioAtual.setDataNascimento(cs.convert(rs.getDate("birth_data"), LocalDate.class));
                usuarioAtual.setSenha(rs.getString("senha"));
                usuarioAtual.setEmail(rs.getString("email"));
                usuarioAtual.setAuthorities(new ArrayList<Autorizacao>());

                userMap.put(id, usuarioAtual);
            }

            String roleName = rs.getString("autoriza_cod");
            if (!roleName.isEmpty()) {
                Autorizacao role = new Autorizacao(roleName);
                usuarioAtual.getRoles().add(role);
            }

        }

        return new ArrayList<>(userMap.values());
    }
}
