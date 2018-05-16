package com.urtiga.gestaocontas.api.services;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.urtiga.gestaocontas.api.entities.Pessoas;
import com.urtiga.gestaocontas.api.repositories.PessoasRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PessoasServiceTest {

	@MockBean
	private PessoasRepository pessoasRepository;
	
	private PessoasService pessoasService;
	
	private static final String cpf = "08260580438";
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.pessoasRepository.findByCpf(Mockito.anyString())).willReturn(new Pessoas());
		BDDMockito.given(this.pessoasRepository.save(Mockito.any(Pessoas.class))).willReturn(new Pessoas());
	}
	
	@Test
	public void testBuscarPessoasPorCpf() {
		Optional<Pessoas> pessoas = this.pessoasService.buscarPorCpf(cpf);
		assertNotNull(pessoas);
	}
	
	@Test
	public void testPersistirPessoas() {
		Pessoas pessoas = this.pessoasService.persistir(new Pessoas());
		assertNotNull(pessoas);
	}
}
