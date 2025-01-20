package com.gustavo.mag_bank.services;

import com.gustavo.mag_bank.domain.ContaPoupanca;
import com.gustavo.mag_bank.domain.dtos.ContaPoupancaDTO;
import com.gustavo.mag_bank.repositories.PoupancaRepository;
import com.gustavo.mag_bank.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoupancaService {
    @Autowired
    private PoupancaRepository repository;
    public ContaPoupanca findById(Integer id){
        Optional<ContaPoupanca> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não econtrado! ID: " + id));
    }

    public List<ContaPoupanca> findAll(){return repository.findAll();}

    public ContaPoupanca create(ContaPoupancaDTO objDTO){
        objDTO.setId(null);
        ContaPoupanca newObj = new ContaPoupanca(objDTO);
        return repository.save(newObj);
    }

    public ContaPoupanca update(Integer id, ContaPoupancaDTO objDTO){
        objDTO.setId(id);
        ContaPoupanca oldObj = findById(id);
        existeId(objDTO);
        ContaPoupanca updatedObj = new ContaPoupanca(objDTO);
        return repository.save(updatedObj);
    }

    public void delete(Integer id){
        ContaPoupanca obj = findById(id);
        permitirDeletar(obj);
        repository.deleteById(id);
    }

    private void existeId(ContaPoupancaDTO objDTO){
        if(objDTO == null){
            throw new ObjectNotFoundException("Objeto não econtrado! ID: " + objDTO.getId());
        }
    }

    private void permitirDeletar(ContaPoupanca objDTO){
        if(objDTO.getSaldo() < 0){
            throw new RuntimeException("Não é possível deletar a conta com saldo negativo.");
        }

        if(objDTO.getSaldo() > 0){
            throw new RuntimeException("Não é possível deletar a conta com saldo.");
        }
    }



}
