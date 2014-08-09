package org.apm.carteiraprofissional;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id" )
	private Integer id=null;
	
	@Column(name="uuid",unique=true,updatable=false)
    private String uuid;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_criacao")
    private Date dataCriacao;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="criado_por")
    private Utilizador criadoPor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_alteracao")
    private Date dataAlteracao;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="alterado_por")
    private Utilizador alteradoPor;
	
	@Column(name="anulado")
    private Boolean anulado=Boolean.FALSE;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_anulado")
    private Date dataAnulado;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="anulado_por")
    private Utilizador anuladoPor;
	
	@Column(name="motivo_anulacao")
    private String motivoAnulacao;
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Utilizador getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Utilizador criadoPor) {
        this.criadoPor = criadoPor;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Utilizador getAlteradoPor() {
        return alteradoPor;
    }

    public void setAlteradoPor(Utilizador alteradoPor) {
        this.alteradoPor = alteradoPor;
    }

    public Boolean isAnulado() {
        return anulado;
    }
    
    public Boolean getAnulado(){
    	return isAnulado();
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public Date getDataAnulado() {
        return dataAnulado;
    }

    public void setDataAnulado(Date dataAnulado) {
        this.dataAnulado = dataAnulado;
    }

    public Utilizador getAnuladoPor() {
        return anuladoPor;
    }

    public void setAnuladoPor(Utilizador anuladoPor) {
        this.anuladoPor = anuladoPor;
    }

    public String getMotivoAnulacao() {
        return motivoAnulacao;
    }

    public void setMotivoAnulacao(String motivoAnulacao) {
        this.motivoAnulacao = motivoAnulacao;
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
		BaseModel other = (BaseModel) obj;
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

    

}
