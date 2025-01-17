package com.gustavo.mag_bank.services;

import com.gustavo.mag_bank.domain.Cliente;
import com.gustavo.mag_bank.domain.ContaCorrente;
import com.gustavo.mag_bank.domain.ContaPoupanca;
import com.gustavo.mag_bank.domain.Transacao;
import com.gustavo.mag_bank.domain.enums.TransacaoTipo;
import com.gustavo.mag_bank.repositories.ClienteRepository;
import com.gustavo.mag_bank.repositories.CorrenteRepository;
import com.gustavo.mag_bank.repositories.PoupancaRepository;
import com.gustavo.mag_bank.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PoupancaRepository poupancaRepository;
    @Autowired
    private CorrenteRepository correnteRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

    public void instanciaDB(){
        Cliente cli1 = new Cliente("43919360893", LocalDate.of(1990, 5, 15), "senha123", "cliente@exemplo.com",
                "Rua Exemplo, 123", "99999-9999", "João Silva"
        );

        Cliente cli2 = new Cliente("43919361193", LocalDate.of(2003, 5, 15), "senha123", "teste@exemplo.com",
                "Rua Exemplo, 42", "91919-9999", "João Martins"
        );

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));

        ContaPoupanca contaPoupanca = new ContaPoupanca(null, "12345667", cli1, 1200.25, 10.0);

        ContaCorrente contaCorrente = new ContaCorrente(null, "188390203", cli2, 333333.22, 10.0);

        poupancaRepository.saveAll(Arrays.asList(contaPoupanca));
        correnteRepository.saveAll(Arrays.asList(contaCorrente));

        Transacao transacao1 = new Transacao(null, 1000.0, contaCorrente, contaPoupanca, TransacaoTipo.PIX);


        transacaoRepository.saveAll(Arrays.asList(transacao1));

        System.out.println("Banco de dados populado com sucesso!");
    }


}
