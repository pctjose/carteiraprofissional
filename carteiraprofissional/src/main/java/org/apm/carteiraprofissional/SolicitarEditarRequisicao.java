package org.apm.carteiraprofissional;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class SolicitarEditarRequisicao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "solicitacao_id")
	private Integer solicitacaoId;
	@Column(name = "motivo")
	private String motivo;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "data_solicitacao")
	private Date dataSolicitacao;
	@ManyToOne
	@JoinColumn(name = "requisicao_id")
	private Requisicao requisicao;
	@Column(name = "tratada")
	private boolean tratada;
	@Column(name = "uuid")
	private String uuid;

	public Integer getSolicitacaoId() {
		return solicitacaoId;
	}

	public void setSolicitacaoId(Integer solicitacaoId) {
		this.solicitacaoId = solicitacaoId;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	public boolean isTratada() {
		return tratada;
	}

	public void setTratada(boolean tratada) {
		this.tratada = tratada;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}