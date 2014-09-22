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
@Table(name="requisitante_formacao")
public class Formacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7822344481485841L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name="instituicao")
	private String instituicao;
	@Column(name="ano")
	private Integer ano;
	@Column(name="localizacao")
	private String localizacao;
	@ManyToOne
	@JoinColumn(name = "grauobtido_id")
	private Escolaridade grauObtido;
	@ManyToOne
	@JoinColumn(name = "requisitante_id")
	private Requisitante requisitante;
	@Column(name="uuid")
	private String uuid;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_registo")
	private Date dataRegisto;
	@Column(name="lock_edit")
	private boolean lockEdit;
	
	
	
	

	public boolean isLockEdit() {
		return lockEdit;
	}

	public void setLockEdit(boolean lockEdit) {
		this.lockEdit = lockEdit;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Escolaridade getGrauObtido() {
		return grauObtido;
	}

	public void setGrauObtido(Escolaridade grauObtido) {
		this.grauObtido = grauObtido;
	}

	

	public Requisitante getRequisitante() {
		return requisitante;
	}

	public void setRequisitante(Requisitante requisitante) {
		this.requisitante = requisitante;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	

	public Date getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(Date dataRegisto) {
		this.dataRegisto = dataRegisto;
	}

	@Override
	public String toString() {
		return instituicao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Formacao other = (Formacao) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

}
