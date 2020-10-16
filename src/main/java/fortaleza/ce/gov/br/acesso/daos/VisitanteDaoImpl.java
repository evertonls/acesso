/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.daos;

import fortaleza.ce.gov.br.acesso.models.Visitante;
import java.io.Serializable;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author tinho
 */
public class VisitanteDaoImpl implements VisitanteDao, Serializable {
    private static final long serialVersionUID = 1L;
    private final JdbcTemplate template;
    private final Logger log;

   
    public VisitanteDaoImpl(DataSource ds, Logger log) {
        this.template = new JdbcTemplate(ds);
        this.log = log;
    }

    @Override
    public void persist(Visitante visit) {
        log.info("Gravando Visitante!");
        final String sql = "INSERT INTO visitantes(visit_cpf, visit_nome, visit_fone) "
                + "VALUES(?, ?, ?)";
        template.update(sql, visit.getCpf(), visit.getNome(), visit.getFone());
    }

}
