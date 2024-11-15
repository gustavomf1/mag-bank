package com.gustavo.mag_bank.domain;

import jakarta.persistence.Entity;

@Entity
public class ContaCorrente extends Conta{
    private Double limite;


    public ContaCorrente(){
    }

    public ContaCorrente(Double limite) {
        this.limite = limite;
    }

    public ContaCorrente(Integer id, String numero, Cliente cliente, Double saldo, Double limite) {
        super(id, numero, cliente, saldo);
        this.limite = limite;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    @Override
    public void sacar(Double valor){
        Double saldo = getSaldo();
        Double total = saldo + limite;
        if(valor <= total){
            saldo -= valor;
            setSaldo(saldo);
        }else{
            throw new IllegalArgumentException("Quantia inválida! O valor do saque excede o limite disponível.");
        }
    }


}
