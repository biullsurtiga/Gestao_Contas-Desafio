package com.urtiga.gestaocontas.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.urtiga.gestaocontas.api.entities.Transacoes;

public interface TransacoesService {

	Page<Transacoes> buscarPorContasId(Long contasId, PageRequest pageResquest);
	
	Optional<Transacoes> buscarPorId(Long id);
	
	Transacoes persistir(Transacoes transacoes);
	
	void remover(Long id);
}
