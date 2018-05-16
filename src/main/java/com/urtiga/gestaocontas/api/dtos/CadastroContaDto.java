package com.urtiga.gestaocontas.api.dtos;

import java.util.Date;
import java.util.Optional;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

public class CadastroContaDto {

	private Long id;
	private String nome;
	private String cpf;
	private Date data;
	private Optional<String> saldo = Optional.empty();
	private Optional<String> limiteSaqueDiario = Optional.empty();
	
	public CadastroContaDto(){
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}

	@NotEmpty(message="Nome não pode ser vazio.")
	@Length(min=3, max=200, message="Nome deve conter entre 3 e 200 caracteres.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty(message = "CPF não pode ser vazio.")
	@CPF(message="CPF inválido")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Optional<String> getSaldo() {
		return saldo;
	}

	public void setSaldo(Optional<String> saldo) {
		this.saldo = saldo;
	}

	public Optional<String> getLimiteSaqueDiario() {
		return limiteSaqueDiario;
	}

	public void setLimiteSaqueDiario(Optional<String> limiteSaqueDiario) {
		this.limiteSaqueDiario = limiteSaqueDiario;
	}
	
	@Override
	public String toString() {
		return "ContasDtop [id=" + id + ", nome=" + nome + ",  cpf=" + cpf
				+ ", saldo=" + saldo + ", limiteSaqueDiario=" + limiteSaqueDiario + ", data=" + data + "]";
	}
	
}
