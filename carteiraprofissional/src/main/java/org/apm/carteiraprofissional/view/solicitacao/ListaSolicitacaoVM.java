package org.apm.carteiraprofissional.view.solicitacao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.Experiencia;
import org.apm.carteiraprofissional.Formacao;
import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.SolicitarEditarCarteira;
import org.apm.carteiraprofissional.service.CarteiraService;
import org.apm.carteiraprofissional.service.RequisicaoService;
import org.apm.carteiraprofissional.service.RequisitanteService;
import org.apm.carteiraprofissional.service.SolicitarEditarCarteiraService;
import org.apm.carteiraprofissional.utils.UtilizadorUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class ListaSolicitacaoVM {
	private static Logger _log = Logger.getLogger(ListaSolicitacaoVM.class);
	private SolicitarEditarCarteira selectedItem;
	private List<SolicitarEditarCarteira> dataSet;

	@WireVariable
	protected SolicitarEditarCarteiraService solicitarEditarCarteiraService;

	@WireVariable
	protected RequisitanteService requisitanteService;

	@WireVariable
	protected RequisicaoService requisicaoService;

	@WireVariable
	protected CarteiraService carteiraService;

	@Wire
	private Window frmListaSolicitacao;

	public Window getFrmListaSolicitacao() {
		return frmListaSolicitacao;
	}

	public void setFrmListaSolicitacao(Window frmListaSolicitacao) {
		this.frmListaSolicitacao = frmListaSolicitacao;
	}

	public SolicitarEditarCarteira getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(SolicitarEditarCarteira selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<SolicitarEditarCarteira> getDataSet() {
		return dataSet;
	}

	public void setDataSet(List<SolicitarEditarCarteira> dataSet) {
		this.dataSet = dataSet;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		dataSet = solicitarEditarCarteiraService.getAll(false);

	}

	private void aceitarSolicitacao(SolicitarEditarCarteira solicitacao,
			boolean nova) {
		solicitacao.setAutorizada(true);
		solicitacao.setAutorizadaPor(UtilizadorUtils.getLogedUser());
		solicitacao.setTratada(true);
		solicitacao.setDataAutorizacao(new Date());

		Carteira carteira = solicitacao.getCarteira();

		Requisicao requisicao = carteira.getRequisicao();

		requisicao.setLockEdit(false);

		if (nova) {
			requisicao.setAceite(false);
			requisicao.setAceitePor(null);
			requisicao.setAssinouCompromisso(false);
			requisicao.setCompleta(false);
			requisicao.setConcordaTermos(false);
			requisicao.setDataAceite(null);
			requisicao.setDataRequisiao(null);
			requisicao.setJustificacaoAceitacao(null);
			requisicao.setLocalRequisicao(null);
			requisicao.setTemCarteira(false);

			
			carteira.setObservacao("Expirada. E já foi solicitada nova carteira");
			carteira.setAnulado(true);
			carteira.setMotivoAnulacao("EXPIRADA");
			carteira.setDataAnulado(new Date());
		} else {
			carteira.setEmitida(false);
			carteira.setEnviarEmissao(false);
			carteira.setLockEdit(false);
			carteira.setObservacao("RE-IMPRESSÃO, ALTERACAO DADOS");
		}

		Requisitante requisitante = requisitanteService
				.getRequisitanteById(requisicao.getRequisitante().getId());

		requisitante.setLockEdit(false);

		for (Experiencia e : requisitante.getExperiencias()) {
			e.setLockEdit(false);
		}

		for (Formacao f : requisitante.getFormacoes()) {
			f.setLockEdit(false);
		}

		solicitarEditarCarteiraService.saveOrUpdate(solicitacao);
		requisitanteService.saveRequisitante(requisitante);
		requisicaoService.saveRequisicao(requisicao);
		carteiraService.saveCarteira(carteira);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	public void onEdit(
			@BindingParam("userRecord") final SolicitarEditarCarteira solicitacao) {

		Messagebox.show("Pretende aceitar esta solicitação?",
				"Aceitar Solicitação", Messagebox.YES | Messagebox.NO,
				Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {

						if (evt.getName().equals("onYes")) {
							try {

								if (solicitacao.getMotivo().equalsIgnoreCase(
										"ALTERACAO")) {
									aceitarSolicitacao(solicitacao, false);

									dataSet = solicitarEditarCarteiraService
											.getAll(false);

									BindUtils.postNotifyChange(null, null,
											ListaSolicitacaoVM.this, "dataSet");
									Clients.showNotification("Solicitação aceite, agora podera editar os dados e submeter e re-impressão:"
											+ solicitacao.getCarteira()
													.getNumeroCarteira());
								} else if (solicitacao.getMotivo()
										.equalsIgnoreCase("PERCA")) {
									solicitacao.setAutorizada(true);
									solicitacao
											.setAutorizadaPor(UtilizadorUtils
													.getLogedUser());
									solicitacao.setTratada(true);
									solicitacao.setDataAutorizacao(new Date());

									Carteira carteira = solicitacao
											.getCarteira();
									carteira.setEmitida(false);
									carteira.setObservacao("RE-IMPRESSÃO, PERCA");

									solicitarEditarCarteiraService
											.saveOrUpdate(solicitacao);
									carteiraService.saveCarteira(carteira);

									dataSet = solicitarEditarCarteiraService
											.getAll(false);

									BindUtils.postNotifyChange(null, null,
											ListaSolicitacaoVM.this, "dataSet");
									Clients.showNotification("Solicitação aceite, e já foi dada a ordem a gráfica para re-impressão:"
											+ solicitacao.getCarteira()
													.getNumeroCarteira());

								} else {
									aceitarSolicitacao(solicitacao, true);

									dataSet = solicitarEditarCarteiraService
											.getAll(false);

									BindUtils.postNotifyChange(null, null,
											ListaSolicitacaoVM.this, "dataSet");
									Clients.showNotification("Solicitação aceite, Será necessário actualizar os dados da requisição: "
											+ solicitacao.getCarteira()
													.getRequisicao()
													.getNumeroRequisicao());
								}

							} catch (Exception e) {
								_log.debug(e);
								Clients.showNotification("Não foi possível aceitar as alterações da carteira: "
										+ solicitacao.getCarteira()
												.getNumeroCarteira());
							}

						} else {
							solicitacao.setAutorizada(false);
							solicitacao.setAutorizadaPor(UtilizadorUtils
									.getLogedUser());
							solicitacao.setTratada(true);
							solicitacao.setDataAutorizacao(new Date());
							solicitarEditarCarteiraService
									.saveOrUpdate(solicitacao);

							dataSet = solicitarEditarCarteiraService
									.getAll(false);

							BindUtils.postNotifyChange(null, null,
									ListaSolicitacaoVM.this, "dataSet");
							Clients.showNotification("Solicitação tratada e não aceite:"
									+ solicitacao.getCarteira()
											.getNumeroCarteira());

						}

					}
				});

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	@NotifyChange("dataSet")
	public void onDelete(
			@BindingParam("userRecord") final SolicitarEditarCarteira solicitacao) {
		Messagebox.show(
				"Confirma que pretende apagar a solicitacao de edição da carteira: "
						+ solicitacao.getCarteira().getNumeroCarteira() + "?",
				"Confirmar Apagar", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onOK")) {
							try {

								solicitarEditarCarteiraService
										.delete(solicitacao);

								dataSet.remove(solicitacao);
								BindUtils.postNotifyChange(null, null,
										ListaSolicitacaoVM.this, "dataSet");
								Clients.showNotification("Solicitação de edição da carteira: "
										+ solicitacao.getCarteira()
												.getNumeroCarteira()
										+ " apagada com sucesso");

							} catch (Exception e) {
								_log.debug(e);
								Clients.showNotification("Não foi possível apagar a solicitacao: "
										+ solicitacao.getCarteira()
												.getNumeroCarteira()
										+ ". Está em uso");
							}

						}
					}
				});

	}

	@GlobalCommand
	@NotifyChange("dataSet")
	public void refreshvalues(
			@BindingParam("returnvalue") List<SolicitarEditarCarteira> dataSet) {
		this.dataSet = dataSet;
	}

}
