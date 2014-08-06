package org.apm.carteiraprofissional.view.carteira;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.FormaPagamento;
import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.service.CarteiraService;
import org.apm.carteiraprofissional.service.FormaPagamentoService;
import org.apm.carteiraprofissional.utils.BarcodeUtil;
import org.apm.carteiraprofissional.utils.PathUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.Images;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;

public class CarteiraVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Carteira selectedRecord;
	private List<FormaPagamento> formasPagamento;
	private boolean makeAsReadOnly;
	private String recordMode;

	@Wire
	private Datebox dataValidade;

	@Wire
	private Datebox dataEmissao;

	@Wire
	private Checkbox enviarEmissao;

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

	public boolean getMakeAsReadOnly() {
		return isMakeAsReadOnly();
	}

	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
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

	public Checkbox getEnviarEmissao() {
		return enviarEmissao;
	}

	public void setEnviarEmissao(Checkbox enviarEmissao) {
		this.enviarEmissao = enviarEmissao;
	}

	@SuppressWarnings("unchecked")
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		final HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("carteiraValues");
		formasPagamento = formaPagamentoService.getAllFormas();
		if (map != null) {
			this.recordMode = (String) map.get("recordMode");
			if (this.recordMode.equalsIgnoreCase("NEW")) {
				Requisicao requisicao = (Requisicao) map.get("requisicao");
				selectedRecord = new Carteira();
				selectedRecord.setRequisicao(requisicao);
			}
			if (this.recordMode.equalsIgnoreCase("EDIT")) {
				selectedRecord = (Carteira) map.get("selectedRecord");
			}
			if (this.recordMode.equalsIgnoreCase("VIEW")) {
				selectedRecord = (Carteira) map.get("selectedRecord");
				setMakeAsReadOnly(true);
			}

		}

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
	public void saveThis() throws Exception {
		if (this.recordMode.equalsIgnoreCase("NEW")) {
			String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWYXZ";
			Random r = new Random();

			char letraFinal = alfabeto.charAt(r.nextInt(alfabeto.length()));

			String numeroCarteira = this.selectedRecord.getRequisicao()
					.getNumeroRequisicao();
			numeroCarteira += letraFinal;
			this.selectedRecord.setNumeroCarteira(numeroCarteira);

			String valorPDF417 = numeroCarteira + "-NOM:";
			valorPDF417 += selectedRecord.getRequisicao().getRequisitante()
					.getNomeCompleto();
			valorPDF417 += "-EMI:" + selectedRecord.getDataEmissao() + "-VAL:"
					+ selectedRecord.getDataValidade();
			// + "-CATG:"
			// + selectedRecord.getRequisicao().getRequisitante()
			// .getCategoria().getDesignacao();

			BufferedImage pdf417 = BarcodeUtil.encodePDF417(valorPDF417);

			selectedRecord.setPdf417(Images.encode("pdf417.png", pdf417)
					.getByteData());

			this.selectedRecord.setUuid(UUID.randomUUID().toString());
			this.selectedRecord.setEmitida(false);
			// this.selectedRecord.setEnviarEmissao(true);
			this.selectedRecord.setDataValidade(dataValidade.getValue());
			this.selectedRecord.setDataCriacao(new Date());
			// this.selectedRecord.setCriadoPor(criadoPor);

			if (enviarEmissao.isChecked())
				this.selectedRecord.setEnviarEmissao(true);

			ImageIO.write(pdf417, "PNG", new File(PathUtils.getWebInfPath()
					+ "/pdf417/" + this.selectedRecord.getNumeroCarteira()
					+ ".png"));

		} else {
			if (this.recordMode.equalsIgnoreCase("EDIT")) {
				this.selectedRecord.setDataAlteracao(new Date());
				// this.selectedRecord.setAlteradoPor(alteradoPor);

				if (enviarEmissao.isChecked())
					this.selectedRecord.setEnviarEmissao(true);
				this.selectedRecord.setDataValidade(dataValidade.getValue());
			}
		}
		carteiraService.saveCarteira(selectedRecord);

	}

}
