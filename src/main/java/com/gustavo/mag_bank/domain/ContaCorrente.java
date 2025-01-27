package com.gustavo.mag_bank.domain;

import com.gustavo.mag_bank.domain.dtos.ContaCorrenteDTO;
import jakarta.persistence.Entity;

@Entity
public class ContaCorrente extends Conta{
    private Double limite;


    public ContaCorrente(){
    }

    public ContaCorrente(ContaCorrenteDTO obj){
        this.id = obj.getId();
        this.saldo = obj.getSaldo();
        this.numero = obj.getNumero();
        this.idCliente = obj.getIdCliente();
        this.limite = obj.getLimite();
    }

    public ContaCorrente(Integer id, String numero, Cliente cliente, Double saldo, Double limite) {
        super(id, numero, cliente, saldo);
        this.limite = limite;
    }

    public ContaCorrente(ContaCorrenteDTO dto, Cliente cliente) {
        super(dto.getId(), dto.getNumero(), cliente, dto.getSaldo());
        this.limite = dto.getLimite();
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }



}
