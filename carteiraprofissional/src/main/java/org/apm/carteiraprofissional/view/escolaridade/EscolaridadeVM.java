package org.apm.carteiraprofissional.view.escolaridade;

import java.util.HashMap;

import org.apm.carteiraprofissional.Escolaridade;
import org.apm.carteiraprofissional.service.EscolaridadeService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;

public class EscolaridadeVM {

	@WireVariable
	private EscolaridadeService escolaridadeService;
	private Escolaridade selectedRecord;
	private String recordMode;
	private boolean makeAsReadOnly;

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

	public boolean isMakeAsReadOnly() {
		return makeAsReadOnly;
	}

	public void setMakeAsReadOnly(boolean makeAsReadOnly) {
		this.makeAsReadOnly = makeAsReadOnly;
	}

	@SuppressWarnings("unchecked")
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {

		Escolaridade userProfile;
		Selectors.wireComponents(view, this, false);

		final HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("allmyvalues");
		this.recordMode = (String) map.get("recordMode");
		userProfile = (Escolaridade) map.get("selectedRecord");
		escolaridadeService = (EscolaridadeService) SpringUtil
				.getBean("escolaridadeService");

		if (recordMode.equals("NEW")) {
			this.selectedRecord = new Escolaridade();
		}

		if (recordMode.equals("EDIT")) {
			this.selectedRecord = userProfile;
		}

		if (recordMode == "READ") {
			setMakeAsReadOnly(true);
			this.selectedRecord = userProfile;
		}
	}

	@Command
	public void saveThis(@BindingParam("action") Integer action) {

		escolaridadeService.saveUpdateEscolaridade(this.selectedRecord);

		if (action == 0) {
			Executions.sendRedirect("listaEscolaridade.zul");
		}
		if (action == 1) {
			this.selectedRecord = new Escolaridade();
			BindUtils.postNotifyChange(null, null, EscolaridadeVM.this,
					"selectedRecord");

		}
	}

	@Command
	public void cancel() {
		Executions.sendRedirect("userList.zul");
	}

}
