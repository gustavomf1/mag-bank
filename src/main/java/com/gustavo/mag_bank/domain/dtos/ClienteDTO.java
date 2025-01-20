package com.gustavo.mag_bank.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gustavo.mag_bank.domain.Cliente;
import com.gustavo.mag_bank.domain.Conta;
import com.gustavo.mag_bank.domain.enums.ClienteTipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteDTO {

    private Integer id;

    @NotNull(message = "O Campo nome é requerido.")
    private String nome;

    @Column(unique = true)
    @NotNull(message = "Campo CPF é requerido.")
    @CPF
    private String cpf;

    @NotNull(message = "Campo Data de nascimento é requerido.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull(message = "Campo senha é requerido.")
    private String senha;


    @NotNull(message = "Campo email é requerido.")
    @Column(unique = true)
    @Email
    private String email;

    @NotNull(message = "Campo endereco é requerido.")
    private String endereco;

    @NotNull(message = "Campo telefone é requerido.")
    private String telefone;


    private Integer conta;

    @NotNull(message = "O campo Tipo de Cliente é requerido")
    private Set<Integer> clienteTipo = new HashSet<>();

    public ClienteDTO(Cliente obj) {
        this.id = obj.getId();
        this.cpf = obj.getCpf();
        this.dataNascimento = obj.getDataNascimento();
        this.senha = obj.getSenha();
        this.email = obj.getEmail();
        this.endereco = obj.getEndereco();
        this.telefone = obj.getTelefone();
        this.conta = obj.getConta() != null ? obj.getConta().getId() : null;
        this.nome = obj.getNome();
        addClienteTipo(ClienteTipo.ADMIN);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public Set<ClienteTipo> getClienteTipo() {
        return clienteTipo.stream().map(x -> ClienteTipo.toEnum(x)).collect(Collectors.toSet());
    }

    public void addClienteTipo(ClienteTipo perfil) {
        this.clienteTipo.add(perfil.getCodigo());
    }
}
