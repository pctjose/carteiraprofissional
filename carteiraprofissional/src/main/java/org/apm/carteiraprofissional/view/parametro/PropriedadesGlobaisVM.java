package org.apm.carteiraprofissional.view.parametro;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apm.carteiraprofissional.PropriedadesGlobais;
import org.apm.carteiraprofissional.service.PropriedadesGlobaisService;
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

public class PropriedadesGlobaisVM {
	private PropriedadesGlobais selectedRecord;
	private boolean makeAsReadOnly;
	private String recordMode;

	@WireVariable
	protected PropriedadesGlobaisService propriedadesGlobaisService;

	@Wire
	private Window frmPropriedadesGlobais;

	public boolean isMakeAsReadOnly() {
		return makeAsReadOnly;
	}

	public void setMakeAsReadOnly(boolean makeAsReadOnly) {
		this.makeAsReadOnly = makeAsReadOnly;
	}	

	

	public PropriedadesGlobais getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(PropriedadesGlobais selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}	
	

	

	public Window getFrmPropriedadesGlobais() {
		return frmPropriedadesGlobais;
	}

	public void setFrmPropriedadesGlobais(Window frmPropriedadesGlobais) {
		this.frmPropriedadesGlobais = frmPropriedadesGlobais;
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
				selectedRecord = new PropriedadesGlobais();
			}
			if (this.recordMode.equalsIgnoreCase("EDIT")) {
				selectedRecord = (PropriedadesGlobais) map.get("selectedRecord");
			}
			if (this.recordMode.equalsIgnoreCase("VIEW")) {
				selectedRecord = (PropriedadesGlobais) map.get("selectedRecord");
				setMakeAsReadOnly(true);
			}

		}

	}	

	@Command
	public void cancel() {
		frmPropriedadesGlobais.detach();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	public void saveThis() {
		String sms="";
		if (selectedRecord.getId() == null){
			selectedRecord.setUuid(UUID.randomUUID().toString());
			sms=": Registada com sucesso";
		}else{
			sms=": Actualizada com sucesso";
		}		
		
		
		propriedadesGlobaisService.savePropriedade(selectedRecord);
		
		
		Map args = new HashMap();
        args.put("returnvalue", propriedadesGlobaisService.getAllPropriedades());        
        BindUtils.postGlobalCommand(null, null, "refreshvalues", args);
		
		
		frmPropriedadesGlobais.detach();
		Clients.showNotification("Propriedade global: "+selectedRecord.getPropriedade()+sms);
	}
}
