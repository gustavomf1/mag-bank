package com.gustavo.mag_bank.services;

import com.gustavo.mag_bank.domain.Cliente;
import com.gustavo.mag_bank.domain.ContaCorrente;
import com.gustavo.mag_bank.domain.ContaPoupanca;
import com.gustavo.mag_bank.domain.dtos.ClienteDTO;
import com.gustavo.mag_bank.domain.enums.ClienteTipo;
import com.gustavo.mag_bank.repositories.ClienteRepository;
import com.gustavo.mag_bank.repositories.CorrenteRepository;
import com.gustavo.mag_bank.repositories.PoupancaRepository;
import com.gustavo.mag_bank.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PoupancaRepository poupancaRepository;
    @Autowired
    private CorrenteRepository correnteRepository;
    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não econtrado! ID: " + id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        Cliente cliente = new Cliente(objDTO);
        return repository.save(cliente);
    }

    public Cliente update(Integer id, ClienteDTO objDTO){
        objDTO.setId(id);
        Cliente oldObj = findById(id);

        ContaPoupanca poupanca = poupancaRepository.findById(objDTO.getConta())
                .orElse(null);

        ContaCorrente corrente = correnteRepository.findById(objDTO.getConta())
                .orElse(null);

        if (poupanca != null) {
            oldObj.setConta(poupanca);
        }

        if (corrente != null) {
            oldObj.setConta(corrente);
        }

        oldObj = new Cliente(
                id,
                objDTO.getNome(),
                objDTO.getCpf(),
                objDTO.getDataNascimento(),
                objDTO.getSenha(),
                objDTO.getEmail(),
                objDTO.getEndereco(),
                objDTO.getTelefone(),
                poupanca != null ? poupanca : corrente,
                objDTO.getClienteTipo().stream().map(ClienteTipo::getCodigo).collect(Collectors.toSet())
        );

        return repository.save(oldObj);
    }

    public void delete(Integer id){
        Cliente obj = findById(id);
        repository.deleteById(id);
    }
}

