/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.daos;

import fortaleza.ce.gov.br.acesso.models.Usuario;

/**
 *
 * @author everton
 */
public interface UsuarioDao {
    
    public String persistUser(Usuario usuario);
    public Usuario getByCpf(String cpf);
    public void gravaCodRecuperacao(String codigo, String email);
    public String getCodRecuperacao(String cpf);
    public void updatePassword(String senha, String cpf);
    public void updateCodRecuperacaoToNull(String cpf);
    

}
