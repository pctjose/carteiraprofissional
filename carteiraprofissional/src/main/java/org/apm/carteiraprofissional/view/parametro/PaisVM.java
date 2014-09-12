package org.apm.carteiraprofissional.view.parametro;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apm.carteiraprofissional.Pais;
import org.apm.carteiraprofissional.service.PaisService;
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

public class PaisVM {
	private Pais selectedRecord;
	private boolean makeAsReadOnly;
	private String recordMode;

	@WireVariable
	protected PaisService paisService;

	@Wire
	private Window frmPais;

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

	public Pais getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(Pais selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public PaisService getPaisService() {
		return paisService;
	}

	public void setPaisService(PaisService paisService) {
		this.paisService = paisService;
	}

	public Window getFrmPais() {
		return frmPais;
	}

	public void setFrmPais(Window frmPais) {
		this.frmPais = frmPais;
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
				selectedRecord = new Pais();
			}
			if (this.recordMode.equalsIgnoreCase("EDIT")) {
				selectedRecord = (Pais) map.get("selectedRecord");
			}
			if (this.recordMode.equalsIgnoreCase("VIEW")) {
				selectedRecord = (Pais) map.get("selectedRecord");
				setMakeAsReadOnly(true);
			}

		}

	}

	@Command
	public void cancel() {
		frmPais.detach();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	public void saveThis() {
		String sms = "";
		if (selectedRecord.getId() == null) {
			selectedRecord.setUuid(UUID.randomUUID().toString());
			Pais temp = paisService.getByDesignacao(selectedRecord
					.getDesignacao());
			if (temp != null) {
				Clients.showNotification("Pais: "
						+ selectedRecord.getDesignacao() + " já existe");
				return;
			}
			sms = " Registado com sucesso";
		} else {
			sms = " Actualizado com sucesso";
		}

		paisService.savePais(selectedRecord);

		Map args = new HashMap();
		args.put("returnvalue", paisService.getAllPaises());
		BindUtils.postGlobalCommand(null, null, "refreshvalues", args);

		frmPais.detach();
		Clients.showNotification("Pais: " + selectedRecord.getDesignacao()
				+ sms);
	}
}
