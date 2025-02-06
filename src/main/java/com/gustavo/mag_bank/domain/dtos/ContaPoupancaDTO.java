package com.gustavo.mag_bank.domain.dtos;

import com.gustavo.mag_bank.domain.ContaPoupanca;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public class ContaPoupancaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "Campo numero é requerido.")
    private Long numero;

    @NotNull(message = "Campo idCliente é requerido.")
    private Integer idCliente;

    @NotNull(message = "Campo saldo é requerido.")
    private Double saldo;

    @NotNull(message = "Campo Taxa de Juros é requerido.")
    private Double taxaJuros;

    public ContaPoupancaDTO(Integer id, Long numero, Integer idCliente, Double saldo, Double taxaJuros) {
        this.id = id;
        this.numero = numero;
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.taxaJuros = taxaJuros;
    }

    public ContaPoupancaDTO(ContaPoupanca obj) {
        this.id = obj.getId();
        this.numero = obj.getNumero();
        this.saldo = obj.getSaldo();
        this.taxaJuros = obj.getTaxaJuros();
        this.idCliente = obj.getCliente().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
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
