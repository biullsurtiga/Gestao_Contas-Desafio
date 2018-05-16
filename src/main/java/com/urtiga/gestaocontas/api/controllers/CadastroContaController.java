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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urtiga.gestaocontas.api.dtos.CadastroContaDto;
import com.urtiga.gestaocontas.api.entities.Contas;
import com.urtiga.gestaocontas.api.entities.Pessoas;
import com.urtiga.gestaocontas.api.enums.FlagAtivo;
import com.urtiga.gestaocontas.api.response.Response;
import com.urtiga.gestaocontas.api.services.ContasService;
import com.urtiga.gestaocontas.api.services.PessoasService;

@RestController
@RequestMapping("/api/cadastrar-cc")
public class CadastroContaController {

	private static final Logger log = LoggerFactory.getLogger(CadastroContaController.class);
	
	@Autowired
	private PessoasService pessoasService;
	
	@Autowired
	private ContasService contasService;
	
	public CadastroContaController() {
		
	}
	
	public ResponseEntity<Response<CadastroContaDto>> cadastrar(@Valid @RequestBody CadastroContaDto cadastroContaDto,
			BindingResult result) throws NoSuchAlgorithmException{
		log.info("Cadastrando Conta: {}", cadastroContaDto.toString());
		Response<CadastroContaDto> response = new Response<CadastroContaDto>();
		
		validarDadosExistentes(cadastroContaDto, result);
		
		Contas contas = this.converterDtoParaContas(cadastroContaDto, result);
		
		if(result.hasErrors()) {
			log.error("Erro validando dados de cadastro CC: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<Pessoas> pessoas = this.pessoasService.buscarPorCpf(cadastroContaDto.getCpf());
		pessoas.ifPresent(emp -> contas.setPessoas(emp));
		this.contasService.persistir(contas);
		
		response.setData(this.converterCadastroContaDto(contas));
		return ResponseEntity.ok(response);
	}
	

	private void validarDadosExistentes(CadastroContaDto cadastroContaDto, BindingResult result) {
		Optional<Pessoas> pessoas = this.pessoasService.buscarPorCpf(cadastroContaDto.getCpf());
		if(!pessoas.isPresent()) {
			result.addError(new ObjectError("pessoas", "Pessoa não Cadastrada."));
		}
		
		this.pessoasService.buscarPorCpf(cadastroContaDto.getCpf())
		.ifPresent(func -> result.addError(new ObjectError("pessoas", "CPF já existente")));
	}
	
	private Contas converterDtoParaContas(CadastroContaDto cadastroContaDto, BindingResult result)
			throws NoSuchAlgorithmException{
		Contas contas = new Contas();
		contas.setFlagAtivo(FlagAtivo.Ativo);
		contas.setTipoConta("Normal");
		cadastroContaDto.getSaldo().ifPresent(saldo -> contas.setSaldo(Float.valueOf(saldo)));
		cadastroContaDto.getLimiteSaqueDiario().ifPresent(limiteSaqueDiario -> contas.setLimiteSaqueDiario(Float.valueOf(limiteSaqueDiario)));
		
		return contas;
	}
	
	private CadastroContaDto converterCadastroContaDto(Contas contas){
		CadastroContaDto cadastroContaDto = new CadastroContaDto();
		cadastroContaDto.setId(contas.getIdConta());
		cadastroContaDto.setNome(contas.getPessoas().getNome());
		cadastroContaDto.setCpf(contas.getPessoas().getCpf());
		cadastroContaDto.setData(contas.getPessoas().getDataNascimento());
		
		contas.getSaldoOpt().ifPresent(saldo -> cadastroContaDto
				.setSaldo(Optional.of(Float.toString(saldo))));
		
		contas.getLimiteSaqueDiarioOpt().ifPresent(limiteSaqueDiario -> cadastroContaDto
				.setLimiteSaqueDiario(Optional.of(Float.toString(limiteSaqueDiario))));
		
		return cadastroContaDto;
	}
}
