package org.apm.carteiraprofissional.view.parametro;

import java.util.HashMap;
import java.util.UUID;

import org.apm.carteiraprofissional.TipoDocumento;
import org.apm.carteiraprofissional.service.TipoDocumentoService;
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

public class TipoDocumentoVM {
	private TipoDocumento selectedRecord;
	private boolean makeAsReadOnly;
	private String recordMode;

	@WireVariable
	protected TipoDocumentoService tipoDocumentoService;

	@Wire
	private Window frmTipoDocumento;

	public boolean isMakeAsReadOnly() {
		return makeAsReadOnly;
	}

	public void setMakeAsReadOnly(boolean makeAsReadOnly) {
		this.makeAsReadOnly = makeAsReadOnly;
	}

	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}

	public TipoDocumento getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(TipoDocumento selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public Window getFrmTipoDocumento() {
		return frmTipoDocumento;
	}

	public void setFrmTipoDocumento(Window frmTipoDocumento) {
		this.frmTipoDocumento = frmTipoDocumento;
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
				selectedRecord = new TipoDocumento();
			}
			if (this.recordMode.equalsIgnoreCase("EDIT")) {
				selectedRecord = (TipoDocumento) map.get("selectedRecord");
			}
			if (this.recordMode.equalsIgnoreCase("VIEW")) {
				selectedRecord = (TipoDocumento) map.get("selectedRecord");
				setMakeAsReadOnly(true);
			}

		}

	}

	@Command
	public void cancel() {
		frmTipoDocumento.detach();
	}

	@Command
	public void saveThis() {
		if (selectedRecord.getId() == null)
			selectedRecord.setUuid(UUID.randomUUID().toString());

		tipoDocumentoService.saveTipoDocumento(selectedRecord);
		frmTipoDocumento.detach();
		Clients.showNotification("Tipo Documento: "
				+ selectedRecord.getDesignacao() + " Registado/Actualizado");
	}

}
