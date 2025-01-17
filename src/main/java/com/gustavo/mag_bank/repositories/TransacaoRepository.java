package com.gustavo.mag_bank.repositories;

import com.gustavo.mag_bank.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
}
