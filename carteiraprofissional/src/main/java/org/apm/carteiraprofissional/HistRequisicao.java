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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="hist_requisicao")
public class HistRequisicao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hist_requisicao_id")
	private Integer histRequisicaoId;
	@Column(name = "assinou_compromisso")
	private boolean assinouCompromisso;
	@Column(name = "concorda_termos")
	private boolean concordaTermos;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_requisicao")
	private Date dataRequisiao;
	@Column(name = "local_requisicao")
	private String localRequisicao;
	@Column(name = "aceite")
	private boolean aceite;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_aceite")
	private Date dataAceite;
	@Column(name = "justificacao_aceite")
	private String justificacaoAceitacao;
	@ManyToOne
	@JoinColumn(name = "hist_requisitante_id")
	private HistRequisitante histRequisitante;
	@Column(name = "numero_requisicao")
	private String numeroRequisicao;
	@Column(name = "lock_edit")
	private boolean lockEdit;
	@Column(name = "completa")
	private boolean completa;
	@ManyToOne
	@JoinColumn(name = "aceite_por")
	private Utilizador aceitePor;
	@Column(name = "uuid")
	private String uuid;
	@Column(name="tem_carteira")
	private boolean temCarteira;
	
	

	

	public Integer getHistRequisicaoId() {
		return histRequisicaoId;
	}

	public void setHistRequisicaoId(Integer histRequisicaoId) {
		this.histRequisicaoId = histRequisicaoId;
	}

	public HistRequisitante getHistRequisitante() {
		return histRequisitante;
	}

	public void setHistRequisitante(HistRequisitante histRequisitante) {
		this.histRequisitante = histRequisitante;
	}

	@Deprecated
	public boolean getAssinouCompromisso() {
		return isAssinouCompromisso();
	}
	
	public boolean isAssinouCompromisso() {
		return assinouCompromisso;
	}

	public void setAssinouCompromisso(boolean assinouCompromisso) {
		this.assinouCompromisso = assinouCompromisso;
	}

	@Deprecated
	public boolean getConcordaTermos() {
		return isConcordaTermos();
	}
	
	public boolean isConcordaTermos() {
		return concordaTermos;
	}

	public void setConcordaTermos(boolean concordaTermos) {
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

	@Deprecated
	public boolean getAceite() {
		return isAceite();
	}
	
	public boolean isAceite() {
		return aceite;
	}

	public void setAceite(boolean aceite) {
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

	

	public String getNumeroRequisicao() {
		return numeroRequisicao;
	}

	public void setNumeroRequisicao(String numeroRequisicao) {
		this.numeroRequisicao = numeroRequisicao;
	}

	@Deprecated
	public boolean getLockEdit() {
		return isLockEdit();
	}
	
	public boolean isLockEdit() {
		return lockEdit;
	}

	public void setLockEdit(boolean lockEdit) {
		this.lockEdit = lockEdit;
	}

	
	

	public boolean isTemCarteira() {
		return temCarteira;
	}

	public void setTemCarteira(boolean temCarteira) {
		this.temCarteira = temCarteira;
	}
	
	@Deprecated
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
		return histRequisitante.toString() + " [" + numeroRequisicao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((histRequisicaoId == null) ? 0 : histRequisicaoId.hashCode());
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
		HistRequisicao other = (HistRequisicao) obj;
		if (histRequisicaoId == null) {
			if (other.histRequisicaoId != null)
				return false;
		} else if (!histRequisicaoId.equals(other.histRequisicaoId))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	public String getAceiteString() {
		if (isAceite())
			return "SIM";
		return "NÃO";
	}
	
	@Deprecated
	public boolean getCompleta(){
		return isCompleta();
	}

	public String getCompletaString() {
		if (isCompleta())
			return "SIM";
		return "NÃO";

	}

}
