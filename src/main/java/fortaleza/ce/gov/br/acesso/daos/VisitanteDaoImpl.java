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
    private JdbcTemplate template;
    private Logger logger;

   
    public VisitanteDaoImpl(DataSource ds, Logger logger) {
        this.template = new JdbcTemplate(ds);
        this.logger = logger;
    }

    @Override
    public void persist(Visitante visit) {
    }

    @Override
    public Visitante getVisitanteByCPF(String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Visitante getVisitanteByPlaca(String placa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
