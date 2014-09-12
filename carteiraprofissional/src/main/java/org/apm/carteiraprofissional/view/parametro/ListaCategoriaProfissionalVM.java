package org.apm.carteiraprofissional.view.parametro;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.Categoria;
import org.apm.carteiraprofissional.service.CategoriaService;
import org.springframework.dao.DataIntegrityViolationException;
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

public class ListaCategoriaProfissionalVM {

	private Categoria selectedItem;
	private List<Categoria> dataSet;
	private static Logger _log = Logger
			.getLogger(ListaCategoriaProfissionalVM.class);

	@WireVariable
	protected CategoriaService categoriaService;

	@Wire
	private Window frmListaCategoria;

	public Window getFrmListaCategoria() {
		return frmListaCategoria;
	}

	public void setFrmListaCategoria(Window frmListaCategoria) {
		this.frmListaCategoria = frmListaCategoria;
	}

	public Categoria getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Categoria selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<Categoria> getDataSet() {
		return dataSet;
	}

	public void setDataSet(List<Categoria> dataSet) {
		this.dataSet = dataSet;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		dataSet = categoriaService.getAllCategorias();

	}

	@Command
	public void onAddNew() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "NEW");
		map.put("selectedRecord", null);
		Sessions.getCurrent().setAttribute("parameterValues", map);
		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/CategoriaProfissional.zul", null,
				null);
		cRequisicao.setParent(frmListaCategoria);
		cRequisicao.doModal();
	}

	@Command
	public void onEdit(@BindingParam("userRecord") Categoria categoria) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "EDIT");
		map.put("selectedRecord", categoria);

		Sessions.getCurrent().setAttribute("parameterValues", map);

		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/CategoriaProfissional.zul", null,
				null);
		cRequisicao.setParent(frmListaCategoria);
		cRequisicao.doModal();

	}

	@Command
	public void openAsReadOnly(@BindingParam("userRecord") Categoria categoria) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recordMode", "VIEW");
		map.put("selectedRecord", categoria);

		Sessions.getCurrent().setAttribute("parameterValues", map);

		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/parametrizacao/CategoriaProfissional.zul", null,
				null);
		cRequisicao.setParent(frmListaCategoria);
		cRequisicao.doModal();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	@NotifyChange("dataSet")
	public void onDelete(@BindingParam("userRecord") final Categoria categoria) {
		Messagebox.show("Tem que pretende apagar a categoria profissional: "
				+ categoria.getDesignacao() + "?", "Confirmar Apagar",
				Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onOK")) {
							try {

								categoriaService.deleteCategoria(categoria);
								// dataSet =
								// categoriaService.getAllCategorias();
								dataSet.remove(categoria);
								BindUtils.postNotifyChange(null, null,
										ListaCategoriaProfissionalVM.this,
										"dataSet");
								Clients.showNotification("Categoria profissional: "
										+ categoria.getDesignacao()
										+ " apagada com sucesso");

							} catch (DataIntegrityViolationException e) {
								_log.debug(e);
								Clients.showNotification("Não foi possível apagar a categoria profissional: "
										+ categoria.getDesignacao()
										+ ". Está em uso");
							}

						}
					}
				});

	}
	
	@GlobalCommand
	@NotifyChange("dataSet")
	public void refreshvalues(
			@BindingParam("returnvalue") List<Categoria> dataSet) {
		this.dataSet = dataSet;
	}

}
