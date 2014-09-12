package org.apm.carteiraprofissional.view.parametro;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.TipoDocumento;
import org.apm.carteiraprofissional.service.TipoDocumentoService;
import org.apm.carteiraprofissional.view.requisicao.RequisicaoCarteiraVM;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
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

public class ListaTipoDocumentoVM {
	private static Logger _log = Logger.getLogger(RequisicaoCarteiraVM.class);
	private TipoDocumento selectedItem;
	private List<TipoDocumento> dataSet;

	@WireVariable
	protected TipoDocumentoService tipoDocumentoService;
	
	@Wire
	private Window frmListaTipoDocumento;		

	
	

	public TipoDocumento getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(TipoDocumento selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<TipoDocumento> getDataSet() {
		return dataSet;
	}

	public void setDataSet(List<TipoDocumento> dataSet) {
		this.dataSet = dataSet;
	}

	public Window getFrmListaTipoDocumento() {
		return frmListaTipoDocumento;
	}

	public void setFrmListaTipoDocumento(Window frmListaTipoDocumento) {
		this.frmListaTipoDocumento = frmListaTipoDocumento;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		dataSet = tipoDocumentoService.getAllTipoDocumento();

	}
	
	@Command
	public void onAddNew(){
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "NEW");
		map.put("selectedRecord", null);
		Sessions.getCurrent().setAttribute("parameterValues", map);
		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/TipoDocumento.zul", null, null);
		cRequisicao.setParent(frmListaTipoDocumento);
		cRequisicao.doModal();
	}
	
	@Command
	public void onEdit(@BindingParam("userRecord") TipoDocumento tipoDocumento) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "EDIT");
		map.put("selectedRecord", tipoDocumento);

		Sessions.getCurrent().setAttribute("parameterValues", map);	

		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/TipoDocumento.zul", null, null);
		cRequisicao.setParent(frmListaTipoDocumento);
		cRequisicao.doModal();

	}
	
	@Command
	public void openAsReadOnly(@BindingParam("userRecord") TipoDocumento tipoDocumento) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "VIEW");
		map.put("selectedRecord", tipoDocumento);

		Sessions.getCurrent().setAttribute("parameterValues", map);	

		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/TipoDocumento.zul", null, null);
		cRequisicao.setParent(frmListaTipoDocumento);
		cRequisicao.doModal();

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	@NotifyChange("dataSet")
	public void onDelete(@BindingParam("userRecord") final TipoDocumento tipoDocumento) {		
		Messagebox.show("Confirma que pretende apagar o tipo de documento: "+tipoDocumento.getDesignacao()+"?",
				"Confirmar Apagar", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION,new org.zkoss.zk.ui.event.EventListener() {
		    public void onEvent(Event evt) throws InterruptedException {
		        if (evt.getName().equals("onOK")) {
		        	try{
		        		
		        		tipoDocumentoService.deleteTipoDocumento(tipoDocumento);
			        	dataSet.remove(tipoDocumento);
			        	BindUtils.postNotifyChange(null, null,
								ListaTipoDocumentoVM.this, "dataSet");
			        	Clients.showNotification("Tipo Documento: "+tipoDocumento.getDesignacao()+" apagado com sucesso");

		        	}catch(Exception e){
		        		_log.debug(e);
		        		Clients.showNotification("Não foi possível apagar o tipo de documento: "+tipoDocumento.getDesignacao()+". Está em uso");
		        	}
		      
		        			        }
		    }
		});

	}
	
	@GlobalCommand
	@NotifyChange("dataSet")
	public void refreshvalues(
			@BindingParam("returnvalue") List<TipoDocumento> dataSet) {
		this.dataSet = dataSet;
	}
}
