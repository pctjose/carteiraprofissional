package org.apm.carteiraprofissional.view.requisicao;

import java.util.Random;

import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.service.NumeroRequisicaoService;
import org.apm.carteiraprofissional.service.RequisicaoService;
import org.apm.carteiraprofissional.service.RequisitanteService;
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

public class RequisicaoVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Requisicao selectedRecord;
	private boolean makeAsReadOnly;
	private String recordMode;
	private Requisitante requisitante;

	@WireVariable
	protected RequisitanteService requisitanteService;

	@WireVariable
	protected RequisicaoService requisicaoService;

	@WireVariable
	protected NumeroRequisicaoService numeroRequisicaoService;

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

	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {

		Requisicao requisicao;
		Selectors.wireComponents(view, this, false);

		requisitante = (Requisitante) Sessions.getCurrent().getAttribute(
				"requisitante");

		/**
		 * Verifica se requisitante não está nulo, de principio nunca deverá
		 * estar nulo.
		 */
		if (requisitante != null) {
			// verificar se o requisitante tem um id, se tiver tudo indica que é
			// uma actualização
			if (requisitante.getId() != null) {
				requisicao = requisicaoService
						.getRequisicaoByRequisitante(requisitante);
				if (requisicao != null) {
					this.selectedRecord = requisicao;
				} else {
					this.selectedRecord = new Requisicao();
				}

			} else {
				this.selectedRecord = new Requisicao();
			}
		} else {
			/**
			 * Voltar ao requisitante
			 */
		}

	}

	@Command
	public void saveThis() {

		requisitanteService.saveRequisitante(requisitante);

		this.selectedRecord.setRequisitante(requisitante);

		Random r = new Random();

		this.selectedRecord.setNumeroRequisicao("2014100" + r.nextInt(20));

		requisicaoService.saveRequisicao(selectedRecord);

		EnviarEmail
				.sendEmail(
						requisitante.getEmail(),
						"Requisicao de Carteira Profissional - APM",
						"Registamos a sua requisicao de carteira profissional no nosso sistema. \n\n Para todos efeitos o numero de requisição é: "
								+ selectedRecord.getNumeroRequisicao());
		
		Clients.showNotification("Requisição Registada e um email foi enviado.");

	}

}
