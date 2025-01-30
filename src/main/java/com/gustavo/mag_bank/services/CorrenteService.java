package com.gustavo.mag_bank.services;

import com.gustavo.mag_bank.domain.ContaCorrente;
import com.gustavo.mag_bank.domain.Cliente;
import com.gustavo.mag_bank.domain.dtos.ContaCorrenteDTO;
import com.gustavo.mag_bank.repositories.CorrenteRepository;
import com.gustavo.mag_bank.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorrenteService {
    @Autowired
    private CorrenteRepository repository;

    @Autowired
    private ClienteService clienteService;

    public ContaCorrente findById(Integer id){
        Optional<ContaCorrente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<ContaCorrente> findAll(){
        return repository.findAll();
    }

    public ContaCorrente create(ContaCorrenteDTO objDTO){
        objDTO.setId(null);
        Cliente cliente = clienteService.findById(objDTO.getIdCliente());
        ContaCorrente newObj = new ContaCorrente(objDTO, cliente);
        return repository.save(newObj);
    }

    public ContaCorrente update(Integer id, ContaCorrenteDTO objDTO){
        objDTO.setId(id);

        ContaCorrente oldObj = findById(id);
        Cliente cliente = clienteService.findById(objDTO.getIdCliente()); // Buscando cliente pelo ID
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
}
