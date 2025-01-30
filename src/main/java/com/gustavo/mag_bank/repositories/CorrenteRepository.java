package com.gustavo.mag_bank.repositories;

import com.gustavo.mag_bank.domain.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorrenteRepository extends JpaRepository<ContaCorrente, Integer> {
    Optional<ContaCorrente> findByClienteId(Integer clienteId);
}
