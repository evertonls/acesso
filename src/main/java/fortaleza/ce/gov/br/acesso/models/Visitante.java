/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.models;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author everton
 */
public class Visitante implements Serializable {

    private static final long serialVersionUID = 1L;
    @CPF
    @NotBlank(message = "{field.cpf.validation.contraints.NotBlank.message}")
    private String cpf;
    @NotBlank(message = "{field.nome.validation.contraints.NotBlank.message}")
    private String nome;
    private String fone;

    public Visitante() {
        //uso do framework
    }

    public Visitante(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    
}