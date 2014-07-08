package org.apm.carteiraprofissional;

import java.io.Serializable;
import java.util.Date;

public class CartaoProfissional implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer membroId;
    private Membro membro;
    private Empregador empregador;
    private String cargo;
    private String numeroCartao;
    private Date dataEmissao;
    private Date dataValidade;
    private String localEmissao;
    private byte[] codigoBarra;
    private String uuid;
	public Integer getMembroId() {
		return membroId;
	}
	public void setMembroId(Integer membroId) {
		this.membroId = membroId;
	}
	public Membro getMembro() {
		return membro;
	}
	public void setMembro(Membro membro) {
		this.membro = membro;
	}
	public Empregador getEmpregador() {
		return empregador;
	}
	public void setEmpregador(Empregador empregador) {
		this.empregador = empregador;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
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
	public byte[] getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(byte[] codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
    
    
    
    
    
}
