package org.apm.carteiraprofissional.view.parametro;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.Provincia;
import org.apm.carteiraprofissional.service.ProvinciaService;
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

public class ListaProvinciaVM {
	private static Logger _log = Logger.getLogger(RequisicaoCarteiraVM.class);
	private Provincia selectedItem;
	private List<Provincia> dataSet;

	@WireVariable
	protected ProvinciaService provinciaService;

	@Wire
	private Window frmListaProvincia;	

	public Provincia getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Provincia selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<Provincia> getDataSet() {
		return dataSet;
	}

	public void setDataSet(List<Provincia> dataSet) {
		this.dataSet = dataSet;
	}

	public ProvinciaService getProvinciaService() {
		return provinciaService;
	}

	public void setProvinciaService(ProvinciaService provinciaService) {
		this.provinciaService = provinciaService;
	}

	public Window getFrmListaProvincia() {
		return frmListaProvincia;
	}

	public void setFrmListaProvincia(Window frmListaProvincia) {
		this.frmListaProvincia = frmListaProvincia;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		dataSet = provinciaService.getAllProvincia();

	}

	@Command
	public void onAddNew() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "NEW");
		map.put("selectedRecord", null);
		Sessions.getCurrent().setAttribute("parameterValues", map);
		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/Provincia.zul", null, null);
		cRequisicao.setParent(frmListaProvincia);
		cRequisicao.doModal();
	}

	@Command
	public void onEdit(@BindingParam("userRecord") Provincia provincia) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "EDIT");
		map.put("selectedRecord", provincia);

		Sessions.getCurrent().setAttribute("parameterValues", map);

		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/Provincia.zul", null, null);
		cRequisicao.setParent(frmListaProvincia);
		cRequisicao.doModal();

	}

	@Command
	public void openAsReadOnly(
			@BindingParam("userRecord") Provincia provincia) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "VIEW");
		map.put("selectedRecord", provincia);

		Sessions.getCurrent().setAttribute("parameterValues", map);

		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/Provincia.zul", null, null);
		cRequisicao.setParent(frmListaProvincia);
		cRequisicao.doModal();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	@NotifyChange("dataSet")
	public void onDelete(
			@BindingParam("userRecord") final Provincia provincia) {
		Messagebox.show(
				"Confirma que pretende apagar a Provincia: "
						+ provincia.getDesignacao() + "?",
				"Confirmar Apagar", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onOK")) {
							try {
								
								provinciaService.delete(provincia);
								dataSet.remove(provincia);
								BindUtils.postNotifyChange(null, null,
										ListaProvinciaVM.this, "dataSet");
								Clients.showNotification("Provincia: "
										+ provincia.getDesignacao()
										+ " apagada com sucesso");

							} catch (Exception e) {
								_log.debug(e);
								Clients.showNotification("Não foi possível apagar a provincia: "
										+ provincia.getDesignacao()
										+ ". Está em uso");
							}

						}
					}
				});

	}

	@GlobalCommand
	@NotifyChange("dataSet")
	public void refreshvalues(
			@BindingParam("returnvalue") List<Provincia> dataSet) {
		this.dataSet = dataSet;
	}
}
