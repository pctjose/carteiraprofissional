package org.apm.carteiraprofissional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author Eurico.Jose: 06.02.2014, 15:51 Guarda os utilizadores do sistema
 * 
 */
@Entity
@Table(name = "utilizador",uniqueConstraints={@UniqueConstraint(columnNames="username")})
public class Utilizador extends BaseModel implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;
	@Column(name = "nome")
	private String nome;
	@Column(name = "apelido")
	private String apelido;
	@Column(name = "contacto")
	private String contacto;
	@Column(name = "email")
	private String email;
	//@OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name="grupo_id")
	private GrupoUtilizador grupo;
	@Column(name = "activo")
	private boolean activo = true;
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

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
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

	public String getPassword() {
		
		return getSenha();
	}

	public String getUsername() {
		
		return getUserName();
	}

	public boolean isAccountNonExpired() {
		
		return true;
	}

	public boolean isAccountNonLocked() {
		
		return true;
	}

	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	public boolean isEnabled() {
		
		return isActivo();
	}

	public Collection<GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrupoUtilizador roles = this.getGrupo();
		authorities.add(new GrantedAuthorityImpl(roles.getAuthority()));
/*
		if(roles != null)
		{
			for (GrupoUtilizador role : roles) {
				//SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getDescricao());
				authorities.add(new GrantedAuthorityImpl(role.getAuthority()));
			}
		}*/
		return authorities;
	}

}
