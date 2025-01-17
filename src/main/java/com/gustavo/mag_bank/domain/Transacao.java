package com.gustavo.mag_bank.domain;

import com.gustavo.mag_bank.domain.enums.TransacaoTipo;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double valor;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "conta_origem_id") // Define a chave estrangeira no banco
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "conta_destino_id") // Define a chave estrangeira no banco
    private Conta contaDestino;

    @Enumerated(EnumType.STRING) // Armazena o nome da constante no banco
    @Column(name = "tipo")
    private TransacaoTipo transacaoTipo;

    public Transacao() {
    }

    public Transacao(Integer id, Double valor, Conta contaOrigem, Conta contaDestino, TransacaoTipo transacaoTipo) {
        this.id = id;
        this.valor = valor;
        this.data = LocalDateTime.now(); // Atribui a data atual automaticamente
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.transacaoTipo = transacaoTipo;
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

    public TransacaoTipo getTransacaoTipo() {
        return transacaoTipo;
    }

    public void setTransacaoTipo(TransacaoTipo transacaoTipo) {
        this.transacaoTipo = transacaoTipo;
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
