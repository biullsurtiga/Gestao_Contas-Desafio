package com.urtiga.gestaocontas.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "pessoas")
public class Pessoas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8945214961258154323L;

	private Long idPessoa;
	private String nome;
	private String cpf;
	private Date dataNascimento;
	
	private Date dataCriacao;
	private Date dataAtualizacao;
	private List<Contas> contas;
	
	public Pessoas() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "cpf", nullable = false)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "dataNascimento", nullable = false)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@OneToMany(mappedBy = "pessoas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Contas> getContas() {
		return contas;
	}

	public void setContas(List<Contas> contas) {
		this.contas = contas;
	}
	
	@Column(name = "dataCriacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Column(name = "dataAtualizacao", nullable = false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	
	@PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }
     
    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }

	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento
				+ ", dataCriacao=" + dataCriacao + "dataAtualizacao=" + dataAtualizacao +"]";
	}
	
	
}
