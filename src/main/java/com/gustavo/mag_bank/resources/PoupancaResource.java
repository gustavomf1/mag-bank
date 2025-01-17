package com.gustavo.mag_bank.resources;

import com.gustavo.mag_bank.services.PoupancaService;
import com.gustavo.mag_bank.domain.Cliente;
import com.gustavo.mag_bank.domain.ContaPoupanca;
import com.gustavo.mag_bank.domain.dtos.ContaPoupancaDTO;
import com.gustavo.mag_bank.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/poupancas")
public class PoupancaResource {

    @Autowired
    private PoupancaService poupancaService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContaPoupancaDTO> findById(@PathVariable Integer id){
        ContaPoupanca obj = poupancaService.findById(id);
        return ResponseEntity.ok().body(new ContaPoupancaDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ContaPoupancaDTO>> findAll(){
        List<ContaPoupanca> list = poupancaService.findAll();
        List<ContaPoupancaDTO> listDTO = list.stream().map(obj -> new ContaPoupancaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ContaPoupancaDTO> create(@RequestBody ContaPoupancaDTO objDTO){
        ContaPoupanca newObj = poupancaService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(new ContaPoupancaDTO(newObj));
    }

    @PutExchange(value = "/{id}")
    public ResponseEntity<ContaPoupancaDTO> update(@PathVariable Integer id, @RequestBody ContaPoupancaDTO objDTO){
        ContaPoupanca newObj = poupancaService.update(id, objDTO);
        return ResponseEntity.ok().body(new ContaPoupancaDTO(newObj));
    }


}
