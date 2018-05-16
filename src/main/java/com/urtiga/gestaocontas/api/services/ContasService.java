package com.urtiga.gestaocontas.api.services;

import java.util.Optional;

import com.urtiga.gestaocontas.api.entities.Contas;

public interface ContasService {

	Optional<Contas> buscarPorId(Long id);
	
	Contas persistir(Contas contas);
}
