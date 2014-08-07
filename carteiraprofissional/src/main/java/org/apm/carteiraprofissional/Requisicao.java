package org.apm.carteiraprofissional;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "requisicao")
public class Requisicao {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "requisicao_id")
	private Integer requisicaoId;
	@Column(name = "assinou_compromisso")
	private Boolean assinouCompromisso=Boolean.FALSE;
	@Column(name = "concorda_termos")
	private Boolean concordaTermos=Boolean.FALSE;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_requisicao")
	private Date dataRequisiao;
	@Column(name = "local_requisicao")
	private String localRequisicao;
	@Column(name = "aceite")
	private Boolean aceite = Boolean.FALSE;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_aceite")
	private Date dataAceite;
	@Column(name = "justificacao_aceite")
	private String justificacaoAceitacao;
	@ManyToOne
	@JoinColumn(name = "requisitante_id")
	private Requisitante requisitante;
	@Column(name = "numero_requisicao")
	private String numeroRequisicao;
	@Column(name = "lock_edit")
	private Boolean lockEdit=Boolean.FALSE;
	@Column(name = "completa")
	private boolean completa;
	@ManyToOne
	@JoinColumn(name = "aceite_por")
	private Utilizador aceitePor;
	@Column(name = "uuid")
	private String uuid;
	@Column(name="tem_carteira")
	private boolean temCarteira;
	
	

	public Integer getRequisicaoId() {
		return requisicaoId;
	}

	public void setRequisicaoId(Integer requisicaoId) {
		this.requisicaoId = requisicaoId;
	}

	public Boolean getAssinouCompromisso() {
		return assinouCompromisso;
	}

	public void setAssinouCompromisso(Boolean assinouCompromisso) {
		this.assinouCompromisso = assinouCompromisso;
	}

	public Boolean getConcordaTermos() {
		return concordaTermos;
	}

	public void setConcordaTermos(Boolean concordaTermos) {
		this.concordaTermos = concordaTermos;
	}

	public Date getDataRequisiao() {
		return dataRequisiao;
	}

	public void setDataRequisiao(Date dataRequisiao) {
		this.dataRequisiao = dataRequisiao;
	}

	public String getLocalRequisicao() {
		return localRequisicao;
	}

	public void setLocalRequisicao(String localRequisicao) {
		this.localRequisicao = localRequisicao;
	}

	public Boolean getAceite() {
		return aceite;
	}

	public void setAceite(Boolean aceite) {
		this.aceite = aceite;
	}

	public Date getDataAceite() {
		return dataAceite;
	}

	public void setDataAceite(Date dataAceite) {
		this.dataAceite = dataAceite;
	}

	public String getJustificacaoAceitacao() {
		return justificacaoAceitacao;
	}

	public void setJustificacaoAceitacao(String justificacaoAceitacao) {
		this.justificacaoAceitacao = justificacaoAceitacao;
	}

	public Requisitante getRequisitante() {
		return requisitante;
	}

	public void setRequisitante(Requisitante requisitante) {
		this.requisitante = requisitante;
	}

	public String getNumeroRequisicao() {
		return numeroRequisicao;
	}

	public void setNumeroRequisicao(String numeroRequisicao) {
		this.numeroRequisicao = numeroRequisicao;
	}

	public Boolean getLockEdit() {
		return lockEdit;
	}

	public void setLockEdit(Boolean lockEdit) {
		this.lockEdit = lockEdit;
	}

	
	

	public boolean isTemCarteira() {
		return temCarteira;
	}

	public void setTemCarteira(boolean temCarteira) {
		this.temCarteira = temCarteira;
	}
	
	public boolean getTemCarteira(){
		return isTemCarteira();
	}

	public boolean isCompleta() {
		return completa;
	}

	public void setCompleta(boolean completa) {
		this.completa = completa;
	}

	public Utilizador getAceitePor() {
		return aceitePor;
	}

	public void setAceitePor(Utilizador aceitePor) {
		this.aceitePor = aceitePor;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return requisitante.toString() + " [" + numeroRequisicao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((requisicaoId == null) ? 0 : requisicaoId.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Requisicao other = (Requisicao) obj;
		if (requisicaoId == null) {
			if (other.requisicaoId != null)
				return false;
		} else if (!requisicaoId.equals(other.requisicaoId))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	public String getAceiteString() {
		if (getAceite())
			return "SIM";
		return "NÃO";
	}
	
	public boolean getCompleta(){
		return isCompleta();
	}

	public String getCompletaString() {
		if (getCompleta())
			return "SIM";
		return "NÃO";

	}

}
