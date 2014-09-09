package org.apm.carteiraprofissional.view.parametro;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.Escolaridade;
import org.apm.carteiraprofissional.service.EscolaridadeService;
import org.apm.carteiraprofissional.view.requisicao.RequisicaoCarteiraVM;
import org.zkoss.bind.BindUtils;
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

public class ListaEscolaridadeVM {
	private static Logger _log = Logger.getLogger(RequisicaoCarteiraVM.class);
	private Escolaridade selectedItem;
	private List<Escolaridade> dataSet;

	@WireVariable
	protected EscolaridadeService escolaridadeService;
	
	@Wire
	private Window frmListaEscolaridade;		

	
	public Window getFrmListaEscolaridade() {
		return frmListaEscolaridade;
	}

	public void setFrmListaEscolaridade(Window frmListaEscolaridade) {
		this.frmListaEscolaridade = frmListaEscolaridade;
	}

	public Escolaridade getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Escolaridade selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<Escolaridade> getDataSet() {
		return dataSet;
	}

	public void setDataSet(List<Escolaridade> dataSet) {
		this.dataSet = dataSet;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		dataSet = escolaridadeService.getAllNiveis();

	}
	
	@Command
	public void onAddNew(){
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "NEW");
		map.put("selectedRecord", null);
		Sessions.getCurrent().setAttribute("parameterValues", map);
		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/Escolaridade.zul", null, null);
		cRequisicao.setParent(frmListaEscolaridade);
		cRequisicao.doModal();
	}
	
	@Command
	public void onEdit(@BindingParam("userRecord") Escolaridade escolaridade) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "EDIT");
		map.put("selectedRecord", escolaridade);

		Sessions.getCurrent().setAttribute("parameterValues", map);	

		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/Escolaridade.zul", null, null);
		cRequisicao.setParent(frmListaEscolaridade);
		cRequisicao.doModal();

	}
	
	@Command
	public void openAsReadOnly(@BindingParam("userRecord") Escolaridade escolaridade) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "VIEW");
		map.put("selectedRecord", escolaridade);

		Sessions.getCurrent().setAttribute("parameterValues", map);	

		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/Escolaridade.zul", null, null);
		cRequisicao.setParent(frmListaEscolaridade);
		cRequisicao.doModal();

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	@NotifyChange("dataSet")
	public void onDelete(@BindingParam("userRecord") final Escolaridade escolaridade) {		
		Messagebox.show("Confirma que pretende apagar o nivel de escolaridade: "+escolaridade.getDesignacao()+"?",
				"Confirmar Apagar", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION,new org.zkoss.zk.ui.event.EventListener() {
		    public void onEvent(Event evt) throws InterruptedException {
		        if (evt.getName().equals("onOK")) {
		        	try{
		        		escolaridadeService.delete(escolaridade);
			        	dataSet.remove(escolaridade);
			        	BindUtils.postNotifyChange(null, null,
								ListaEscolaridadeVM.this, "dataSet");
			        	Clients.showNotification("Nivel Escolaridade: "+escolaridade.getDesignacao()+" apagada com sucesso");

		        	}catch(Exception e){
		        		_log.debug(e);
		        		Clients.showNotification("Não foi possível apagar a escolaridade: "+escolaridade.getDesignacao()+". Está em uso");
		        	}
		      
		        			        }
		    }
		});

	}

}
