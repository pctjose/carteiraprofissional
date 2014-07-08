package org.apm.carteiraprofissional.view.requisicao;

import java.util.HashMap;
import java.util.List;

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

public class ListaRequisicaoSingleUserVM {

	private Requisitante selectedItem;
	private List<Requisitante> RequisicoesOfUser;
	private Utilizador requisitante;

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

	public List<Requisitante> getDataSet() {
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
