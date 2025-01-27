package com.gustavo.mag_bank.domain.dtos;

import com.gustavo.mag_bank.domain.ContaCorrente;
import com.gustavo.mag_bank.domain.ContaPoupanca;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public class ContaPoupancaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "Campo numero é requerido.")
    private String numero;

    @NotNull(message = "Campo idCliente é requerido.")
    private Integer idCliente;

    @NotNull(message = "Campo saldo é requerido.")
    private Double saldo;

    @NotNull(message = "Campo Taxa de Juros é requerido.")
    private Double taxaJuros;

    public ContaPoupancaDTO(Integer id, String numero, Integer idCliente, Double saldo, Double taxaJuros) {
        this.id = id;
        this.numero = numero;
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.taxaJuros = taxaJuros;
    }

    public ContaPoupancaDTO(ContaPoupanca obj) {
        this.id = obj.getId();
        this.numero = obj.getNumero();
        this.idCliente = obj.getIdCliente();
        this.saldo = obj.getSaldo();
        this.taxaJuros = obj.getTaxaJuros();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }
}
