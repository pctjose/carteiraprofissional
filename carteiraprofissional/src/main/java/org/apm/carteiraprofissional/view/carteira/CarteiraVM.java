package org.apm.carteiraprofissional.view.carteira;

import java.util.List;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.FormaPagamento;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;

public class CarteiraVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Carteira selectedRecord;
	private List<FormaPagamento> formasPagamento;

	public Carteira getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(Carteira selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public List<FormaPagamento> getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
		this.formasPagamento = formasPagamento;
	}
	
	
	
	

}
