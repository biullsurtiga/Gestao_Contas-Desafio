package com.urtiga.gestaocontas.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urtiga.gestaocontas.api.entities.Contas;
import com.urtiga.gestaocontas.api.repositories.ContasRepository;
import com.urtiga.gestaocontas.api.services.ContasService;

@Service
public class ContasServiceImpl implements ContasService{

	private static final Logger log = LoggerFactory.getLogger(ContasServiceImpl.class);
	
	@Autowired
	private ContasRepository contasRepository;
	
	@Override
	public Contas persistir(Contas contas) {
		log.info("Persistindo conta: {}", contas);
		return this.contasRepository.save(contas);
	}
	
	@Override
	public Optional<Contas> buscarPorId(Long id) {
		log.info("Buscando conta pelo ID: {}", id);
		return Optional.ofNullable(this.contasRepository.findOne(id));
	}

}
