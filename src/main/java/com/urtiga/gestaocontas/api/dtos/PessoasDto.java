package com.urtiga.gestaocontas.api.dtos;

public class PessoasDto {

	private Long id;
	private String nome;
	private String cpf;
	
	public PessoasDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public String toString() {
		return "PessoasDto [id=" + id + ", nome=" + nome + ", cpf=" + cpf + "]";
	}
}
