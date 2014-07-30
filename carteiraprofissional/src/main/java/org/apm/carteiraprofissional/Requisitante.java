package org.apm.carteiraprofissional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "requisitante")
public class Requisitante extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4135925580655381866L;

	// Demografico
	@Column(name = "nome")
	private String nome;
	@Column(name = "apelido")
	private String apelido;
	@Column(name = "sexo")
	private String sexo;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	@Column(name = "foto")
	private byte[] foto;
	@ManyToOne
	@JoinColumn(name = "escolaridade_id")
	private Escolaridade escolaridade;

	// Identificacao
	@Column(name = "numero_doc")
	private String numeroDoc;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emissao")
	private Date dataEmissao;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_validade")
	private Date dataValidade;
	@Column(name = "local_emissao")
	private String localEmissao;
	@Column(name = "copia_doc")
	private byte[] copiaDoc;
	@Column(name = "numero_nuit")
	private String numeroNuit;
	@ManyToOne
	@JoinColumn(name = "tipodoc_id")
	private TipoDocumento tipoDoc;

	// Endereco
	@ManyToOne
	@JoinColumn(name = "provincia_id")
	private Provincia provincia;
	@Column(name = "cidade")
	private String cidade;
	@Column(name = "endereco")
	private String endereco;

	// Contacto
	@Column(name = "contacto1")
	private String contacto1;
	@Column(name = "contacto2")
	private String contacto2;
	@Column(name = "email")
	private String email;
	@Column(name = "outro_contacto")
	private String outroContacto;

	// Membro
	@Column(name = "membro")
	private Boolean membro;
	@Column(name = "numero_membro")
	private String numeroMembro;

	// Ainda por discutir esta ideia de implementação:

	// private List<RequisicaoSubCategoria> requisicaoSubcategorias;
	@OneToMany(mappedBy = "requisitante", cascade = CascadeType.ALL)
	private List<Experiencia> experiencias;
	@OneToMany(mappedBy = "requisitante", cascade = CascadeType.ALL)
	private List<Formacao> formacoes;

	public Requisitante() {

		this.experiencias = new ArrayList<Experiencia>();
		this.formacoes = new ArrayList<Formacao>();
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getNumeroNuit() {
		return numeroNuit;
	}

	public void setNumeroNuit(String numeroNuit) {
		this.numeroNuit = numeroNuit;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getLocalEmissao() {
		return localEmissao;
	}

	public void setLocalEmissao(String localEmissao) {
		this.localEmissao = localEmissao;
	}

	public List<Experiencia> getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(List<Experiencia> experiencias) {
		this.experiencias = experiencias;
	}

	public List<Formacao> getFormacoes() {
		return formacoes;
	}

	public void setFormacoes(List<Formacao> formacoes) {
		this.formacoes = formacoes;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNumeroDoc() {
		return numeroDoc;
	}

	public void setNumeroDoc(String numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public byte[] getCopiaDoc() {
		return copiaDoc;
	}

	public void setCopiaDoc(byte[] copiaDoc) {
		this.copiaDoc = copiaDoc;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getContacto1() {
		return contacto1;
	}

	public void setContacto1(String contacto1) {
		this.contacto1 = contacto1;
	}

	public String getContacto2() {
		return contacto2;
	}

	public void setContacto2(String contacto2) {
		this.contacto2 = contacto2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOutroContacto() {
		return outroContacto;
	}

	public void setOutroContacto(String outroContacto) {
		this.outroContacto = outroContacto;
	}

	public Boolean getMembro() {
		return membro;
	}

	public void setMembro(Boolean membro) {
		this.membro = membro;
	}

	public String getNumeroMembro() {
		return numeroMembro;
	}

	public void setNumeroMembro(String numeroMembro) {
		this.numeroMembro = numeroMembro;
	}

	public TipoDocumento getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(TipoDocumento tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNomeCompleto() {
		return this.nome + " " + this.apelido;
	}

}
