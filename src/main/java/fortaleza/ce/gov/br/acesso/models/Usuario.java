/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import fortaleza.ce.gov.br.acesso.annotations.EqualFields;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 *
 * @author everton
 */
@Component
@EqualFields.List({
		@EqualFields(baseValue = "email", matchValue = "confEmail", message = "{field.email.validation.contraints.EqualFields.message}"),
		@EqualFields(baseValue = "senha", matchValue = "confSenha", message = "{field.senha.validation.contraints.EqualFields.message}") })
@JsonIgnoreProperties({ "username", "password", "enabled", "accountNonExpired", "authorities", "accountNonLocked",
		"credentialsNonExpired" })
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotBlank(message = "{field.nome.validation.contraints.NotBlank.message}")
    @Length(min = 3, max = 255)
    private String nome;
    @NotBlank(message = "{field.apelido.validation.contraints.NotBlank.message}")
    @Length(min = 3, max = 255)
    private String apelido;
    @Email
    @NotBlank(message = "{field.email.validation.contraints.NotBlank.message}")
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @DateTimeFormat
    private LocalDate dataNascimento;
    @JsonIgnore
    @Length(max = 10, message = "{field.codRecuperacao.validation.contraints.Length.message}")
    private String codRecuperacao;
    private List<Autorizacao> roles = new ArrayList<>();
    @NotBlank(message = "{field.senha.validation.contraints.NotBlank.message}")
    @Pattern(regexp = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{8,10})$", message = "{field.senha.validation.contraints.Pattern.message}")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    /*
     * Somente para a área de cadastro do usuário
     */
    @NotBlank(message = "{field.confsenha.validation.contraints.NotBlank.message}")
    @Pattern(regexp = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{8,10})$", message = "{field.confsenha.validation.contraints.Pattern.message}")
    @JsonIgnore
    private String confSenha;
    @Email
    @NotBlank(message = "{field.confemail.validation.contraints.NotBlank.message}")
    @JsonIgnore
    private String confEmail;
    

    public Usuario() {
//        uso do frameworl
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCodRecuperacao() {
        return codRecuperacao;
    }

    public void setCodRecuperacao(String codRecuperacao) {
        this.codRecuperacao = codRecuperacao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfSenha() {
        return confSenha;
    }

    public void setConfSenha(String confSenha) {
        this.confSenha = confSenha;
    }

    public String getConfEmail() {
        return confEmail;
    }

    public void setConfEmail(String confEmail) {
        this.confEmail = confEmail;
    }

    public List<Autorizacao> getRoles() {
        return roles;
    }

    public void setRoles(List<Autorizacao> roles) {
        this.roles = roles;
    }


}
