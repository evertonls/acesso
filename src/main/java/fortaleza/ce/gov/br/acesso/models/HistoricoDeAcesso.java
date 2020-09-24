/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.models;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author everton
 */
public class HistoricoDeAcesso implements Serializable{
    private Visitante visitante;
    private String placa;
    @NotBlank
    private LocalDate dataEntrada;
    @NotBlank
    private LocalDate dataSaida;

    public HistoricoDeAcesso() {
//        uso do framework
    }
    
    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }
    
    
}
