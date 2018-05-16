package com.urtiga.gestaocontas.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urtiga.gestaocontas.api.entities.Pessoas;
import com.urtiga.gestaocontas.api.repositories.PessoasRepository;
import com.urtiga.gestaocontas.api.services.PessoasService;

@Service
public class PessoasServiceImpl implements PessoasService{

	private static final Logger log = LoggerFactory.getLogger(PessoasServiceImpl.class);
	
	@Autowired
	private PessoasRepository pessoasRepository;
	
	@Override
	public Optional<Pessoas> buscarPorCpf(String cpf) {
		log.info("Buscando uma pessoa para o CPF {}", cpf);
		return Optional.ofNullable(pessoasRepository.findByCpf(cpf));
	}

	@Override
	public Pessoas persistir(Pessoas pessoas) {
		log.info("Persistindo pessoa: {}", pessoas);
		return this.pessoasRepository.save(pessoas);
	}
}
