package com.urtiga.gestaocontas.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.urtiga.gestaocontas.api.entities.Pessoas;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PessoasRepositoryTest {

	@Autowired
	private PessoasRepository pessoaRepository;
	
	private static final String CPF = "08260580438";
	
	@Before
	public void setUp() throws Exception {
		Pessoas pessoa = new Pessoas();
		pessoa.setNome("Pessoa de exemplo");
		pessoa.setCpf(CPF);
		this.pessoaRepository.save(pessoa);
	}
	
	@After
	public final void tearDown() {
		this.pessoaRepository.deleteAll();
	}
	@Test
	public void testBuscarPorCpf() {
		Pessoas pessoas = this.pessoaRepository.findByCpf(CPF);
		assertEquals(CPF, pessoas.getCpf());
	}
}
