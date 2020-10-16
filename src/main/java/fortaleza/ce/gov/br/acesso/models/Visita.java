/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author everton
 */
public class Visita implements Serializable {

    private Visitante visitante;
    private Usuario usuario;
    private String placa;
    @NotBlank
    private LocalDateTime dataEntrada;
    @NotBlank
    private LocalDateTime dataSaida;
    private Setor setor;
    private boolean agendada;
    private String autoriadoPor;

    public Visita() {
//        uso do framework
    }

    
    public Visita(Visitante visitante, Usuario usuario, String placa, 
            LocalDateTime dataEntrada, LocalDateTime dataSaida, Setor setor, 
            String autoriadoPor) {
        
        this.visitante = visitante;
        this.usuario = usuario;
        this.placa = placa;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.setor = setor;
        this.autoriadoPor = autoriadoPor;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public boolean isAgendada() {
        return agendada;
    }

    public void setAgendada(boolean agendada) {
        this.agendada = agendada;
    }

    public String getAutoriadoPor() {
        return autoriadoPor;
    }

    public void setAutoriadoPor(String autoriadoPor) {
        this.autoriadoPor = autoriadoPor;
    }

}
