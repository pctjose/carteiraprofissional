package org.apm.carteiraprofissional.view.carteira;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.FormaPagamento;
import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.service.CarteiraService;
import org.apm.carteiraprofissional.service.FormaPagamentoService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Datebox;

public class CarteiraVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Carteira selectedRecord;
	private List<FormaPagamento> formasPagamento;
	private boolean makeAsReadOnly;

	@Wire
	private Datebox dataValidade;

	@Wire
	private Datebox dataEmissao;

	@WireVariable
	protected FormaPagamentoService formaPagamentoService;
	
	@WireVariable
	protected CarteiraService carteiraService;
	
	

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

	public boolean isMakeAsReadOnly() {
		return makeAsReadOnly;
	}

	public void setMakeAsReadOnly(boolean makeAsReadOnly) {
		this.makeAsReadOnly = makeAsReadOnly;
	}

	

	public Datebox getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Datebox dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Datebox getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Datebox dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		Requisicao requisicao = (Requisicao) Sessions.getCurrent()
				.getAttribute("requisicao");
		formasPagamento = formaPagamentoService.getAllFormas();
		selectedRecord = new Carteira();
		selectedRecord.setRequisicao(requisicao);

	}

	@Command
	@NotifyChange("dataValidade")
	public void calcDataValidade() {

		Calendar calendario = Calendar.getInstance();
		calendario.setTime(dataEmissao.getValue());

		if (this.selectedRecord.getRequisicao().getRequisitante().getMembro() != null) {
			if (this.selectedRecord.getRequisicao().getRequisitante()
					.getMembro()) {
				calendario.add(Calendar.YEAR, 3);
			} else {
				calendario.add(Calendar.YEAR, 2);
			}
		} else {
			calendario.add(Calendar.YEAR, 2);
		}

		dataValidade.setValue(calendario.getTime());
		this.selectedRecord.setDataValidade(calendario.getTime());
		
	}
	
	@Command
	public void saveThis(){
		String alfabeto="ABCDEFGHIJKLMNOPQRSTUVWYXZ";
		Random r=new Random();
		
		char letraFinal=alfabeto.charAt(r.nextInt(alfabeto.length()));
		
		String numeroCarteira=this.selectedRecord.getRequisicao().getNumeroRequisicao();
		numeroCarteira+=letraFinal;
		this.selectedRecord.setNumeroCarteira(numeroCarteira);
		
		this.selectedRecord.setUuid(UUID.randomUUID().toString());
		this.selectedRecord.setEmitida(false);
		this.selectedRecord.setEnviarEmissao(true);
		this.selectedRecord.setDataValidade(dataValidade.getValue());
		
		carteiraService.saveCarteira(selectedRecord);
	}

}
