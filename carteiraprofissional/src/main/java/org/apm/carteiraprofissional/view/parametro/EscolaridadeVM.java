package org.apm.carteiraprofissional.view.parametro;

import java.util.HashMap;
import java.util.UUID;

import org.apm.carteiraprofissional.Escolaridade;
import org.apm.carteiraprofissional.service.EscolaridadeService;
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

public class EscolaridadeVM {

	private Escolaridade selectedRecord;
	private boolean makeAsReadOnly;
	private String recordMode;

	@WireVariable
	protected EscolaridadeService escolaridadeService;

	@Wire
	private Window frmEscolaridade;

	public boolean isMakeAsReadOnly() {
		return makeAsReadOnly;
	}

	public void setMakeAsReadOnly(boolean makeAsReadOnly) {
		this.makeAsReadOnly = makeAsReadOnly;
	}	

	public Escolaridade getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(Escolaridade selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}	
	

	public Window getFrmEscolaridade() {
		return frmEscolaridade;
	}

	public void setFrmEscolaridade(Window frmEscolaridade) {
		this.frmEscolaridade = frmEscolaridade;
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
				selectedRecord = new Escolaridade();
			}
			if (this.recordMode.equalsIgnoreCase("EDIT")) {
				selectedRecord = (Escolaridade) map.get("selectedRecord");
			}
			if (this.recordMode.equalsIgnoreCase("VIEW")) {
				selectedRecord = (Escolaridade) map.get("selectedRecord");
				setMakeAsReadOnly(true);
			}

		}

	}	

	@Command
	public void cancel() {
		frmEscolaridade.detach();
	}

	@Command
	public void saveThis() {
		if (selectedRecord.getId() == null)
			selectedRecord.setUuid(UUID.randomUUID().toString());
		escolaridadeService.saveUpdateEscolaridade(selectedRecord);		
		frmEscolaridade.detach();
		Clients.showNotification("Nivel de Escolaridade: "+selectedRecord.getDesignacao()+" Registada/Actualizada");
	}

}
