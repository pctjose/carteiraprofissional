package org.apm.carteiraprofissional.view.requisicao;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.PropriedadesGlobais;
import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.service.RequisicaoService;
import org.apm.carteiraprofissional.utils.EnviarEmail;
import org.apm.carteiraprofissional.utils.PropriedadeGlobalUtils;
import org.apm.carteiraprofissional.utils.UtilizadorUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

public class AnalisarRequisicaoVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(AnalisarRequisicaoVM.class);

	private Requisicao selectedRecord;
	private boolean makeAsReadOnly;
	private String aceiteString;

	@WireVariable
	protected RequisicaoService requisicaoService;

	@Wire
	private Window frmAnalisarRequisicao;

	public Window getFrmAnalisarRequisicao() {
		return frmAnalisarRequisicao;
	}

	public void setFrmAnalisarRequisicao(Window frmAnalisarRequisicao) {
		this.frmAnalisarRequisicao = frmAnalisarRequisicao;
	}

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

		if (this.selectedRecord.isAceite()) {
			this.aceiteString = "S";
		} else {
			this.aceiteString = "N";
		}

	}

	@Command
	public void saveThis() {
		if (this.aceiteString.equals("S")) {
			this.selectedRecord.setAceite(true);
			this.selectedRecord.setAceitePor(UtilizadorUtils.getLogedUser());
		} else {
			this.selectedRecord.setAceite(false);
			this.selectedRecord.setAceitePor(null);
		}

		requisicaoService.saveRequisicao(selectedRecord);

		try {

			Clients.showBusy("Enviando email ao requisitante...");

			String title = "";

			String mensagem = "Exmo(a) Srº(ª): "
					+ selectedRecord.getRequisitante().getNomeCompleto()
					+ "\n\n";
			mensagem += "Vimos informar que a sua requisição de carteira profissional solicitada a APM foi: \n\n";
			if (selectedRecord.isAceite()) {
				mensagem += "ACEITE \n\n";
				title = "ACEITAÇÃO DA REQUISIÇÃO DE CARTEIRA PROFISSIONAL APM: "
						+ selectedRecord.getNumeroRequisicao();
			} else {
				mensagem += "REJEITADA \n\n";
				title = "REJEIÇÃO DA REQUISIÇÃO DE CARTEIRA PROFISSIONAL APM: "
						+ selectedRecord.getNumeroRequisicao();
			}

			mensagem += "Observações: \n\n";

			mensagem += selectedRecord.getJustificacaoAceitacao();

			mensagem += "\n\n OBRIGADO.";

			PropriedadesGlobais emailApmFrom = PropriedadeGlobalUtils
					.getEmailAPM();

			EnviarEmail.sendEmail(emailApmFrom.getValor(), emailApmFrom
					.getValor2(), selectedRecord.getRequisitante().getEmail(),
					title, mensagem);

			Clients.showNotification("Requisição actualizada e um email foi enviado ao requisitante: "
					+ selectedRecord.getRequisitante().getNomeCompleto());

			Clients.clearBusy();
		} catch (Exception e) {
			log.debug(e);
			Clients.clearBusy();
			Clients.showNotification("Requisição actualizada mas não foi possível enviar email de notificação da decisão ao requisitante");
		}

		frmAnalisarRequisicao.detach();
	}

	@Command
	public void cancel() {
		frmAnalisarRequisicao.detach();
	}

}
