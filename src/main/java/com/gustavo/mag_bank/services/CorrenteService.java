package com.gustavo.mag_bank.services;

import com.gustavo.mag_bank.domain.ContaCorrente;
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

    public ContaCorrente findById(Integer id){
        Optional<ContaCorrente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não econtrado! ID: " + id));
    }

    public List<ContaCorrente> findAll(){return repository.findAll();}

    public ContaCorrente create(ContaCorrenteDTO objDTO){
        objDTO.setId(null);
        ContaCorrente newObj = new ContaCorrente(objDTO);
        return repository.save(newObj);
    }

    public ContaCorrente update(Integer id, ContaCorrenteDTO objDTO){
        objDTO.setId(id);

        ContaCorrente oldObj = findById(id);
        ContaCorrente updateObj = new ContaCorrente(objDTO);

        return repository.save(updateObj);
    }

    public void delete(Integer id){
        ContaCorrente obj = findById(id);
        repository.deleteById(id);
    }

}

