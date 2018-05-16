package com.urtiga.gestaocontas.api.repositories;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.urtiga.gestaocontas.api.entities.Contas;
import com.urtiga.gestaocontas.api.entities.Pessoas;
import com.urtiga.gestaocontas.api.entities.Transacoes;
import com.urtiga.gestaocontas.api.enums.FlagAtivo;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TransacoesRepositoryTest {

	@Autowired
	private TransacoesRepository transacoesRepository;
	
	@Autowired
	private ContasRepository contasRepository;
	
	@Autowired
	private PessoasRepository pessoasRepository;
	
	private Long contasId;
	
	@Before
	public void setUp() throws Exception{
		Pessoas pessoas = this.pessoasRepository.save(obterDadosPessoas());
		Contas contas = this.contasRepository.save(obterDadosContas(pessoas));
		
		this.contasId = contas.getId();
		
		this.transacoesRepository.save(obterDadosTransacoes(contas));
		this.transacoesRepository.save(obterDadosTransacoes(contas));
	}
	
	@After
	public void tearDown() throws Exception{
		this.pessoasRepository.deleteAll();
	}
	
	@Test
	public void testBuscarTransacoesPorContasId() {
		List<Transacoes> transacoes = this.transacoesRepository.findByContasId(contasId);
		assertEquals(2, transacoes.size());
	}
	
	@Test
	public void testBuscarTransacoesPorContasIdPaginado() {
		PageRequest page = new PageRequest(0, 10);
		Page<Transacoes> transacoes = this.transacoesRepository.findByContasId(contasId, page);
		
		assertEquals(2, transacoes.getTotalElements());
	}
	
	private Transacoes obterDadosTransacoes(Contas contas) {
		Transacoes transacoes = new Transacoes();
		transacoes.setValor((float) 250.00);
		transacoes.setContas(contas);
		return transacoes;
	}
	
	private Contas obterDadosContas(Pessoas pessoas) throws NoSuchAlgorithmException{
		Contas contas = new Contas();
		contas.setSaldo((float) 1000.00);
		contas.setLimiteSaqueDiario((float) 200.00);
		contas.setFlagAtivo(FlagAtivo.Ativo);
		contas.setTipoConta("Normal");
		contas.setPessoas(pessoas);
		return contas;
	}

	private Pessoas obterDadosPessoas() {
		Pessoas pessoas = new Pessoas();
		pessoas.setNome("Exmplo de pessoas");
		pessoas.setCpf("51463645000100");
		return pessoas;
	}
}
