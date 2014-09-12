package org.apm.carteiraprofissional.view.parametro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apm.carteiraprofissional.Pais;
import org.apm.carteiraprofissional.Provincia;
import org.apm.carteiraprofissional.service.PaisService;
import org.apm.carteiraprofissional.service.ProvinciaService;
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

public class ProvinciaVM {
	private Provincia selectedRecord;
	private boolean makeAsReadOnly;
	private String recordMode;
	private List<Pais> paises;

	@WireVariable
	protected PaisService paisService;
	@WireVariable
	protected ProvinciaService provinciaService;

	@Wire
	private Window frmProvincia;

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

	

	public PaisService getPaisService() {
		return paisService;
	}

	public void setPaisService(PaisService paisService) {
		this.paisService = paisService;
	}

	

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public Provincia getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(Provincia selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public ProvinciaService getProvinciaService() {
		return provinciaService;
	}

	public void setProvinciaService(ProvinciaService provinciaService) {
		this.provinciaService = provinciaService;
	}

	public Window getFrmProvincia() {
		return frmProvincia;
	}

	public void setFrmProvincia(Window frmProvincia) {
		this.frmProvincia = frmProvincia;
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
				selectedRecord = new Provincia();
			}
			if (this.recordMode.equalsIgnoreCase("EDIT")) {
				selectedRecord = (Provincia) map.get("selectedRecord");
			}
			if (this.recordMode.equalsIgnoreCase("VIEW")) {
				selectedRecord = (Provincia) map.get("selectedRecord");
				setMakeAsReadOnly(true);
			}

		}
		paises=paisService.getAllPaises();

	}

	@Command
	public void cancel() {
		frmProvincia.detach();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	public void saveThis() {
		String sms = "";
		if (selectedRecord.getId() == null) {
			selectedRecord.setUuid(UUID.randomUUID().toString());
			Provincia temp = provinciaService.getByDesignacao(selectedRecord
					.getDesignacao());
			if (temp != null) {
				Clients.showNotification("Provincia: "
						+ selectedRecord.getDesignacao() + " já existe");
				return;
			}
			sms = " Registada com sucesso";
		} else {
			sms = " Actualizada com sucesso";
		}

		
		
		provinciaService.saveProvincia(selectedRecord);

		Map args = new HashMap();
		args.put("returnvalue", provinciaService.getAllProvincia());
		BindUtils.postGlobalCommand(null, null, "refreshvalues", args);

		frmProvincia.detach();
		
		Clients.showNotification("Provincia: " + selectedRecord.getDesignacao()
				+ sms);
	}
}
