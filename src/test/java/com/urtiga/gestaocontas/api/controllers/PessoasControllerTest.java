package com.urtiga.gestaocontas.api.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.urtiga.gestaocontas.api.entities.Pessoas;
import com.urtiga.gestaocontas.api.services.PessoasService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PessoasControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PessoasService pessoasService;
	
	private static final String BUSCAR_PESSOAS_CPF_URL = "/api/pessoas/cnpj/";
	private static final Long ID = Long.valueOf(1);
	private static final String CPF = "08260580438";
	private static final String NOME = "SEVERINO URTIGA";
	
	@Test
	@WithMockUser
	public void testBuscarPessoasCpfInvalido() throws Exception{
		BDDMockito.given(this.pessoasService.buscarPorCpf(Mockito.anyString())).willReturn(Optional.empty());
		
		mvc.perform(MockMvcRequestBuilders.get(BUSCAR_PESSOAS_CPF_URL + CPF).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors").value("Pessoa não encontrada para o CPF " + CPF));
	}
	
	@Test
	@WithMockUser
	public void testBuscarPessoasCpfValido() throws Exception{
		BDDMockito.given(this.pessoasService.buscarPorCpf(Mockito.anyString())).willReturn(Optional.of((this.obterDadosPessoas())));
		
		mvc.perform(MockMvcRequestBuilders.get(BUSCAR_PESSOAS_CPF_URL + CPF)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.nome" , equalTo(NOME)))
		.andExpect(jsonPath("$.data.cpf", equalTo(CPF)))
	    .andExpect(jsonPath("$.errors").isEmpty());

	}

	private Pessoas obterDadosPessoas() {
		Pessoas pessoas = new Pessoas();
		pessoas.setId(ID);
		pessoas.setNome(NOME);
		pessoas.setCpf(CPF);
		return pessoas;
	}
}
