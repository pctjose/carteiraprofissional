package org.apm.carteiraprofissional.view;

import java.util.HashMap;

import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.service.RequisicaoService;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

public class StartSearchVM extends SelectorComposer<Component>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Wire
	private String codigoPesquisa;
	
	@WireVariable
	protected RequisicaoService requisicaoService;

	public String getCodigoPesquisa() {
		return codigoPesquisa;
	}

	public void setCodigoPesquisa(String codigoPesquisa) {
		this.codigoPesquisa = codigoPesquisa;
	}
	
	@Command
	public void areaRestrita(){
		Executions.sendRedirect("/pages/pagebased/index-login.zul");
	}
	
	@Command
	public void requisitarCarteira(){
		//EnviarEmail.sendEmail("pctjose@gmail.com", "jorge3", "eurico.jose@fgh.org.mz", "Teste Envio de Email", "Enviando email atraves do Gmail via Java");
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectedRecord", null);
		map.put("recordMode", "NEW");
		Sessions.getCurrent().setAttribute("allmyvalues", map);
		Executions.sendRedirect("/pages/pagebased/index-requisitante-novo.zul");
	}
	
	@Command
	public void pesquisar(){
		if(this.codigoPesquisa!=null){
			if(this.codigoPesquisa.trim().isEmpty()){
				Clients.showNotification("Deve introduzir o codigo de pesquisa");
			}else{
				Requisicao requisicao=requisicaoService.getRequisicaoByNumero(codigoPesquisa);
				if(requisicao==null){
					Clients.showNotification("Não foi encontrada nenhuma requisição com o número introduzido");
				}else{
					final HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("selectedRecord", requisicao.getRequisitante());
					map.put("recordMode", "EDIT");
					Sessions.getCurrent().setAttribute("allmyvalues", map);
					Executions.sendRedirect("/pages/pagebased/index-requisitante-novo.zul");
				}
			}
		}else{
			Clients.showNotification("Deve introduzir o codigo de pesquisa");
		}
		
	}
	
	

}
