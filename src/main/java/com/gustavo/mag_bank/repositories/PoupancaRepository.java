package com.gustavo.mag_bank.repositories;

import com.gustavo.mag_bank.domain.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoupancaRepository extends JpaRepository<ContaPoupanca, Integer> {
}
