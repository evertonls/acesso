/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.models;

import java.io.Serializable;
import java.text.Collator;
import java.util.Locale;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author everton
 */
public class Autorizacao implements GrantedAuthority, Serializable, Comparable<Autorizacao> {

    private static final long serialVersionUID = 1L;
    private String authority;
    private String descricao;

    
    public Autorizacao(String authority) {
        super();
        this.authority = authority;
    }

    public Autorizacao(String authority, String descricao) {
        super();
        this.authority = authority;
        this.descricao = descricao;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String nome) {
        this.authority = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Autorizacao)) {
            return false;
        }

        Autorizacao r = (Autorizacao) obj;

        return Objects.equals(authority, r.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }

    @Override
    public int compareTo(Autorizacao o) {
        Collator brCollator = Collator.getInstance(Locale.getDefault());
        brCollator.setStrength(Collator.PRIMARY);
        return brCollator.compare(this.getDescricao(), o.getDescricao());
    }

}
