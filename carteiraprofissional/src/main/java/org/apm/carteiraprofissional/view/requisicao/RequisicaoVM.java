package org.apm.carteiraprofissional.view.requisicao;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.NumeroRequisicao;
import org.apm.carteiraprofissional.PropriedadesGlobais;
import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.service.NumeroRequisicaoService;
import org.apm.carteiraprofissional.service.PropriedadesGlobaisService;
import org.apm.carteiraprofissional.service.RequisicaoService;
import org.apm.carteiraprofissional.service.RequisitanteService;
import org.apm.carteiraprofissional.utils.EnviarEmail;
import org.apm.carteiraprofissional.utils.PageUtils;
import org.apm.carteiraprofissional.utils.PathUtils;
import org.apm.carteiraprofissional.utils.PropriedadeGlobalUtils;
import org.apm.carteiraprofissional.utils.UtilizadorUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class RequisicaoVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Requisicao selectedRecord;
	private boolean makeAsReadOnly;
	private String recordMode;
	private Requisitante requisitante;

	private static Logger log = Logger.getLogger(RequisicaoVM.class);

	@WireVariable
	protected RequisitanteService requisitanteService;

	@WireVariable
	protected RequisicaoService requisicaoService;

	@WireVariable
	protected NumeroRequisicaoService numeroRequisicaoService;

	@WireVariable
	protected PropriedadesGlobaisService propriedadesGlobaisService;

	@Wire
	private Window requisicao;

	@Wire
	Datebox dataRequisiao;

	@Wire
	Textbox localRequisicao;

	@Wire
	Checkbox assinouCompromisso;

	@Wire
	Checkbox concordaTermos;

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

		validate();

		if (requisitante.getId() == null) {
			requisitante.setUuid(UUID.randomUUID().toString());
			requisitante.setDataCriacao(new Date());
			requisitante.setCriadoPor(UtilizadorUtils.getLogedUser());
		}

		if (this.selectedRecord.getRequisicaoId() == null) {
			this.selectedRecord.setUuid(UUID.randomUUID().toString());
			this.selectedRecord.setDataCriacao(new Date());
			this.selectedRecord.setCriadoPor(UtilizadorUtils.getLogedUser());

			List<NumeroRequisicao> numeros = numeroRequisicaoService
					.getAllNumeros();
			NumeroRequisicao numero;
			if (numeros != null && numeros.size() > 0) {
				numero = numeros.get(0);
				Integer ano = numero.getAno();
				Integer numInt = numero.getNumero();
				if (ano != Calendar.getInstance().get(Calendar.YEAR)) {
					ano = Calendar.getInstance().get(Calendar.YEAR);
					numero.setAno(ano);
					numInt = 0;
				}
				numInt++;
				String valorStr = "";
				if (numInt < 10) {
					valorStr = "1000" + numInt;
				} else if (numInt < 100) {
					valorStr = "100" + numInt;
				} else if (numInt < 1000) {
					valorStr = "10" + numInt;
				} else if (numInt < 10000) {
					valorStr = "1" + numInt;
				} else {
					valorStr = numInt.toString();
				}

				numero.setDataEmissao(new Date());
				numero.setNumero(numInt);
				numero.setNumeroReal(ano + valorStr);

			} else {
				numero = new NumeroRequisicao();
				numero.setAno(Calendar.getInstance().get(Calendar.YEAR));
				numero.setDataEmissao(new Date());
				numero.setNumero(1);
				numero.setUuid(UUID.randomUUID().toString());
				numero.setNumeroReal(numero.getAno() + "1000"
						+ numero.getNumero());
			}

			numeroRequisicaoService.saveNumeroRequisicao(numero);
			this.selectedRecord.setNumeroRequisicao(numero.getNumeroReal());

			requisitanteService.saveRequisitante(requisitante);

			this.selectedRecord.setRequisitante(requisitante);

			requisicaoService.saveRequisicao(selectedRecord);

			String dataDirPath = PathUtils.getEnvDataDir() + "/datareal/"
					+ this.selectedRecord.getNumeroRequisicao();

			File dataDir = new File(dataDirPath);

			if (!dataDir.exists())
				dataDir.mkdir();

			PropriedadesGlobais emailApmFrom = PropriedadeGlobalUtils
					.getEmailAPM();

			if (emailApmFrom != null && requisitante.getEmail() != null
					&& !requisitante.getEmail().isEmpty()) {
				String subject = "Requisicao de Carteira Profissional - APM: "
						+ requisitante.getNome();
				String message = "Registamos a sua requisicao de carteira profissional no nosso sistema. \n\n";
				message += "Se pretender efectual alguma consulta dentro do nosso sistema, o número da requisição é: \n\n";
				message += selectedRecord.getNumeroRequisicao() + "\n\n";
				message += "Obrigado. \n\n A Gerência.";

				try {
					EnviarEmail.sendEmail(emailApmFrom.getValor(),
							emailApmFrom.getValor2(), requisitante.getEmail(),
							subject, message);
					PageUtils
							.redirectTo("/pages/anonimo/requisicao/startSearch.zul");
					Clients.showNotification("Requisição Registada e um email foi enviado com o numero de requisicao.");
				} catch (Exception e) {
					log.debug(e.getStackTrace());
					PageUtils
							.redirectTo("/pages/anonimo/requisicao/startSearch.zul");
					Clients.showNotification("Requisição Registada mas não foi possível enviar email com o numero de requisição.");
				}
			}

		} else {
			requisitante.setAlteradoPor(UtilizadorUtils.getLogedUser());
			requisitante.setDataAlteracao(new Date());
			requisitanteService.saveRequisitante(requisitante);
			requisicaoService.saveRequisicao(selectedRecord);
			PageUtils.redirectTo("/pages/anonimo/requisicao/startSearch.zul");
			Clients.showNotification("Requisição Actualizada.");
		}

	}

	@Command("fechar")
	public void close() {

		requisicao.detach();
	}

	public void validate() {
		if (dataRequisiao == null || dataRequisiao.getValue() == null) {
			throw new WrongValueException(dataRequisiao,
					"Data de requisição é um campo obrigatório");
		}
		if (localRequisicao == null || localRequisicao.getValue().isEmpty()) {

			throw new WrongValueException(localRequisicao,
					"Local de requisição deve ser preenchido");
		}

		if (assinouCompromisso == null || !assinouCompromisso.isChecked()) {
			throw new WrongValueException(assinouCompromisso,
					"Deve indicar que assinou o termo de compromisso");
		}

		if (concordaTermos == null || !concordaTermos.isChecked()) {
			throw new WrongValueException(concordaTermos,
					"Deve ler e concordar com o regulamento da Carteira Profissional");
		}
	}

}
