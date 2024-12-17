package com.gustavo.mag_bank.service;

import com.gustavo.mag_bank.domain.ContaPoupanca;
import com.gustavo.mag_bank.repositories.PoupancaRepository;
import com.gustavo.mag_bank.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PoupancaService {
    @Autowired
    private PoupancaRepository repository;
    public ContaPoupanca findById(Integer id){
        Optional<ContaPoupanca> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não econtrado! ID: " + id));
    }

}
