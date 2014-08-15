package org.apm.carteiraprofissional.view.carteira;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.service.CarteiraService;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

public class ListaCarteiraVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Carteira selectedItem;
	private List<Carteira> listaCarteiras;

	private Date startDataEmissao;
	private Date endDateEmissao;
	private Date startDateValidade;
	private Date endDateValidade;
	private String numeroCarteira;
	private String nomeTitular;
	private String apelidoTitular;
	private boolean emitida;
	private String emitidaString;

	@WireVariable
	protected CarteiraService carteiraService;
	
	@Wire
	private Window formListaCarteira;
	
	

	public Window getFormListaCarteira() {
		return formListaCarteira;
	}

	public void setFormListaCarteira(Window formListaCarteira) {
		this.formListaCarteira = formListaCarteira;
	}

	public String getEmitidaString() {
		return emitidaString;
	}

	public void setEmitidaString(String emitidaString) {
		this.emitidaString = emitidaString;
	}

	public Carteira getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Carteira selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<Carteira> getListaCarteiras() {
		return listaCarteiras;
	}

	public void setListaCarteiras(List<Carteira> listaCarteiras) {
		this.listaCarteiras = listaCarteiras;
	}

	public Date getStartDataEmissao() {
		return startDataEmissao;
	}

	public void setStartDataEmissao(Date startDataEmissao) {
		this.startDataEmissao = startDataEmissao;
	}

	public Date getEndDateEmissao() {
		return endDateEmissao;
	}

	public void setEndDateEmissao(Date endDateEmissao) {
		this.endDateEmissao = endDateEmissao;
	}

	public Date getStartDateValidade() {
		return startDateValidade;
	}

	public void setStartDateValidade(Date startDateValidade) {
		this.startDateValidade = startDateValidade;
	}

	public Date getEndDateValidade() {
		return endDateValidade;
	}

	public void setEndDateValidade(Date endDateValidade) {
		this.endDateValidade = endDateValidade;
	}

	public String getNumeroCarteira() {
		return numeroCarteira;
	}

	public void setNumeroCarteira(String numeroCarteira) {
		this.numeroCarteira = numeroCarteira;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public String getApelidoTitular() {
		return apelidoTitular;
	}

	public void setApelidoTitular(String apelidoTitular) {
		this.apelidoTitular = apelidoTitular;
	}

	public boolean isEmitida() {
		return emitida;
	}

	public void setEmitida(boolean emitida) {
		this.emitida = emitida;
	}

	public List<Carteira> getDataSet() {
		return listaCarteiras;
	}

	@Command
	@NotifyChange({ "listaCarteiras", "dataSet" })
	public void pesquisar() {
		Boolean emitiBool = null;
		if (emitidaString != null) {

			if (emitidaString.equalsIgnoreCase("E")) {
				emitiBool = true;
			} else {
				if (emitidaString.equalsIgnoreCase("N")) {
					emitiBool = false;
				}
			}
		}
		listaCarteiras = carteiraService.getAllByAttributes(numeroCarteira,
				nomeTitular, apelidoTitular, startDataEmissao, endDateEmissao,
				startDateValidade, endDateValidade, emitiBool);
	}

	@Command
	public void onEdit(@BindingParam("carteiraRecord") Carteira carteira) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "EDIT");
		map.put("selectedRecord", carteira);

		Sessions.getCurrent().setAttribute("carteiraValues", map);

		//Executions.sendRedirect("/pages/carteira/carteira.zul");
		
		Window cRequisicao=(Window)Executions.createComponents("/pages/carteira/carteira.zul", null, null);
		cRequisicao.setParent(formListaCarteira);
		cRequisicao.doModal();
		
		
	}
	
	@Command
	public void visualizar(@BindingParam("carteiraRecord") Carteira carteira) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "VIEW");
		map.put("selectedRecord", carteira);

		Sessions.getCurrent().setAttribute("carteiraValues", map);
		
		Window cRequisicao=(Window)Executions.createComponents("/pages/carteira/carteira.zul", null, null);
		cRequisicao.setParent(formListaCarteira);
		cRequisicao.doModal();
	}

}
