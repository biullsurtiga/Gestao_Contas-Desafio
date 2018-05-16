package com.urtiga.gestaocontas.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.urtiga.gestaocontas.api.entities.Contas;

@Transactional(readOnly=true)

public interface ContasRepository extends JpaRepository<Contas, Long>{

	Contas findBySaldo(Float saldo);
}
