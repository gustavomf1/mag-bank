package com.gustavo.mag_bank.domain;

import com.gustavo.mag_bank.domain.dtos.ContaCorrenteDTO;
import jakarta.persistence.Entity;

@Entity
public class ContaCorrente extends Conta {
    private Double limite;

    public ContaCorrente() {
    }

    public ContaCorrente(ContaCorrenteDTO dto, Cliente cliente) {
        super(dto.getId(), dto.getNumero(), cliente, dto.getSaldo());
        this.limite = dto.getLimite();
    }

    public ContaCorrente(Integer id, Long numero, Cliente cliente, Double saldo, Double limite) {
        super(id, numero, cliente, saldo);
        this.limite = limite;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }
}
