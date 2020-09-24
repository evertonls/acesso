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
    private String endereco;

    public Visitante() {
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

}
