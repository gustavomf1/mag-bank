package com.gustavo.mag_bank.domain;

import com.gustavo.mag_bank.domain.enums.ClienteTipo;
import com.gustavo.mag_bank.domain.enums.TransacaoTipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Transacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double valor;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private Conta contaDestino;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TIPO_TRANSACAO")
    private Set<Integer> transacaoTipo = new HashSet<>();

    public Transacao() {
    }

    public Transacao(Double valor, Conta conta, Integer contaOrigemId, Integer contaDestinoId) {
        this.valor = valor;
        this.data = LocalDateTime.now();
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
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

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Set<TransacaoTipo> getTransacaoTipo() {
        return transacaoTipo.stream().map(x -> TransacaoTipo.toEnum(x)).collect(Collectors.toSet());
    }

    public void addTransacaoTipo(TransacaoTipo tipo) {
        this.transacaoTipo.add(tipo.getCodigo());
    }

    public boolean executarTransacao(){
        if (contaOrigem.getSaldo() >= valor) {
            // Subtrair da conta de origem
            contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);

            // Adicionar na conta de destino
            contaDestino.setSaldo(contaDestino.getSaldo() + valor);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(id, transacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
