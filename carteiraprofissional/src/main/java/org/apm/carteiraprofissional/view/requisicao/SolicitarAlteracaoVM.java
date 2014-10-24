package org.apm.carteiraprofissional.view.requisicao;

import java.util.UUID;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.SolicitarEditarCarteira;
import org.apm.carteiraprofissional.service.CarteiraService;
import org.apm.carteiraprofissional.service.SolicitarEditarCarteiraService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class SolicitarAlteracaoVM {
	private SolicitarEditarCarteira selectedRecord;
	private String numeroCarteira;
	private Carteira carteira;

	@WireVariable
	protected CarteiraService carteiraService;

	@WireVariable
	protected SolicitarEditarCarteiraService solicitarEditarCarteiraService;

	@Wire
	Radiogroup motivo;
	
	@Wire
	Textbox carteiraValidate;
	
	@Wire
	Window frmSolicitaAlteracao;

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

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		this.selectedRecord = new SolicitarEditarCarteira();
	}

	public void validate() {
		if (motivo == null || motivo.getSelectedItem() == null
				|| motivo.getSelectedItem().getValue() == null) {
			throw new WrongValueException(motivo,
					"Deve indicar o motivo da solicitação de alteração da carteira");
		}

		carteira = carteiraService.getCarteiraByNumero(numeroCarteira);
		
		

		if (carteira == null) {
			throw new WrongValueException(carteiraValidate,
					"Deve indicar um numero de carteira válido");
		} else {
			SolicitarEditarCarteira solicitacao = solicitarEditarCarteiraService
					.getByCarteira(carteira, false);
			if (solicitacao != null) {
				throw new WrongValueException(motivo,
						"Existe uma solicitação ainda não tratada para esta carteira");
			}
			
			if(motivo.getSelectedItem().getValue().toString().equalsIgnoreCase("NOVA")){
				if(!carteira.isExpirou()){
					throw new WrongValueException(motivo,
							"Não pode solicitar uma nova carteira enquanto a outra não expirou");
				}
			}

		}

	}
	@Command
	public void saveThis(){
		validate();
		
		this.selectedRecord.setCarteira(carteira);
		if(this.selectedRecord.getSolicitacaoId()==null){
			this.selectedRecord.setUuid(UUID.randomUUID().toString());
		}
		solicitarEditarCarteiraService.saveOrUpdate(selectedRecord);
		
		frmSolicitaAlteracao.detach();
		Clients.showNotification("Solicitação registada. Ela será analisada e tratada");
	}
	@Command
	public void fechar(){		
		frmSolicitaAlteracao.detach();		
	}

}
