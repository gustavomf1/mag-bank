package com.gustavo.mag_bank.controllers;

import com.gustavo.mag_bank.domain.ContaCorrente;
import com.gustavo.mag_bank.domain.dtos.ContaCorrenteDTO;
import com.gustavo.mag_bank.services.CorrenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/correntes")
public class CorrenteController {
    @Autowired
    private CorrenteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContaCorrenteDTO> findByiD(@PathVariable Integer id){
        ContaCorrente obj = service.findById(id);
        return ResponseEntity.ok().body(new ContaCorrenteDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ContaCorrenteDTO>> findAll(){
        List<ContaCorrente> list = service.findAll();
        List<ContaCorrenteDTO> listDTO = list.stream().map(obj -> new ContaCorrenteDTO(obj)).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ContaCorrenteDTO> create(@Valid @RequestBody ContaCorrenteDTO objDTO){
        ContaCorrente newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ContaCorrenteDTO> update(@PathVariable Integer id, @Valid @RequestBody ContaCorrenteDTO objDTO){
        ContaCorrente newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new ContaCorrenteDTO(newObj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ContaCorrenteDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}
