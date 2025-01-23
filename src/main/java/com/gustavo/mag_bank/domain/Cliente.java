package com.gustavo.mag_bank.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gustavo.mag_bank.domain.dtos.ClienteDTO;
import com.gustavo.mag_bank.domain.enums.ClienteTipo;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

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

    @Enumerated(EnumType.STRING) // Persistirá como string no banco de dados
    @Column(name = "tipo_cliente")
    private ClienteTipo clienteTipo;

    // Construtor padrão para inicializar o tipo de cliente como NORMAL por padrão
    public Cliente() {
    }

    // Construtor com campos essenciais, inicializando o tipo de cliente com valor passado ou padrão
    public Cliente(String cpf, LocalDate dataNascimento, String senha,
                   String email, String endereco, String telefone, String nome) {
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.nome = nome;
        this.clienteTipo = clienteTipo != null ? clienteTipo : ClienteTipo.NORMAL;
    }

    // Construtor para inicializar com DTO
    public Cliente(ClienteDTO objDTO) {
        this.id = objDTO.getId();
        this.nome = objDTO.getNome();
        this.cpf = objDTO.getCpf();
        this.dataNascimento = objDTO.getDataNascimento();
        this.senha = objDTO.getSenha();
        this.email = objDTO.getEmail();
        this.endereco = objDTO.getEndereco();
        this.telefone = objDTO.getTelefone();
    }

    // Construtor completo para inicializar todas as propriedades
    public Cliente(Integer id, String nome, String cpf, LocalDate dataNascimento,
                   String senha, String email, String endereco, String telefone,
                   Conta conta) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.conta = conta;
    }

    // Métodos Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public ClienteTipo getClienteTipo() {
        return clienteTipo;
    }

    public void setClienteTipo(ClienteTipo clienteTipo) {
        this.clienteTipo = clienteTipo;
    }

    // Método privado para obter o cliente tipo ou o padrão caso seja nulo ou vazio
    private ClienteTipo getClienteTipoOrDefault(Set<ClienteTipo> clienteTipo) {
        return clienteTipo != null && !clienteTipo.isEmpty() ? clienteTipo.iterator().next() : ClienteTipo.NORMAL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
