/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.services;

import fortaleza.ce.gov.br.acesso.daos.UsuarioDao;
import fortaleza.ce.gov.br.acesso.models.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author everton
 */

@Service("MyDatabaseUserDetailsService")
public class MyDatabaseUserDetailsService implements UserDetailsService {

    private final UsuarioDao jdbcUsuarioDAO;
    
    @Autowired
    public MyDatabaseUserDetailsService(UsuarioDao jdbcUsuarioDAO) {
        super();
        this.jdbcUsuarioDAO = jdbcUsuarioDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios.add(jdbcUsuarioDAO.getByCpf(username));
        } catch (Exception e) {
            throw new UsernameNotFoundException("O usuário " + username + " não existe!");
        }
        return usuarios.get(0);
    }

}
