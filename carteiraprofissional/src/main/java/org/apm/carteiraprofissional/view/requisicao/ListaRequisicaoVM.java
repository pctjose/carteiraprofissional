package org.apm.carteiraprofissional.view.requisicao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Utilizador;
import org.apm.carteiraprofissional.service.RequisicaoService;
import org.apm.carteiraprofissional.utils.BarcodeUtil;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.WireVariable;

public class ListaRequisicaoVM {

	private Requisicao selectedItem;
	private List<Requisicao> listaRequisicoes;
	private Utilizador requisitante;
	
	private String numeroRequisicao;
	private String nomeRequisitante;
	private String apelidoRequisitante;
	private Date dataRequisicao1;
	private Date dataRequisicao2;
	
	@WireVariable
	private RequisicaoService requisicaoService;
	
	

	public String getNumeroRequisicao() {
		return numeroRequisicao;
	}

	public void setNumeroRequisicao(String numeroRequisicao) {
		this.numeroRequisicao = numeroRequisicao;
	}

	public String getNomeRequisitante() {
		return nomeRequisitante;
	}

	public void setNomeRequisitante(String nomeRequisitante) {
		this.nomeRequisitante = nomeRequisitante;
	}

	public String getApelidoRequisitante() {
		return apelidoRequisitante;
	}

	public void setApelidoRequisitante(String apelidoRequisitante) {
		this.apelidoRequisitante = apelidoRequisitante;
	}

	public Date getDataRequisicao1() {
		return dataRequisicao1;
	}

	public void setDataRequisicao1(Date dataRequisicao1) {
		this.dataRequisicao1 = dataRequisicao1;
	}

	public Date getDataRequisicao2() {
		return dataRequisicao2;
	}

	public void setDataRequisicao2(Date dataRequisicao2) {
		this.dataRequisicao2 = dataRequisicao2;
	}
	
	

	

	public Requisicao getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Requisicao selectedItem) {
		this.selectedItem = selectedItem;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws Exception {
		Selectors.wireComponents(view, this, false);
		BarcodeUtil.encodePDF417("20141000V");
	}

	public List<Requisicao> getDataSet() {
		return listaRequisicoes;
	}
	@Command
	public void onAddNew() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectedRecord", null);
		map.put("recordMode", "NEW");
		Sessions.getCurrent().setAttribute("allmyvalues", map);
		Executions.sendRedirect("/pages/pagebased/index-requisicao-nova.zul");
	}
	
	@Command
	@NotifyChange({"listaRequisicoes","dataSet"})
	public void pesquisar(){
		System.out.println("Requisicao: "+this.numeroRequisicao+" - Nome:"+this.nomeRequisitante+" - Apelido:"+this.apelidoRequisitante+" - Data1: "+this.dataRequisicao1+" - Data2: "+dataRequisicao2);
		
		listaRequisicoes = requisicaoService.getRequisicaoByAttributes(numeroRequisicao, nomeRequisitante, apelidoRequisitante, dataRequisicao1, dataRequisicao2, null, null);
	}
	
	@Command
	public void completar(@BindingParam("requisicaoRecord") Requisicao requisicao){
		Sessions.getCurrent().setAttribute("requisicao", requisicao);
		Executions.sendRedirect("/pages/requisicao/CompletarRequisicao.zul");
	}
	
	@Command
	public void analisar(@BindingParam("requisicaoRecord") Requisicao requisicao){
		Sessions.getCurrent().setAttribute("requisicao", requisicao);
		//Sessions.getCurrent().setAttribute("selectedId", requisicao.getRequisicaoId());
		
		
		Executions.sendRedirect("/pages/requisicao/AnalisarRequisicao.zul");
	}
	
	@Command
	public void registarCartao(@BindingParam("requisicaoRecord") Requisicao requisicao){
		Sessions.getCurrent().setAttribute("requisicao", requisicao);
		//Sessions.getCurrent().setAttribute("selectedId", requisicao.getRequisicaoId());
		
		
		Executions.sendRedirect("/pages/carteira/carteira.zul");
	}

}
