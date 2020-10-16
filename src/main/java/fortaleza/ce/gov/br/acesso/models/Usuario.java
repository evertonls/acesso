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
import java.text.Collator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author everton
 */
@Component
@EqualFields.List({
    @EqualFields(baseValue = "email", matchValue = "confEmail", message = "{field.email.validation.contraints.EqualFields.message}"),
    @EqualFields(baseValue = "senha", matchValue = "confSenha", message = "{field.senha.validation.contraints.EqualFields.message}")})
@JsonIgnoreProperties({"username", "password", "enabled", "accountNonExpired", "authorities", "accountNonLocked",
    "credentialsNonExpired"})
public class Usuario implements UserDetails, Serializable, Comparable<Usuario> {

    private static final long serialVersionUID = 1L;
    @NotBlank(message = "{field.nome.validation.contraints.NotBlank.message}")
    @Length(min = 3, max = 255)
    private String nome;
    @NotBlank(message = "{field.apelido.validation.contraints.NotBlank.message}")
    @Length(min = 3, max = 255)
    private String apelido;
    @CPF
    @NotBlank(message = "{field.cpf.validation.contraints.NotBlank.message}")
    private String cpf;
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
    private boolean ativo;
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

    /**
     *
     */
    public Usuario() {
//        uso do frameworl
    }

    public Usuario(String nome, String apelido, String cpf, String email, LocalDate dataNascimento, String senha) {
        this.nome = nome;
        this.apelido = apelido;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    public Usuario(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getApelido() {
        return apelido;
    }

    /**
     *
     * @param apelido
     */
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    /**
     *
     * @return
     */
    public String getCpf() {
        return cpf;
    }

    /**
     *
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     *
     * @param dataNascimento
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     *
     * @return
     */
    public String getCodRecuperacao() {
        return codRecuperacao;
    }

    /**
     *
     * @param codRecuperacao
     */
    public void setCodRecuperacao(String codRecuperacao) {
        this.codRecuperacao = codRecuperacao;
    }

    /**
     *
     * @return
     */
    public String getSenha() {
        return senha;
    }

    /**
     *
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     *
     * @return
     */
    public String getConfSenha() {
        return confSenha;
    }

    /**
     *
     * @param confSenha confirmação de senha
     */
    public void setConfSenha(String confSenha) {
        this.confSenha = confSenha;
    }

    /**
     *
     * @return
     */
    public String getConfEmail() {
        return confEmail;
    }

    /**
     *
     * @param confEmail
     */
    public void setConfEmail(String confEmail) {
        this.confEmail = confEmail;
    }

    /**
     *
     * @return
     */
    public List<Autorizacao> getRoles() {
        return roles;
    }

    /**
     *
     * @param roles
     */
    public void setRoles(List<Autorizacao> roles) {
        this.roles = roles;
    }

    /**
     *
     * @param roles
     */
    public void setAuthorities(List<Autorizacao> roles) {
        this.roles.addAll(roles);
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Usuario)) {
            return false;
        }

        Usuario usuario = (Usuario) obj;

        return usuario.cpf.equals(cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    // comparação por ordem natural. nome completo.
    @Override
    public int compareTo(Usuario o) {
        Collator brCollator = Collator.getInstance(Locale.getDefault());
        brCollator.setStrength(Collator.PRIMARY);
        return brCollator.compare(nome, o.getNome());
    }

    public int compareApelido(Usuario o) {
        Collator brCollator = Collator.getInstance(Locale.getDefault());
        brCollator.setStrength(Collator.PRIMARY);
        return brCollator.compare(apelido, o.getApelido());
    }

}
