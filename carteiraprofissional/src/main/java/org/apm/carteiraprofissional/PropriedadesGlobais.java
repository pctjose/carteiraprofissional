package org.apm.carteiraprofissional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "propriedade_global")
public class PropriedadesGlobais {
	@Id
	@Column(name = "propriedade")
	private String propriedade;
	@Column(name = "valor")
	private String valor;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "uuid")
	private String uuid;
	@Column(name="valor2")
	private String valor2;
	
	

	public String getValor2() {
		return valor2;
	}

	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}

	public String getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return getPropriedade();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((propriedade == null) ? 0 : propriedade.hashCode());
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
		PropriedadesGlobais other = (PropriedadesGlobais) obj;
		if (propriedade == null) {
			if (other.propriedade != null)
				return false;
		} else if (!propriedade.equals(other.propriedade))
			return false;
		return true;
	}

}
