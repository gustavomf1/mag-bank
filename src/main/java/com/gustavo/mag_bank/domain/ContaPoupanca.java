package com.gustavo.mag_bank.domain;

import com.gustavo.mag_bank.domain.dtos.ContaPoupancaDTO;
import jakarta.persistence.Entity;

@Entity
public class ContaPoupanca  extends Conta{

    private Double taxaJuros;

    public ContaPoupanca(){}

    public ContaPoupanca(ContaPoupancaDTO obj) {
        this.id = obj.getId();
        this.saldo = obj.getSaldo();
        this.numero = obj.getNumero();
        this.idCliente = obj.getIdCliente();
        this.taxaJuros = obj.getTaxaJuros();
    }

    public ContaPoupanca(Integer id, String numero, Cliente cliente, Double saldo, Double taxaJuros) {
        super(id, numero, cliente, saldo);
        this.taxaJuros = taxaJuros;
    }

    public ContaPoupanca(ContaPoupancaDTO dto, Cliente cliente) {
        super(dto.getId(), dto.getNumero(), cliente, dto.getSaldo());
        this.taxaJuros = dto.getTaxaJuros();
    }

    public ContaPoupanca(Double taxaJuros) {
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
