package com.urtiga.gestaocontas.api.dtos;

import java.util.Optional;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ContasDto {

	private Long id;
	private Optional<String> saldo = Optional.empty();
	private Optional<String> limiteSaqueDiario = Optional.empty();
	private String tipoConta;
	
	public ContasDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@NotEmpty(message="Tipo Conta não pode ser vazio.")
	@Length(min = 3, max = 200, message="Tipo de conta deve conter entre 3e 200 caracteres.")
	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	@Override
	public String toString() {
		return "ContasDto [id=" + id + ", saldo=" + saldo + ", limiteSaqueDiario=" + limiteSaqueDiario 
				+ ", tipoConta=" + tipoConta + "]";
	}
}
