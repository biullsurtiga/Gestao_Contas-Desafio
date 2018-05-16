package com.urtiga.gestaocontas.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.urtiga.gestaocontas.api.entities.Pessoas;
@Transactional(readOnly=true)
public interface PessoasRepository extends JpaRepository<Pessoas, Long>{

	Pessoas findByCpf(String cpf);
	
}
