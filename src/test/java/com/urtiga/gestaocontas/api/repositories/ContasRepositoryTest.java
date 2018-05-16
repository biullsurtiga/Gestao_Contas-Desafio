package com.urtiga.gestaocontas.api.repositories;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.urtiga.gestaocontas.api.entities.Contas;
import com.urtiga.gestaocontas.api.entities.Pessoas;
import com.urtiga.gestaocontas.api.enums.FlagAtivo;
import com.urtiga.gestaocontas.api.enums.TipoConta;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ContasRepositoryTest {

	@Autowired
	private ContasRepository contasRepository;
	
	@Autowired
	private PessoasRepository pessoasRepository;
	
	private static final FlagAtivo flagAtivo = FlagAtivo.Ativo;
	
	@Before
	public void setUp() throws Exception {
		Pessoas pessoas = this.pessoasRepository.save(obterDadosPessoas());
		this.contasRepository.save(obterDadosContas(pessoas));
	}
	
	@After
	public final void tearDown() {
		this.pessoasRepository.deleteAll();
	}
	
	@Test
	public void testBuscarContaPorFlagAtivo() {
		Contas contas = this.contasRepository.findbyflagAtivo(flagAtivo);
		assertEquals(flagAtivo, contas.getFlagAtivo());
	}

	private Contas obterDadosContas(Pessoas pessoas) throws NoSuchAlgorithmException{
		Contas contas = new Contas();
		contas.setSaldo((float) 1000.00);
		contas.setLimiteSaqueDiario((float) 200.00);
		contas.setFlagAtivo(flagAtivo);
		contas.setTipoConta(TipoConta.Conta_Corrente);
		contas.setPessoas(pessoas);
		return contas;
	}

	private Pessoas obterDadosPessoas() {
		Pessoas pessoas = new Pessoas();
		pessoas.setNome("Exemplo de pessoas");
		pessoas.setCpf("51463645000100");
		return pessoas;
	}
}
