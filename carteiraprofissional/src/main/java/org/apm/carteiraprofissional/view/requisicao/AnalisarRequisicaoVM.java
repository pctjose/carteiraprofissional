package org.apm.carteiraprofissional.view.requisicao;

import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.service.RequisicaoService;
import org.apm.carteiraprofissional.utils.EnviarEmail;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

public class AnalisarRequisicaoVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Requisicao selectedRecord;
	private boolean makeAsReadOnly;
	private String aceiteString;

	@WireVariable
	protected RequisicaoService requisicaoService;

	public Requisicao getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(Requisicao selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public boolean isMakeAsReadOnly() {
		return makeAsReadOnly;
	}

	public void setMakeAsReadOnly(boolean makeAsReadOnly) {
		this.makeAsReadOnly = makeAsReadOnly;
	}

	public String getAceiteString() {
		return aceiteString;
	}

	public void setAceiteString(String aceiteString) {
		this.aceiteString = aceiteString;
	}

	public RequisicaoService getRequisicaoService() {
		return requisicaoService;
	}

	public void setRequisicaoService(RequisicaoService requisicaoService) {
		this.requisicaoService = requisicaoService;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		selectedRecord = (Requisicao) Sessions.getCurrent().getAttribute(
				"requisicao");

	}

	@Command
	public void saveThis() {
		if (this.aceiteString.equals("S")) {
			this.selectedRecord.setAceite(true);
		} else {
			this.selectedRecord.setAceite(false);
		}
		requisicaoService.saveRequisicao(selectedRecord);

		String title = "";

		String mensagem = "Exmo(a) Sr�(�): "
				+ selectedRecord.getRequisitante().getNomeCompleto() + "\n\n";
		mensagem += "Vimos informar que a sua requisi��o de carteira profissional solicitada a APM foi: \n\n";
		if (selectedRecord.getAceite()) {
			mensagem += "ACEITE \n\n";
			title = "ACEITA��O DA REQUISI��O DE CARTEIRA PROFISSIONAL APM: "
					+ selectedRecord.getNumeroRequisicao();
		} else {
			mensagem += "REJEITADA \n\n";
			title = "REJEI��O DA REQUISI��O DE CARTEIRA PROFISSIONAL APM: "
					+ selectedRecord.getNumeroRequisicao();
		}

		mensagem += "Observa��es: \n\n";

		mensagem += selectedRecord.getJustificacaoAceitacao();

		mensagem += "\n\n OBRIGADO.";

		EnviarEmail.sendEmail(selectedRecord.getRequisitante().getEmail(),
				title, mensagem);

		Clients.showNotification("Requisi��o actualizada e um email foi enviado ao requisitante");
	}

}
