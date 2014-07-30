package org.apm.carteiraprofissional.view.requisicao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.Utilizador;
import org.apm.carteiraprofissional.service.RequisitanteService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;

public class ListaRequisicaoVM {

	private Requisitante selectedItem;
	private List<Requisicao> RequisicoesOfUser;
	private Utilizador requisitante;
	
	private String numeroRequisicao;
	private String nomeRequisitante;
	private String apelidoRequisitante;
	private Date dataRequisicao1;
	private Date dataRequisicao2;
	
	

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
	@WireVariable
	private RequisitanteService requisicaoService;

	public Requisitante getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Requisitante selectedItem) {
		this.selectedItem = selectedItem;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		requisicaoService = (RequisitanteService) SpringUtil
				.getBean("requisicaoService");

		requisitante = (Utilizador) Sessions.getCurrent().getAttribute(
				"requisitante");
		//RequisicoesOfUser = requisicaoService
		//		.getAllRequisicoesOfUser(requisitante);
	}

	public List<Requisicao> getDataSet() {
		return RequisicoesOfUser;
	}
	@Command
	public void onAddNew() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectedRecord", null);
		map.put("recordMode", "NEW");
		Sessions.getCurrent().setAttribute("allmyvalues", map);
		Executions.sendRedirect("/pages/pagebased/index-requisicao-nova.zul");
	}

}
