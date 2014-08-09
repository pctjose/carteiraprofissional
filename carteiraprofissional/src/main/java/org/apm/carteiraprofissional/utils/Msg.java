package org.apm.carteiraprofissional.utils;

import java.io.Serializable;

/**
 * @author Helio.Machabane
 * <p>Classe que guarda as mensagens de erro validadas nos formularios</p>
 */
public class Msg implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String txt;

	private String hlp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getHlp() {
		return hlp;
	}

	public void setHlp(String hlp) {
		this.hlp = hlp;
	}
}
