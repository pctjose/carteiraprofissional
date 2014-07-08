/**
 * 
 */
package org.apm.carteiraprofissional;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Ezequiel Barreto
 * O Role pode ser ROLE_ADMIN, ROLE_USER
 */
@Entity
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id" )
	private Integer id;
	private String descricao;
	//private Set<Utilizador> utilizador;
	
	public Role(){
		
	}
	
	public Role(int id){
		this.id = id;
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/*public Set<Utilizador> getUtilizador() {
		return utilizador;
	}
	public void setUtilizador(Set<Utilizador> utilizador) {
		this.utilizador = utilizador;
	}*/
	/*@Override
	public String getAuthority() {
		return this.descricao;
	}*/
	
	public String toString(){
		return this.descricao;
	}
}
