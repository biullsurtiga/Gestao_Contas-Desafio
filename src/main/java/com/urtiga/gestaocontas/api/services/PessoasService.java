package com.urtiga.gestaocontas.api.services;

import java.util.Optional;

import com.urtiga.gestaocontas.api.entities.Pessoas;

public interface PessoasService {

	Optional<Pessoas> buscarPorCpf(String cpf);
	
	Pessoas persistir(Pessoas pessoas);
}
