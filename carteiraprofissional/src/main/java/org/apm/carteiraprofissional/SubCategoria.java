package org.apm.carteiraprofissional;

import java.io.Serializable;

public class SubCategoria extends BaseModel implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;
	private String designacao;
	private Categoria categoria;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return designacao;
	}
	
	

}
