package com.urtiga.gestaocontas.api.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urtiga.gestaocontas.api.dtos.PessoasDto;
import com.urtiga.gestaocontas.api.entities.Pessoas;
import com.urtiga.gestaocontas.api.response.Response;
import com.urtiga.gestaocontas.api.services.PessoasService;

@RestController
@RequestMapping("/api/pessoas")
@CrossOrigin(origins = "*")
public class PessoasController {

	private static final Logger log = LoggerFactory.getLogger(PessoasController.class);
	
	@Autowired
	private PessoasService pessoasService;
	
	public PessoasController() {
		
	}
	
	@GetMapping(value="/cpf/{cpf}")
	public ResponseEntity<Response<PessoasDto>> buscarPorCpf(@PathVariable("cpf") String cpf) {
		log.info("Buscanco pessoa por CPF: {}", cpf);
		Response<PessoasDto> response = new Response<PessoasDto>();
		Optional<Pessoas> pessoas = pessoasService.buscarPorCpf(cpf);
		
		if(!pessoas.isPresent()) {
			log.info("Pessoa não encontrada pelo CPF: {}", cpf);
			response.getErros().add("Pessoa não encontrada pelo CPF "+ cpf);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterPessoasDto(pessoas.get()));
		return ResponseEntity.ok(response);
	}

	private PessoasDto converterPessoasDto(Pessoas pessoas) {
		PessoasDto pessoasDto = new PessoasDto();
		pessoasDto.setId(pessoas.getId());
		pessoasDto.setNome(pessoas.getNome());
		pessoasDto.setCpf(pessoas.getCpf());
		return pessoasDto;
	}
}
