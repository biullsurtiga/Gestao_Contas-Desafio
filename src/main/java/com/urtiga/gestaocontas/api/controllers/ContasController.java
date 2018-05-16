package com.urtiga.gestaocontas.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urtiga.gestaocontas.api.dtos.ContasDto;
import com.urtiga.gestaocontas.api.entities.Contas;
import com.urtiga.gestaocontas.api.response.Response;
import com.urtiga.gestaocontas.api.services.ContasService;

@RestController
@RequestMapping("/api/contas")
@CrossOrigin(origins = "*")
public class ContasController {

	private static final Logger log = LoggerFactory.getLogger(ContasController.class);
	
	@Autowired
	private ContasService contasService;
	
	public ContasController() {
		
	}
	
	@PutMapping(value="/{id}")
	ResponseEntity<Response<ContasDto>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody ContasDto contasDto, BindingResult result) throws NoSuchAlgorithmException{
		
		log.info("Atualizando conta: {}", contasDto.toString());
		Response<ContasDto> response = new Response<ContasDto>();
		
		Optional<Contas> contas = this.contasService.buscarPorId(id);
		if(!contas.isPresent()) {
			result.addError(new ObjectError("contas", "Conta não encontrada"));
		}
		
		this.atualizaDadosContas(contas.get(), contasDto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando contas: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.contasService.persistir(contas.get());
		response.setData(this.converterContasDto(contas.get()));
		
		return ResponseEntity.ok(response);
	}

	private ContasDto converterContasDto(Contas contas) {
		ContasDto contasDto = new ContasDto();
		contasDto.setId(contas.getId());
		contasDto.setTipoConta(contas.getTipoConta());
		
		contas.getLimiteSaqueDiarioOpt().ifPresent(limiteSaqueDiario -> contasDto.setLimiteSaqueDiario(Optional.of(Float.toString(limiteSaqueDiario))));
		
		return contasDto;
	}

	private void atualizaDadosContas(Contas contas, ContasDto contasDto, BindingResult result) {
		
		contas.setTipoConta(contasDto.getTipoConta());
		
		contas.setSaldo(null);
		contasDto.getSaldo().ifPresent(saldo -> contas.setSaldo(Float.valueOf(saldo)));
		
		contas.setLimiteSaqueDiario(null);
		contasDto.getLimiteSaqueDiario().ifPresent(limiteSaqueDiario -> contas.setLimiteSaqueDiario(Float.valueOf(limiteSaqueDiario)));
		
	}
}
