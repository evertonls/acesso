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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

/**
 *
 * @author everton
 */
@Repository
public class UsuarioDaoImpl implements UsuarioDao, Serializable {

    private static final long serialVersionUID = 1L;
    private final JdbcTemplate template;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ConversionService cs;
    private static final String QUERY = "SELECT usuario_nm, usuario_ap, u.cpf_num, birth_data, email, senha, recupera_cod, ativo, autoriza_cod"
            + "FROM usuarios u" + "LEFT JOIN permissoes p ON u.cpf_num = p.cpf_num";

    public UsuarioDaoImpl(DataSource ds, ConversionService cs) {
        this.template = new JdbcTemplate(ds);
        this.cs = cs;
    }

    @Override
    public void persistUser(Usuario usuario) {
        final String sql = "INSERT INTO usuarios (usuario_nm, usuario_ap, cpf_num, birth_data, email, senha, recupera_cod, ativo)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, usuario.getNome(), usuario.getApelido(), usuario.getCpf(), usuario.getDataNascimento(),
                usuario.getEmail(), usuario.getSenha(), usuario.getCodRecuperacao(), usuario.isAtivo());
    }

    @Override
    public Usuario getByCpf(String cpf) {
        final String sql = QUERY + "WHERE cpf_num = ?";

        List<Usuario> usuarios = template.query(sql, this::extractUsers, cpf);
        logger.info("Buscando usuário: " + cpf);
        
        return Optional.ofNullable(usuarios)
                .filter(u -> Optional.ofNullable(u.get(0)).isPresent())
                .map(u -> u.get(0)).get();
        
       
    }

    @Override
    public void gravaCodRecuperacao(String codigo, String email) {
        final String sql = "UPDATE usuarios SET recupera_cod = ? WHERE email = ?";
        template.update(sql, codigo, email);
    }

    @Override
    public String getCodRecuperacao(String cpf) {
        return template.queryForObject("SELECT recupera_cod FROM usuarios WHERE cpf_num = ?", String.class, cpf);
    }

    @Override
    public void updatePassword(String senha, String cpf) {
        template.update("UPDATE Usuarios SET senha = ? WHERE cpf_num = ?", BCrypt.hashpw(senha, BCrypt.gensalt()), cpf);
    }

    @Override
    public void updateCodRecuperacaoToNull(String cpf) {
        template.update("UPDATE usuarios SET recupera_cod = ? WHERE cpf_num = ?", null, cpf);
    }

    private List<Usuario> extractUsers(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Usuario> userMap = new HashMap<>();
        Usuario usuarioAtual;

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
                usuarioAtual.setAuthorities(new ArrayList<>());

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
