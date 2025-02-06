package com.gustavo.mag_bank.repositories;

import com.gustavo.mag_bank.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
