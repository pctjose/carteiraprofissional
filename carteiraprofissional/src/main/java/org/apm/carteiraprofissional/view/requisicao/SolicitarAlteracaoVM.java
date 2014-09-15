package org.apm.carteiraprofissional.view.requisicao;

import org.apm.carteiraprofissional.SolicitarEditarCarteira;

public class SolicitarAlteracaoVM {
	private SolicitarEditarCarteira selectedRecord;
	private String numeroCarteira;

	public SolicitarEditarCarteira getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(SolicitarEditarCarteira selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public String getNumeroCarteira() {
		return numeroCarteira;
	}

	public void setNumeroCarteira(String numeroCarteira) {
		this.numeroCarteira = numeroCarteira;
	}
	
	
	
	
}
