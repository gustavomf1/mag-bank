package com.gustavo.mag_bank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Conta {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Column(unique = true)
    protected String numero;

    @JsonIgnore
    @OneToOne(mappedBy = "conta")
    protected Cliente cliente;

    @Column(name = "id_cliente")
    protected Integer idCliente;

    protected Double saldo;

    public Conta() {}

    public Conta(Integer id, String numero, Cliente cliente, Double saldo) {
        this.id = id;
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = saldo;
        this.idCliente = cliente.getId();
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
        this.idCliente = cliente != null ? cliente.getId() : null;
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

    public void depositar(Double valor) {
        this.saldo += valor;
    }

    public void sacar(Double valor) {
        // Implementar lógica de saque nas subclasses
    }
}
