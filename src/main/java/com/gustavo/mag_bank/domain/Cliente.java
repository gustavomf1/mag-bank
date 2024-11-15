package com.gustavo.mag_bank.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gustavo.mag_bank.domain.enums.ClienteTipo;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String senha;

    @Column(unique = true)
    private String email;

    private String endereco;

    private String telefone;

    @OneToOne
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    private Conta conta;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TIPO_CLIENTE")
    private Set<Integer> clienteTipo = new HashSet<>();

    public Cliente(Integer id, String cpf, LocalDate dataNascimento, String senha,
                   String email, String endereco, String telefone, Conta conta) {
        this.id = id;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.conta = conta;
        addClienteTipo(ClienteTipo.NORMAL);
    }

    public Cliente() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Set<ClienteTipo> getClienteTipo() {
        return clienteTipo.stream().map(x -> ClienteTipo.toEnum(x)).collect(Collectors.toSet());
    }

    public void addClienteTipo(ClienteTipo tipo) {
        this.clienteTipo.add(tipo.getCodigo());
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente pessoa = (Cliente) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }


}
