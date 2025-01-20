package com.gustavo.mag_bank.services;

import com.gustavo.mag_bank.domain.Cliente;
import com.gustavo.mag_bank.repositories.ClienteRepository;
import com.gustavo.mag_bank.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não econtrado! ID: " + id));
    }

    public List<Cliente> findAll(){return repository.findAll();}
}
