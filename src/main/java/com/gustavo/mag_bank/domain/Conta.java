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
    protected Long numero;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    protected Cliente cliente;

    protected Double saldo;

    public Conta() {}

    public Conta(Integer id, Long numero, Cliente cliente, Double saldo) {
        this.id = id;
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = saldo;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
