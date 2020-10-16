/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.daos;

import fortaleza.ce.gov.br.acesso.models.Setor;
import fortaleza.ce.gov.br.acesso.models.Usuario;
import fortaleza.ce.gov.br.acesso.models.Visita;
import fortaleza.ce.gov.br.acesso.models.Visitante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author everton
 */
public class VisitaDaoImpl implements VisitaDao{
    private final JdbcTemplate template;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ConversionService cs;
    
    public VisitaDaoImpl(DataSource ds, ConversionService cs){
        this.template = new JdbcTemplate(ds);
        this.cs = cs;
    }
    
    
    @Override
    public void persist(Visita visita) {
        log.info("Granvando uma visita da base de dados!");
        final String sql = "INSERT INTO visita(visit_cpf, cpf_num, placa, dataent, datasai, setor_cod, agendada, autorizado_por) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, visita.getVisitante().getCpf(), visita.getUsuario().getCpf(), 
                visita.getPlaca(), visita.getDataEntrada(), visita.getDataSaida(),
                visita.getSetor().getCod(), visita.isAgendada(), visita.getAutoriadoPor());
    }

    @Override
    public List<Visita> getVisitaByCpf(String cpf) {
        log.info("Buscando visitas com base no cpf!");
        final String sql = "SELECT v.visit_cpf, visit_nome, visit_fone, cpf_num, placa, dataent, datasai, setor_cod, agendada, autorizado_por "
                + "FROM visita v LEFT JOIN visitantes v2 ON v.visit_cpf = v2.visit_cpf WHERE v.visit_cpf = ?";
        return template.query(sql, this::rowMapVisita, cpf);
    }

    @Override
    public List<Visita> getVisitaByPlaca(String placa) {
        log.info("Buscando visitas com base na placa!");
        final String sql = "SELECT v.visit_cpf, visit_nome, visit_fone, cpf_num, placa, dataent, datasai, setor_cod, agendada, autorizado_por "
                + "FROM visita v LEFT JOIN visitantes v2 ON v.visit_cpf = v2.visit_cpf WHERE v.placa = ?";
        return template.query(sql, this::rowMapVisita, placa);
    }
    
    private Visita rowMapVisita(ResultSet rs,int numRow) throws SQLException{
        Visita visita = new Visita(new Visitante (rs.getString("visit_cpf")), new Usuario(rs.getString("cpf_num")), 
                        rs.getString("placa"), cs.convert(rs.getTimestamp("dataent"), LocalDateTime.class), 
                        cs.convert(rs.getTimestamp("datasai"), LocalDateTime.class), new Setor(rs.getString("setor_cod")), 
                        rs.getString("autorizado_por"));
        visita.setAgendada(rs.getBoolean("agendado"));
        return visita;
    }
}
