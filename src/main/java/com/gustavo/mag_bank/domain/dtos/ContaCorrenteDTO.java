package com.gustavo.mag_bank.domain.dtos;

import com.gustavo.mag_bank.domain.ContaCorrente;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class ContaCorrenteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "Campo numero é requerido.")
    private String numero;

    @NotNull(message = "Campo saldo é requerido.")
    private Double saldo;

    @NotNull(message = "Campo limite é requerido.")
    private Double limite;

    private Integer idCliente;

    public ContaCorrenteDTO(Integer id, String numero, Double saldo, Double limite, Integer idCliente) {
        this.id = id;
        this.numero = numero;
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.limite = limite;
    }

    public ContaCorrenteDTO(ContaCorrente obj) {
        this.id = obj.getId();
        this.numero = obj.getNumero();
        this.saldo = obj.getSaldo();
        this.limite = obj.getLimite();
        this.idCliente = obj.getCliente() != null ? obj.getCliente().getId() : null;
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

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }
}
