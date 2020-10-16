/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.daos;

import fortaleza.ce.gov.br.acesso.models.Visita;
import java.util.List;

/**
 *
 * @author everton
 */
public interface VisitaDao {
    public void persist(Visita visita);
    public List<Visita> getVisitaByCpf(String cpf);
    public List<Visita> getVisitaByPlaca(String placa);
    
}
