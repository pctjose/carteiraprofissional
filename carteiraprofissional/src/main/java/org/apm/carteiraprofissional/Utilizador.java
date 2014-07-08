package org.apm.carteiraprofissional;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Eurico.Jose: 06.02.2014, 15:51 Guarda os utilizadores do sistema
 * 
 */
@Entity
@Table(name = "utilizador")
public class Utilizador extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "nome")
	private String nome;
	@Column(name = "contacto")
	private String contacto;
	@Column(name = "email")
	private String email;
	@ManyToOne
	@JoinColumn(name = "grupo_id")
	private GrupoUtilizador grupo;
	@Column(name = "activo")
	private boolean activo;
	@Column(name = "username")
	private String userName;
	@Column(name = "password")
	private String senha;

	public Utilizador(String userName, String senha, boolean activo) {
		this.userName = userName;
		this.senha = senha;
		this.activo = activo;

	}

	public Utilizador() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public GrupoUtilizador getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoUtilizador grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return nome;
	}

	public String getNomeCompleto() {
		return toString();
	}

}
