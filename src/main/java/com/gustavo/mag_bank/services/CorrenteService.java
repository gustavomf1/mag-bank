package com.gustavo.mag_bank.services;

import com.gustavo.mag_bank.domain.ContaCorrente;
import com.gustavo.mag_bank.domain.Cliente;
import com.gustavo.mag_bank.domain.ContaPoupanca;
import com.gustavo.mag_bank.domain.dtos.ContaCorrenteDTO;
import com.gustavo.mag_bank.repositories.ClienteRepository;
import com.gustavo.mag_bank.repositories.CorrenteRepository;
import com.gustavo.mag_bank.repositories.PoupancaRepository;
import com.gustavo.mag_bank.services.exceptions.ClientAlreadyRegistered;
import com.gustavo.mag_bank.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CorrenteService {
    @Autowired
    private CorrenteRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PoupancaRepository poupancaRepository;

    public ContaCorrente findById(Integer id){
        Optional<ContaCorrente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<ContaCorrente> findAll(){
        return repository.findAll();
    }

    public ContaCorrente create(ContaCorrenteDTO objDTO){
        objDTO.setId(null);
        Cliente cliente = clienteRepository.findById(objDTO.getIdCliente())
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + objDTO.getIdCliente()));

        Optional<ContaCorrente> existingAccountCorrente = repository.findByClienteId(objDTO.getIdCliente());
        Optional<ContaPoupanca> existingAccountPoupanca = poupancaRepository.findByClienteId(objDTO.getIdCliente());

        if(existingAccountPoupanca.isPresent()){
            throw new ClientAlreadyRegistered("O cliente já possui uma conta poupanca.");
        }

        if(existingAccountCorrente.isPresent()){
            throw new ClientAlreadyRegistered("O cliente já possui uma conta corrente.");
        }
        ContaCorrente newObj = new ContaCorrente(objDTO, cliente);
        return repository.save(newObj);
    }

    public ContaCorrente update(Integer id, ContaCorrenteDTO objDTO){
        objDTO.setId(id);

        ContaCorrente oldObj = findById(id);
        Cliente cliente = clienteRepository.findById(objDTO.getIdCliente())
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + objDTO.getIdCliente()));
        ContaCorrente updatedObj = new ContaCorrente(objDTO, cliente);  // Atualizando ContaCorrente com cliente

        return repository.save(updatedObj);
    }

    public void delete(Integer id){
        ContaCorrente obj = findById(id);
        permitirDeletar(obj);
        repository.deleteById(id);
    }

    private void permitirDeletar(ContaCorrente objDTO){
        if(objDTO.getSaldo() < 0){
            throw new RuntimeException("Não é possível deletar a conta com saldo negativo.");
        }

        if(objDTO.getSaldo() > 0){
            throw new RuntimeException("Não é possível deletar a conta com saldo positivo.");
        }
    }

    private Long gerarNumeroUnico() {
        Random random = new Random();
        boolean unico = false;
        Long novoNumero = null;

        while (!unico) {
            novoNumero = 100_000_000L + random.nextInt(900_000_000); // Gera um número entre 100000000 e 999999999
            unico = !repository.existsByNumero(novoNumero) && !poupancaRepository.existsByNumero(novoNumero);
        }

        return novoNumero;
    }
}
