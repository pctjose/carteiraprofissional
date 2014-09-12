package org.apm.carteiraprofissional.view.parametro;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apm.carteiraprofissional.Categoria;
import org.apm.carteiraprofissional.service.CategoriaService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

public class CategoriaProfissionalVM {

	private Categoria selectedRecord;
	private boolean makeAsReadOnly;
	private String recordMode;

	@WireVariable
	protected CategoriaService categoriaService;

	@Wire
	private Window frmCategoriaProfissional;

	public boolean isMakeAsReadOnly() {
		return makeAsReadOnly;
	}

	public void setMakeAsReadOnly(boolean makeAsReadOnly) {
		this.makeAsReadOnly = makeAsReadOnly;
	}

	public Categoria getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(Categoria selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}

	@SuppressWarnings("unchecked")
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		final HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("parameterValues");

		if (map != null) {
			this.recordMode = (String) map.get("recordMode");
			if (this.recordMode.equalsIgnoreCase("NEW")) {
				selectedRecord = new Categoria();
			}
			if (this.recordMode.equalsIgnoreCase("EDIT")) {
				selectedRecord = (Categoria) map.get("selectedRecord");
			}
			if (this.recordMode.equalsIgnoreCase("VIEW")) {
				selectedRecord = (Categoria) map.get("selectedRecord");
				setMakeAsReadOnly(true);
			}

		}

	}

	public Window getFrmCategoriaProfissional() {
		return frmCategoriaProfissional;
	}

	public void setFrmCategoriaProfissional(Window frmCategoriaProfissional) {
		this.frmCategoriaProfissional = frmCategoriaProfissional;
	}

	@Command
	public void cancel() {
		frmCategoriaProfissional.detach();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	public void saveThis() {
		String sms = "";
		if (selectedRecord.getId() == null) {
			selectedRecord.setUuid(UUID.randomUUID().toString());
			sms = " Registada com sucesso";
		} else {
			sms = " Actualizada com sucesso";
		}

		categoriaService.saveCategoria(selectedRecord);
		
		Map args = new HashMap();
        args.put("returnvalue", categoriaService.getAllCategorias());        
        BindUtils.postGlobalCommand(null, null, "refreshvalues", args);
		
		
		frmCategoriaProfissional.detach();
		Clients.showNotification("Categoria Profissional: "
				+ selectedRecord.getDesignacao() + sms);
	}

}
