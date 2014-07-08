package org.apm.carteiraprofissional;

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
@Table(name="requisitante_experiencia")
public class Experiencia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name="empregador")
	private String empregador;
	@Temporal(TemporalType.DATE)
	@Column(name="data_inicial")
	private Date dataInicial;
	@Temporal(TemporalType.DATE)
	@Column(name="data_final")
	private Date dataFinal;
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	@Column(name="funcao_exercida")
	private String funcaoExercida;
	@Column(name="experiencia_relevante")
	private String experienciaRelevante;
	@Column(name="actual")
	private Boolean actual;
	@Column(name="empregador_endereco")
	private String empregadorEndereco;
	@Column(name="empregador_contacto")
	private String empregadorContacto;
	@ManyToOne
	@JoinColumn(name = "requisitante_id")
	private Requisitante requisitante;
	@Column(name="uuid")
	private String uuid;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_registo")
	private Date dataRegisto;
	
	
	public String getActualString(){
		if(actual)
			return "SIM";
		return "NÃO";
	}
	

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpregadorEndereco() {
		return empregadorEndereco;
	}

	public void setEmpregadorEndereco(String empregadorEndereco) {
		this.empregadorEndereco = empregadorEndereco;
	}

	public String getEmpregadorContacto() {
		return empregadorContacto;
	}

	public void setEmpregadorContacto(String empregadorContacto) {
		this.empregadorContacto = empregadorContacto;
	}

	public String getEmpregador() {
		return empregador;
	}

	public void setEmpregador(String empregador) {
		this.empregador = empregador;
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

	

	public String getFuncaoExercida() {
		return funcaoExercida;
	}

	public void setFuncaoExercida(String funcaoExercida) {
		this.funcaoExercida = funcaoExercida;
	}

	public String getExperienciaRelevante() {
		return experienciaRelevante;
	}

	public void setExperienciaRelevante(String experienciaRelevante) {
		this.experienciaRelevante = experienciaRelevante;
	}

	public Boolean getActual() {
		return actual;
	}

	public void setActual(Boolean actual) {
		this.actual = actual;
	}
	
	

	public Requisitante getRequisitante() {
		return requisitante;
	}


	public void setRequisitante(Requisitante requisitante) {
		this.requisitante = requisitante;
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
		return  empregador;
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
		Experiencia other = (Experiencia) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	

}
