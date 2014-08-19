package org.apm.carteiraprofissional.view.requisicao;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.service.CarteiraService;
import org.apm.carteiraprofissional.service.RequisicaoService;
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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

public class ListaRequisicaoVM {

	private Requisicao selectedItem;
	private List<Requisicao> listaRequisicoes;	
	private String numeroRequisicao;
	private String nomeRequisitante;
	private String apelidoRequisitante;
	private Date dataRequisicao1;
	private Date dataRequisicao2;
	
	@WireVariable
	private RequisicaoService requisicaoService;
	
	@WireVariable
	private CarteiraService carteiraService;
	
	@Wire
	Window formlistaRequisicao;
	
	

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
	
	private boolean validateSearch(){
		boolean retorno=false;
		if(numeroRequisicao !=null && numeroRequisicao.trim().length()>0){
			retorno=true;
		}
		
		if(nomeRequisitante !=null && nomeRequisitante.trim().length()>0){
			retorno=true;
		}
		
		if(apelidoRequisitante !=null && apelidoRequisitante.trim().length()>0){
			retorno=true;
		}
		
		if(dataRequisicao1!=null && dataRequisicao2!=null){
			retorno=true;
		}
		
		
		
		return retorno;
	}
	
	@Command
	@NotifyChange({"listaRequisicoes","dataSet"})
	public void pesquisar(){
		
		if(validateSearch()){
			listaRequisicoes = requisicaoService.getRequisicaoByAttributes(numeroRequisicao, nomeRequisitante, apelidoRequisitante, dataRequisicao1, dataRequisicao2, null, null);
			if(listaRequisicoes==null || listaRequisicoes.size()<=0){
				Clients.showNotification("N�o foi encontrado nenhuma requisi��o com os par�metros introduzidos.");
			}
		}else{
			Clients.showNotification("Deve introduzir pelo menos um par�metro de pesquisa.");
		}
		
	}
	
	@Command
	public void completar(@BindingParam("requisicaoRecord") Requisicao requisicao){
		Sessions.getCurrent().setAttribute("requisicao", requisicao);
		
		Window cRequisicao=(Window)Executions.createComponents("/pages/supervisor/requisicao/CompletarRequisicao.zul", null, null);
		cRequisicao.setParent(formlistaRequisicao);
		cRequisicao.doModal();
	}
	
	@Command
	public void analisar(@BindingParam("requisicaoRecord") Requisicao requisicao){
		Sessions.getCurrent().setAttribute("requisicao", requisicao);		
		Window cRequisicao=(Window)Executions.createComponents("/pages/supervisor/requisicao/AnalisarRequisicao.zul", null, null);
		cRequisicao.setParent(formlistaRequisicao);
		cRequisicao.doModal();
	}
	
	@Command
	public void registarCartao(@BindingParam("requisicaoRecord") Requisicao requisicao){
				
		if(requisicao.isTemCarteira()){
			Clients.showNotification("J� existe uma carteira registada para esta requisi��o.");
		}else{
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("recordMode", "NEW");
			map.put("requisicao", requisicao);

			Sessions.getCurrent().setAttribute("carteiraValues", map);
			
			Window cRequisicao=(Window)Executions.createComponents("/pages/supervisor/carteira/carteira.zul", null, null);
			cRequisicao.setParent(formlistaRequisicao);
			cRequisicao.doModal();		
			
		}
		
		
	}
	
	@Command
	public void onEdit(){
		Clients.showNotification("Funcionalidade em implementa��o...");
	}
	
	

}
