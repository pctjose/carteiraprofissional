package org.apm.carteiraprofissional;

import java.io.Serializable;
import java.util.Date;

public class RequisicaoSubCategoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8117725453035933602L;

	private Integer requisicaoSubcategoriaId;
	private String uuid;
	private SubCategoria subCategoria;
	private Date dataInicial;
	private Date dataFinal;
	private Boolean actual;
	private byte[] doc;
	private Requisitante requisicao;	

	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getRequisicaoSubcategoriaId() {
		return requisicaoSubcategoriaId;
	}

	public void setRequisicaoSubcategoriaId(Integer requisicaoSubcategoriaId) {
		this.requisicaoSubcategoriaId = requisicaoSubcategoriaId;
	}

	public Requisitante getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisitante requisicao) {
		this.requisicao = requisicao;
	}

	public SubCategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Boolean getActual() {
		return actual;
	}

	public void setActual(Boolean actual) {
		this.actual = actual;
	}

	public byte[] getDoc() {
		return doc;
	}

	public void setDoc(byte[] doc) {
		this.doc = doc;
	}

	@Override
	public String toString() {
		return subCategoria.getDesignacao();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((requisicao == null) ? 0 : requisicao.hashCode());
		result = prime * result
				+ ((subCategoria == null) ? 0 : subCategoria.hashCode());
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
		RequisicaoSubCategoria other = (RequisicaoSubCategoria) obj;
		if (requisicao == null) {
			if (other.requisicao != null)
				return false;
		} else if (!requisicao.equals(other.requisicao))
			return false;
		if (subCategoria == null) {
			if (other.subCategoria != null)
				return false;
		} else if (!subCategoria.equals(other.subCategoria))
			return false;
		return true;
	}
	
	
	

}
