package com.gustavo.mag_bank.services;

import com.gustavo.mag_bank.domain.ContaCorrente;
import com.gustavo.mag_bank.domain.ContaPoupanca;
import com.gustavo.mag_bank.domain.Cliente;
import com.gustavo.mag_bank.domain.dtos.ContaPoupancaDTO;
import com.gustavo.mag_bank.repositories.ClienteRepository;
import com.gustavo.mag_bank.repositories.CorrenteRepository;
import com.gustavo.mag_bank.repositories.PoupancaRepository;
import com.gustavo.mag_bank.services.exceptions.ClientAlreadyRegistered;
import com.gustavo.mag_bank.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoupancaService {

    @Autowired
    private PoupancaRepository repository;

    @Autowired
    private CorrenteRepository correnteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public ContaPoupanca findById(Integer id) {
        Optional<ContaPoupanca> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<ContaPoupanca> findAll() {
        return repository.findAll();
    }

    public ContaPoupanca create(ContaPoupancaDTO objDTO) {
        objDTO.setId(null);


        Cliente cliente = clienteRepository.findById(objDTO.getIdCliente())
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + objDTO.getIdCliente()));

        Optional<ContaPoupanca> existingAccountPoupanca = repository.findByClienteId(objDTO.getIdCliente());
        Optional<ContaCorrente> existingAccountCorrente = correnteRepository.findByClienteId(objDTO.getIdCliente());

        if(existingAccountPoupanca.isPresent()){
            throw new ClientAlreadyRegistered("O cliente já possui uma conta poupanca.");
        }

        if(existingAccountCorrente.isPresent()){
            throw new ClientAlreadyRegistered("O cliente já possui uma conta corrente.");
        }
        ContaPoupanca newObj = new ContaPoupanca(objDTO, cliente);
        return repository.save(newObj);
    }

    public ContaPoupanca update(Integer id, ContaPoupancaDTO objDTO) {
        objDTO.setId(id);

        ContaPoupanca oldObj = findById(id);
        Cliente cliente = clienteRepository.findById(objDTO.getIdCliente())
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + objDTO.getIdCliente()));

        ContaPoupanca updatedObj = new ContaPoupanca(objDTO, cliente);
        return repository.save(updatedObj);
    }

    public void delete(Integer id) {
        ContaPoupanca obj = findById(id);
        permitirDeletar(obj);
        repository.deleteById(id);
    }

    private void permitirDeletar(ContaPoupanca obj) {
        if (obj.getSaldo() < 0) {
            throw new RuntimeException("Não é possível deletar a conta com saldo negativo.");
        }

        if (obj.getSaldo() > 0) {
            throw new RuntimeException("Não é possível deletar a conta com saldo positivo.");
        }
    }
}
