package org.apm.carteiraprofissional;

import java.io.Serializable;

/**
 * 
 * @author Eurico.Jose: 06.02.2014: 16:03
 * 
 */

public class Empregador extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String designacao;
	private String endereco;
	private String contactoFixo;
	private String contactoCell;
	private String contactoEmail;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getContactoFixo() {
		return contactoFixo;
	}

	public void setContactoFixo(String contactoFixo) {
		this.contactoFixo = contactoFixo;
	}

	public String getContactoCell() {
		return contactoCell;
	}

	public void setContactoCell(String contactoCell) {
		this.contactoCell = contactoCell;
	}

	public String getContactoEmail() {
		return contactoEmail;
	}

	public void setContactoEmail(String contactoEmail) {
		this.contactoEmail = contactoEmail;
	}

	@Override
	public String toString() {
		return designacao;
	}

}
