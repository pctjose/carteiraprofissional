package org.apm.carteiraprofissional;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="hist_carteira")
public class HistCarteira implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hist_carteira_id")
	private Integer histCarteiraId;
	
	@ManyToOne
	@JoinColumn(name = "hist_requisicao_id")
	private HistRequisicao histRequisicao;
	@Column(name = "data_emissao")
	private Date dataEmissao;
	@Column(name = "data_validade")
	private Date dataValidade;
	@Column(name = "numero_carteira")
	private String numeroCarteira;
	@Column(name = "pdf417")
	private byte[] pdf417;
	@Column(name = "emitida")
	private boolean emitida;
	@Column(name = "tamplate")
	private byte[] tamplate;
	@Column(name = "valor_cobrado")
	private Double valorCobrado;
	@Column(name = "nume_doc_pagamento")
	private String numeroDocPagamento;
	@ManyToOne
	@JoinColumn(name = "forma_pagamento")
	private FormaPagamento formaPagamento;
	@Column(name = "data_pagamento")
	private Date dataPagamento;
	@Column(name = "enviar_emissao")
	private boolean enviarEmissao;

	
	
	
	public Integer getHistCarteiraId() {
		return histCarteiraId;
	}

	public void setHistCarteiraId(Integer histCarteiraId) {
		this.histCarteiraId = histCarteiraId;
	}

	public HistRequisicao getHistRequisicao() {
		return histRequisicao;
	}

	public void setHistRequisicao(HistRequisicao histRequisicao) {
		this.histRequisicao = histRequisicao;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getNumeroCarteira() {
		return numeroCarteira;
	}

	public void setNumeroCarteira(String numeroCarteira) {
		this.numeroCarteira = numeroCarteira;
	}

	public byte[] getPdf417() {
		return pdf417;
	}

	public void setPdf417(byte[] pdf417) {
		this.pdf417 = pdf417;
	}	

	public boolean isEmitida() {
		return emitida;
	}

	public void setEmitida(boolean emitida) {
		this.emitida = emitida;
	}
	
	public boolean getEmitida(){
		return isEmitida();
	}

	public byte[] getTamplate() {
		return tamplate;
	}

	public void setTamplate(byte[] tamplate) {
		this.tamplate = tamplate;
	}

	public Double getValorCobrado() {
		return valorCobrado;
	}

	public void setValorCobrado(Double valorCobrado) {
		this.valorCobrado = valorCobrado;
	}

	public String getNumeroDocPagamento() {
		return numeroDocPagamento;
	}

	public void setNumeroDocPagamento(String numeroDocPagamento) {
		this.numeroDocPagamento = numeroDocPagamento;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Override
	public String toString() {
		return histRequisicao.getHistRequisitante().toString();
	}

	public boolean isEnviarEmissao() {
		return enviarEmissao;
	}

	public void setEnviarEmissao(boolean enviarEmissao) {
		this.enviarEmissao = enviarEmissao;
	}

	public boolean getEnviarEmissao() {
		return isEnviarEmissao();
	}
}
