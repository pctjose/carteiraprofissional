package org.apm.carteiraprofissional;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="solicitar_editar_carteira")
public class SolicitarEditarCarteira implements Serializable {

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
	@JoinColumn(name = "carteira_id")
	private Carteira carteira;
	@Column(name = "tratada")
	private boolean tratada;
	@Column(name = "uuid")
	private String uuid;
	@Column(name="autorizada")
	private boolean autorizada;
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="autorizado_por")
	private Utilizador autorizadaPor;
	@Column(name="data_autorizacao")
	private Date dataAutorizacao;

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

	

	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
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

	public boolean isAutorizada() {
		return autorizada;
	}

	public void setAutorizada(boolean autorizada) {
		this.autorizada = autorizada;
	}

	public Utilizador getAutorizadaPor() {
		return autorizadaPor;
	}

	public void setAutorizadaPor(Utilizador autorizadaPor) {
		this.autorizadaPor = autorizadaPor;
	}

	public Date getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(Date dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}
	
	

}
