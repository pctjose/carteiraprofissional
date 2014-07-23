package org.apm.carteiraprofissional.view.requisicao;

import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.service.NumeroRequisicaoService;
import org.apm.carteiraprofissional.service.RequisicaoService;
import org.apm.carteiraprofissional.service.RequisitanteService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.WireVariable;

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
		 * Verifica se requisitante n�o est� nulo, de principio nunca dever�
		 * estar nulo.
		 */
		if (requisitante != null) {
			// verificar se o requisitante tem um id, se tiver tudo indica que �
			// uma actualiza��o
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
		
		requisicaoService.saveRequisicao(selectedRecord);
		
		

	}

}
