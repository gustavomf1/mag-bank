package com.gustavo.mag_bank.domain.dtos;

import com.gustavo.mag_bank.domain.Transacao;
import com.gustavo.mag_bank.domain.enums.TransacaoTipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TransacaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "Campo numero é requerido.")
    private Double valor;

    private LocalDateTime data;

    @NotNull(message = "Campo Conta de origem é requerido.")
    private Integer contaOrigem;

    @ManyToOne
    @NotNull(message = "Campo Conta destino é requerido.")
    private Integer contaDestino;


    private Set<Integer> transacaoTipo = new HashSet<>();


    public TransacaoDTO(Transacao obj){
        this.id = obj.getId();
        this.valor = obj.getValor();
        this.contaOrigem = obj.getContaOrigem().getId();
        this.contaDestino = obj.getContaDestino().getId();
        this.transacaoTipo = obj.getTransacaoTipo().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.data = obj.getData();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Integer getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Integer contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Integer getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Integer contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Set<TransacaoTipo> getTransacaoTipo() {
        return transacaoTipo.stream().map(x -> TransacaoTipo.toEnum(x)).collect(Collectors.toSet());
    }

    public void addTransacaoTipo(TransacaoTipo tipo) {
        this.transacaoTipo.add(tipo.getCodigo());
    }
}
