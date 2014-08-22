package org.apm.carteiraprofissional.view.parametro;

import java.util.HashMap;
import java.util.UUID;

import org.apm.carteiraprofissional.FormaPagamento;
import org.apm.carteiraprofissional.service.FormaPagamentoService;
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

public class FormaPagamentoVM {
	
	private FormaPagamento selectedRecord;
	private boolean makeAsReadOnly;
	private String recordMode;

	@WireVariable
	protected FormaPagamentoService formaPagamentoService;

	@Wire
	private Window frmFormaPagamento;

	public boolean isMakeAsReadOnly() {
		return makeAsReadOnly;
	}

	public void setMakeAsReadOnly(boolean makeAsReadOnly) {
		this.makeAsReadOnly = makeAsReadOnly;
	}

	public FormaPagamento getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(FormaPagamento selectedRecord) {
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
				selectedRecord = new FormaPagamento();
			}
			if (this.recordMode.equalsIgnoreCase("EDIT")) {
				selectedRecord = (FormaPagamento) map.get("selectedRecord");
			}
			if (this.recordMode.equalsIgnoreCase("VIEW")) {
				selectedRecord = (FormaPagamento) map.get("selectedRecord");
				setMakeAsReadOnly(true);
			}

		}

	}

	

	public Window getFrmFormaPagamento() {
		return frmFormaPagamento;
	}

	public void setFrmFormaPagamento(Window frmFormaPagamento) {
		this.frmFormaPagamento = frmFormaPagamento;
	}

	@Command
	public void cancel() {
		frmFormaPagamento.detach();
	}

	@Command
	public void saveThis() {
		if (selectedRecord.getId() == null)
			selectedRecord.setUuid(UUID.randomUUID().toString());
		formaPagamentoService.saveFormaPagamento(selectedRecord);
		frmFormaPagamento.detach();
		Clients.showNotification("Forma pagamento: "+selectedRecord.getDesignacao()+" Registada/Actualizada");
	}


}
