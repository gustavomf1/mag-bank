package com.gustavo.mag_bank.domain.dtos;

import com.gustavo.mag_bank.domain.Cliente;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class ContaCorrenteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "Campo numero é requerido.")
    @Column(unique = true)
    private String numero;

    @NotNull(message = "Campo cliente é requerido.")
    private Cliente cliente;

    @Column(name = "id_cliente")
    private Integer idCliente;

    @NotNull(message = "Campo saldo é requerido.")
    private Double saldo;

    @NotNull(message = "Campo limite é requerido.")
    private Double limite;

    public ContaCorrenteDTO(Integer id, String numero, Cliente cliente, Double saldo, Double limite) {
        this.id = id;
        this.numero = numero;
        this.cliente = cliente;
        this.idCliente = cliente != null ? cliente.getId() : null;
        this.saldo = saldo;
        this.limite = limite;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
