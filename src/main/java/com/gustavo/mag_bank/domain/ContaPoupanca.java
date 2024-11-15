package com.gustavo.mag_bank.domain;

import jakarta.persistence.Entity;

@Entity
public class ContaPoupanca  extends Conta{

    private Double taxaJuros;

    public ContaPoupanca() {
    }

    public ContaPoupanca(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public ContaPoupanca(Integer id, String numero, Cliente cliente, Double saldo, Double taxaJuros) {
        super(id, numero, cliente, saldo);
        this.taxaJuros = taxaJuros;
    }

    public Double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    @Override
    public void sacar(Double valor){
        Double saldo = getSaldo();
        Double a;
        if(valor <= saldo){
            a = saldo * taxaJuros;
            saldo -= a;
            setSaldo(saldo);
        }
        else{
            throw new IllegalArgumentException("Quantia inválida! O valor do saque excede o montante disponível.");
        }
    }

}
