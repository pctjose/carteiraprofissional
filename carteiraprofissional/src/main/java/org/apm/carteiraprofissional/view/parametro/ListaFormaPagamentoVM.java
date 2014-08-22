package org.apm.carteiraprofissional.view.parametro;

import java.util.HashMap;
import java.util.List;

import org.apm.carteiraprofissional.FormaPagamento;
import org.apm.carteiraprofissional.service.FormaPagamentoService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class ListaFormaPagamentoVM {
	private FormaPagamento selectedItem;
	private List<FormaPagamento> dataSet;

	@WireVariable
	protected FormaPagamentoService formaPagamentoService;
	
	@Wire
	private Window frmListaFormaPagamento;
	
	
	
	
	
	public Window getFrmListaFormaPagamento() {
		return frmListaFormaPagamento;
	}

	public void setFrmListaFormaPagamento(Window frmListaFormaPagamento) {
		this.frmListaFormaPagamento = frmListaFormaPagamento;
	}

	public FormaPagamento getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(FormaPagamento selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<FormaPagamento> getDataSet() {
		return dataSet;
	}

	public void setDataSet(List<FormaPagamento> dataSet) {
		this.dataSet = dataSet;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		dataSet = formaPagamentoService.getAllFormas();

	}
	
	@Command
	public void onAddNew(){
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "NEW");
		map.put("selectedRecord", null);
		Sessions.getCurrent().setAttribute("parameterValues", map);
		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/FormaPagamento.zul", null, null);
		cRequisicao.setParent(frmListaFormaPagamento);
		cRequisicao.doModal();
	}
	
	@Command
	public void onEdit(@BindingParam("userRecord") FormaPagamento forma) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "EDIT");
		map.put("selectedRecord", forma);

		Sessions.getCurrent().setAttribute("parameterValues", map);	

		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/FormaPagamento.zul", null, null);
		cRequisicao.setParent(frmListaFormaPagamento);
		cRequisicao.doModal();

	}
	
	@Command
	public void openAsReadOnly(@BindingParam("userRecord") FormaPagamento forma) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "VIEW");
		map.put("selectedRecord", forma);

		Sessions.getCurrent().setAttribute("parameterValues", map);	

		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/FormaPagamento.zul", null, null);
		cRequisicao.setParent(frmListaFormaPagamento);
		cRequisicao.doModal();

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	@NotifyChange("dataSet")
	public void onDelete(@BindingParam("userRecord") final FormaPagamento forma) {		
		Messagebox.show("Tem que pretende apagar a forma de Pagamento: "+forma.getDesignacao()+"?",
				"Confirmar Apagar", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION,new org.zkoss.zk.ui.event.EventListener() {
		    public void onEvent(Event evt) throws InterruptedException {
		        if (evt.getName().equals("onOK")) {
		        	formaPagamentoService.deleteForma(forma);
		        	dataSet = formaPagamentoService.getAllFormas();
		        	Clients.showNotification("Forma Pagamento: "+forma.getDesignacao()+" apagada com sucesso");
		        }
		    }
		});

	}

}
