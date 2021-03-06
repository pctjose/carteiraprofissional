package org.apm.carteiraprofissional.view.parametro;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.PropriedadesGlobais;
import org.apm.carteiraprofissional.service.PropriedadesGlobaisService;
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

public class ListaPropriedadesGlobaisVM {
	private static Logger _log = Logger.getLogger(ListaPropriedadesGlobaisVM.class);
	private PropriedadesGlobais selectedItem;
	private List<PropriedadesGlobais> dataSet;

	@WireVariable
	protected PropriedadesGlobaisService propriedadesGlobaisService;

	@Wire
	private Window frmListaPropriedadesGlobais;

	

	public Window getFrmListaPropriedadesGlobais() {
		return frmListaPropriedadesGlobais;
	}

	public void setFrmListaPropriedadesGlobais(Window frmListaPropriedadesGlobais) {
		this.frmListaPropriedadesGlobais = frmListaPropriedadesGlobais;
	}

	

	public PropriedadesGlobais getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(PropriedadesGlobais selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<PropriedadesGlobais> getDataSet() {
		return dataSet;
	}

	public void setDataSet(List<PropriedadesGlobais> dataSet) {
		this.dataSet = dataSet;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		dataSet = propriedadesGlobaisService.getAllPropriedades();

	}

	@Command
	public void onAddNew() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "NEW");
		map.put("selectedRecord", null);
		Sessions.getCurrent().setAttribute("parameterValues", map);
		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/PropriedadeGlobal.zul", null, null);
		cRequisicao.setParent(frmListaPropriedadesGlobais);
		cRequisicao.doModal();
	}

	@Command
	public void onEdit(@BindingParam("userRecord") PropriedadesGlobais propriedadeGlobal) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "EDIT");
		map.put("selectedRecord", propriedadeGlobal);

		Sessions.getCurrent().setAttribute("parameterValues", map);

		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/PropriedadeGlobal.zul", null, null);
		cRequisicao.setParent(frmListaPropriedadesGlobais);
		cRequisicao.doModal();

	}

	@Command
	public void openAsReadOnly(
			@BindingParam("userRecord") PropriedadesGlobais propriedadeGlobal) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "VIEW");
		map.put("selectedRecord", propriedadeGlobal);

		Sessions.getCurrent().setAttribute("parameterValues", map);

		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/PropriedadeGlobal.zul", null, null);
		cRequisicao.setParent(frmListaPropriedadesGlobais);
		cRequisicao.doModal();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	@NotifyChange("dataSet")
	public void onDelete(
			@BindingParam("userRecord") final PropriedadesGlobais propriedadeGlobal) {
		Messagebox.show(
				"Confirma que pretende apagar a propriedade global: "
						+ propriedadeGlobal.getPropriedade() + "?",
				"Confirmar Apagar", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onOK")) {
							try {
																
								propriedadesGlobaisService.delete(propriedadeGlobal);
								dataSet.remove(propriedadeGlobal);
								
								BindUtils.postNotifyChange(null, null,
										ListaPropriedadesGlobaisVM.this, "dataSet");
								Clients.showNotification("Propriedade Global: "
										+ propriedadeGlobal.getPropriedade()
										+ " apagada com sucesso");

							} catch (Exception e) {
								_log.debug(e);
								Clients.showNotification("N�o foi poss�vel apagar a propriedade global: "
										+ propriedadeGlobal.getPropriedade()
										+ ". Est� em uso");
							}

						}
					}
				});

	}

	@GlobalCommand
	@NotifyChange("dataSet")
	public void refreshvalues(
			@BindingParam("returnvalue") List<PropriedadesGlobais> dataSet) {
		this.dataSet = dataSet;
	}
}
