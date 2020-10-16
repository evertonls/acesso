/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.daos;

import fortaleza.ce.gov.br.acesso.models.Visitante;

/**
 *
 * @author tinho
 */
public interface VisitanteDao {
    public void persist (Visitante visit);
    //public Visitante getVisitanteByCPF(String cpf);
    //public Visitante getVisitanteByPlaca(String placa);
            
    
}
