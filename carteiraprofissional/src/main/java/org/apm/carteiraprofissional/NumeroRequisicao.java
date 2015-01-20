package org.apm.carteiraprofissional;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "numero_requisicao")
public class NumeroRequisicao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4217425809786468578L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "numero_requisicao_id")
	private Integer id;

	@Column(name = "ano")
	private Integer ano;
	@Column(name = "numero")
	private Integer numero;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_emissao")
	private Date dataEmissao;
	@Column(name = "uuid")
	private String uuid;
	@Column(name="nome_real")
	private String numeroReal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}	

	public String getNumeroReal() {
		return numeroReal;
	}

	public void setNumeroReal(String numeroReal) {
		this.numeroReal = numeroReal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		NumeroRequisicao other = (NumeroRequisicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ano.toString() + numero.toString();
	}

}
