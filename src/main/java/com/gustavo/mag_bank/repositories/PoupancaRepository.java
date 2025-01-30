package com.gustavo.mag_bank.repositories;

import com.gustavo.mag_bank.domain.Cliente;
import com.gustavo.mag_bank.domain.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PoupancaRepository extends JpaRepository<ContaPoupanca, Integer> {
    Optional<ContaPoupanca> findByClienteId(Integer clienteId);
}
