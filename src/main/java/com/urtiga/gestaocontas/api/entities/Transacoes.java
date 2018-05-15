package com.urtiga.gestaocontas.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "transacoes")
public class Transacoes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -207430903002385159L;

	private Long idTransacao;
	private Conta id_Contas;
	private Float valor;
	private Date dataTransacao;
	
	private Date dataCriacao;
	private Date dataAtualizacao;
	
	public Transacoes() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(Long idTransacao) {
		this.idTransacao = idTransacao;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Conta getId_Contas() {
		return id_Contas;
	}

	public void setId_Contas(Conta id_Contas) {
		this.id_Contas = id_Contas;
	}

	@Column(name = "valor", nullable = true)
	public Float getValor() {
		return valor;
	}
	
	@Transient
	public Optional<Float> getValorOpt() {
		return Optional.ofNullable(valor);
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataTransacao", nullable = false)
	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
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
		return "Lancamento [idTransacao=" + idTransacao + ", id_Contas=" + id_Contas + ", valor=" + valor + ", dataTransacao=" + dataTransacao
				+ ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao +"]";
	}
	
}
