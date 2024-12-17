package com.gustavo.mag_bank.resources;

import com.gustavo.mag_bank.service.PoupancaService;
import com.gustavo.mag_bank.domain.Cliente;
import com.gustavo.mag_bank.domain.ContaPoupanca;
import com.gustavo.mag_bank.domain.dtos.ContaPoupancaDTO;
import com.gustavo.mag_bank.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
