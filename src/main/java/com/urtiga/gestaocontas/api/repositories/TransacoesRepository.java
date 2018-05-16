package com.urtiga.gestaocontas.api.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.urtiga.gestaocontas.api.entities.Transacoes;

@Transactional(readOnly=true)
@NamedQueries({
	@NamedQuery(name = "TransacoesRepository.findByContasId",
				query = "SELECT tran FROM Transacoes tran WHERE tran.contas.id = :contasId") })
public interface TransacoesRepository extends JpaRepository<Transacoes, Long>{

	List<Transacoes> findByContasId(@Param("contasId") Long contasId);
	
	Page<Transacoes> findByContasId(@Param("contasId") Long contasId, Pageable pageable);
}
