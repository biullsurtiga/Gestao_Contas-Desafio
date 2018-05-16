package com.urtiga.gestaocontas.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.urtiga.gestaocontas.api.entities.Transacoes;
import com.urtiga.gestaocontas.api.repositories.TransacoesRepository;
import com.urtiga.gestaocontas.api.services.TransacoesService;

@Service
public class TransacoesServiceImpl implements TransacoesService{

	private static final Logger log = LoggerFactory.getLogger(TransacoesServiceImpl.class);
	
	@Autowired
	private TransacoesRepository trasacoesRepository;

	@Override
	public Page<Transacoes> buscarPorContasId(Long contasId, PageRequest pageResquest) {
		log.info("Buscanco transacoes para a conta ID: {}", contasId);
		return this.trasacoesRepository.findByContasId(contasId, pageResquest);
	}

	@Override
	public Optional<Transacoes> buscarPorId(Long id) {
		log.info("Buscanco uma transacao pelo ID: {}", id);
		return Optional.ofNullable(this.trasacoesRepository.findOne(id));
	}

	@Override
	public Transacoes persistir(Transacoes transacoes) {
		log.info("Persistindo a transacao: {}", transacoes);
		return this.trasacoesRepository.save(transacoes);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo a Transação: {}", id);
		this.trasacoesRepository.delete(id);
		
	}

}
