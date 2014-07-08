package org.apm.carteiraprofissional;

import java.io.Serializable;
import java.text.Format;
import java.util.Date;
import java.util.List;

public class Membro extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Demografico
	private String nome;
	private String apelido;
	private String sexo;
	private Date dataNascimento;
	private byte[] foto;
	
	//Identificacao
	private String numeroBi;
	private Date dataEmissao;
	private Date dataValidade;
	private String localEmissao;
	private byte[] copiaBI;
	private String numeroNuit;	
	
	//Endereco&Contacto
	private String requisitanteEndereco;
	private String requisitanteContacto1;
	private String requisitanteContacto2;
	private String requisitanteEmail;
	private String requisitanteOutroContacto;
	
	
	//Empregador
	private String entidadeEmpregador;
	private String funcaoExercida;
	private SubCategoria subCategoria;
	private String enderecoEmpregador;
	private String contactoEmpregadorFixo;
	private String contactoEmpregadorMovel;
	private String contactoEmpregadorFax;
	private String emailEmpregador;
	
	
		
	private Escolaridade escolaridade;	
	private CartaoProfissional cartao;
	
	//Outros Metadados
	private Boolean assinouCompromisso;
	private Boolean concordaTermos;
	private Date dataRequisiao;
	private String localRequisicao;
	private Boolean analisada;
	private Boolean aceite;
	
	
	//Ainda por discutir esta ideia de implementação:
	
	
	private List<RequisicaoSubCategoria> membroSubcategorias;
	private List<Experiencia> experiencias;
	private List<Format>formacoes;

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

	

	

	public String getNumeroBi() {
		return numeroBi;
	}

	public void setNumeroBi(String numeroBi) {
		this.numeroBi = numeroBi;
	}	

	
	

	

	public String getRequisitanteOutroContacto() {
		return requisitanteOutroContacto;
	}

	public void setRequisitanteOutroContacto(String requisitanteOutroContacto) {
		this.requisitanteOutroContacto = requisitanteOutroContacto;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public SubCategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	
	public CartaoProfissional getCartao() {
		return cartao;
	}

	public void setCartao(CartaoProfissional cartao) {
		this.cartao = cartao;
	}

	public String getNumeroNuit() {
		return numeroNuit;
	}

	public void setNumeroNuit(String numeroNuit) {
		this.numeroNuit = numeroNuit;
	}

	

	public List<RequisicaoSubCategoria> getMembroSubcategorias() {
		return membroSubcategorias;
	}

	public void setMembroSubcategorias(List<RequisicaoSubCategoria> membroSubcategorias) {
		this.membroSubcategorias = membroSubcategorias;
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

	public byte[] getCopiaBI() {
		return copiaBI;
	}

	public void setCopiaBI(byte[] copiaBI) {
		this.copiaBI = copiaBI;
	}

	public String getRequisitanteEndereco() {
		return requisitanteEndereco;
	}

	public void setRequisitanteEndereco(String requisitanteEndereco) {
		this.requisitanteEndereco = requisitanteEndereco;
	}

	public String getRequisitanteContacto1() {
		return requisitanteContacto1;
	}

	public void setRequisitanteContacto1(String requisitanteContacto1) {
		this.requisitanteContacto1 = requisitanteContacto1;
	}

	public String getRequisitanteContacto2() {
		return requisitanteContacto2;
	}

	public void setRequisitanteContacto2(String requisitanteContacto2) {
		this.requisitanteContacto2 = requisitanteContacto2;
	}

	public String getRequisitanteEmail() {
		return requisitanteEmail;
	}

	public void setRequisitanteEmail(String requisitanteEmail) {
		this.requisitanteEmail = requisitanteEmail;
	}

	public String getEntidadeEmpregador() {
		return entidadeEmpregador;
	}

	public void setEntidadeEmpregador(String entidadeEmpregador) {
		this.entidadeEmpregador = entidadeEmpregador;
	}

	public String getFuncaoExercida() {
		return funcaoExercida;
	}

	public void setFuncaoExercida(String funcaoExercida) {
		this.funcaoExercida = funcaoExercida;
	}

	public String getEnderecoEmpregador() {
		return enderecoEmpregador;
	}

	public void setEnderecoEmpregador(String enderecoEmpregador) {
		this.enderecoEmpregador = enderecoEmpregador;
	}

	public String getContactoEmpregadorFixo() {
		return contactoEmpregadorFixo;
	}

	public void setContactoEmpregadorFixo(String contactoEmpregadorFixo) {
		this.contactoEmpregadorFixo = contactoEmpregadorFixo;
	}

	public String getContactoEmpregadorMovel() {
		return contactoEmpregadorMovel;
	}

	public void setContactoEmpregadorMovel(String contactoEmpregadorMovel) {
		this.contactoEmpregadorMovel = contactoEmpregadorMovel;
	}

	public String getContactoEmpregadorFax() {
		return contactoEmpregadorFax;
	}

	public void setContactoEmpregadorFax(String contactoEmpregadorFax) {
		this.contactoEmpregadorFax = contactoEmpregadorFax;
	}

	public String getEmailEmpregador() {
		return emailEmpregador;
	}

	public void setEmailEmpregador(String emailEmpregador) {
		this.emailEmpregador = emailEmpregador;
	}

	public Boolean getAssinouCompromisso() {
		return assinouCompromisso;
	}

	public void setAssinouCompromisso(Boolean assinouCompromisso) {
		this.assinouCompromisso = assinouCompromisso;
	}

	public Boolean getConcordaTermos() {
		return concordaTermos;
	}

	public void setConcordaTermos(Boolean concordaTermos) {
		this.concordaTermos = concordaTermos;
	}

	public Date getDataRequisiao() {
		return dataRequisiao;
	}

	public void setDataRequisiao(Date dataRequisiao) {
		this.dataRequisiao = dataRequisiao;
	}

	public String getLocalRequisicao() {
		return localRequisicao;
	}

	public void setLocalRequisicao(String localRequisicao) {
		this.localRequisicao = localRequisicao;
	}

	public Boolean getAnalisada() {
		return analisada;
	}

	public void setAnalisada(Boolean analisada) {
		this.analisada = analisada;
	}

	public Boolean getAceite() {
		return aceite;
	}

	public void setAceite(Boolean aceite) {
		this.aceite = aceite;
	}

	public List<Experiencia> getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(List<Experiencia> experiencias) {
		this.experiencias = experiencias;
	}

	public List<Format> getFormacoes() {
		return formacoes;
	}

	public void setFormacoes(List<Format> formacoes) {
		this.formacoes = formacoes;
	}
	
	
	
	
	
	
	

	
	
	

}
