package com.urtiga.gestaocontas.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.urtiga.gestaocontas.api.enums.FlagAtivo;
import com.urtiga.gestaocontas.api.enums.TipoConta;

@Entity
@Table(name = "contas")
public class Contas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1474070392386369226L;

	private Long idConta;
	private Float saldo;
	private Float limiteSaqueDiario;
	private TipoConta tipoConta;
	private FlagAtivo flagAtivo;
	private Pessoas pessoas;
	
	private Date dataCriacao;
	private Date dataAtualizacao;
	private List<Transacoes> transacoes;
	
	public Contas() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}

	@Column(name = "saldo", nullable = true)
	public Float getSaldo() {
		return saldo;
	}
	
	@Transient
	public Optional<Float> getSaldoOpt() {
		return Optional.ofNullable(saldo);
	}

	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}

	@Column(name = "limiteSaqueDiario", nullable = true)
	public Float getLimiteSaqueDiario() {
		return limiteSaqueDiario;
	}
	
	@Transient
	public Optional<Float> getLimiteSaqueDiarioOpt() {
		return Optional.ofNullable(limiteSaqueDiario);
	}

	public void setLimiteSaqueDiario(Float limiteSaqueDiario) {
		this.limiteSaqueDiario = limiteSaqueDiario;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoConta", nullable = false)
	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "flagAtivo", nullable = false)
	public FlagAtivo getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(FlagAtivo flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Pessoas getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
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


	@OneToMany(mappedBy = "contas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Transacoes> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacoes> transacoes) {
		this.transacoes = transacoes;
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
		return "Pessoa [idConta=" + idConta + ", saldo=" + saldo + ", limiteSaqueDiario=" + limiteSaqueDiario + ", tipoConta=" + tipoConta
				+ ", flagAtivo=" + flagAtivo + "dataCriacao=" + dataCriacao + "dataAtualizacao=" + dataAtualizacao
				+ ", id_Pessoa=" + pessoas +"]";
	}
	
}
