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
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.Utilizador;
import org.apm.carteiraprofissional.service.CarteiraService;
import org.apm.carteiraprofissional.service.FormaPagamentoService;
import org.apm.carteiraprofissional.service.RequisicaoService;
import org.apm.carteiraprofissional.service.RequisitanteService;
import org.apm.carteiraprofissional.utils.BarcodeUtil;
import org.apm.carteiraprofissional.utils.DateUtil;
import org.apm.carteiraprofissional.utils.EnviarEmail;
import org.apm.carteiraprofissional.utils.PathUtils;
import org.apm.carteiraprofissional.utils.WritePDFCarteira;
import org.apm.carteiraprofissional.utils.ZipDirUtil;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.Images;
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
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Window;

public class CarteiraVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Carteira selectedRecord;
	private List<FormaPagamento> formasPagamento;
	private boolean makeAsReadOnly;
	private String recordMode;
	private Requisicao requisicao;
	private Utilizador logedInUser;

	@Wire
	private Window frmCriarCarteira;

	@Wire
	private Datebox dataValidade;

	@Wire
	private Datebox dataEmissao;

	@Wire
	private Checkbox enviarEmissao;
	
	@Wire
	private Decimalbox valorCobrado;

	@WireVariable
	protected FormaPagamentoService formaPagamentoService;

	@WireVariable
	protected CarteiraService carteiraService;

	@WireVariable
	private RequisicaoService requisicaoService;

	@WireVariable
	protected RequisitanteService requisitanteService;

	public Window getFrmCriarCarteira() {
		return frmCriarCarteira;
	}

	public void setFrmCriarCarteira(Window frmCriarCarteira) {
		this.frmCriarCarteira = frmCriarCarteira;
	}

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
	
	

	public Decimalbox getValorCobrado() {
		return valorCobrado;
	}

	public void setValorCobrado(Decimalbox valorCobrado) {
		this.valorCobrado = valorCobrado;
	}

	@SuppressWarnings("unchecked")
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		final HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("carteiraValues");
		formasPagamento = formaPagamentoService.getAllFormas();
		logedInUser= (Utilizador)Sessions.getCurrent().getAttribute("utilizador");
		if (map != null) {
			this.recordMode = (String) map.get("recordMode");
			if (this.recordMode.equalsIgnoreCase("NEW")) {
				requisicao = (Requisicao) map.get("requisicao");
				selectedRecord = new Carteira();
				selectedRecord.setRequisicao(requisicao);
			}
			if (this.recordMode.equalsIgnoreCase("EDIT")) {
				selectedRecord = (Carteira) map.get("selectedRecord");
				requisicao = selectedRecord.getRequisicao();
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
		
		if (this.selectedRecord.getRequisicao().getRequisitante()
				.isMembro()) {
			calendario.add(Calendar.YEAR, 3);
		} else {
			calendario.add(Calendar.YEAR, 2);
		}

		dataValidade.setValue(calendario.getTime());
		this.selectedRecord.setDataValidade(calendario.getTime());

	}

	@Command
	public void saveThis() throws Exception {

		validate();
		
		
		Requisitante requisitante = requisitanteService
				.getRequisitanteById(this.selectedRecord.getRequisicao()
						.getRequisitante().getId());

		String dataDirectory = PathUtils.getWebInfPath() + "/data/"
				+ this.requisicao.getNumeroRequisicao();
		String dataZipDir = PathUtils.getWebInfPath() + "/data/datazip";

		if (this.recordMode.equalsIgnoreCase("NEW")) {
			String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWYXZ";
			Random r = new Random();

			char letraFinal = alfabeto.charAt(r.nextInt(alfabeto.length()));

			String numeroCarteira = this.selectedRecord.getRequisicao()
					.getNumeroRequisicao();
			numeroCarteira += letraFinal;
			this.selectedRecord.setNumeroCarteira(numeroCarteira);
			this.selectedRecord.setUuid(UUID.randomUUID().toString());
			this.selectedRecord.setEmitida(false);
			this.selectedRecord.setDataCriacao(new Date());
			this.selectedRecord.setCriadoPor(logedInUser);

		} else {
			if (this.recordMode.equalsIgnoreCase("EDIT")) {
				this.selectedRecord.setDataAlteracao(new Date());
				this.selectedRecord.setAlteradoPor(logedInUser);
			}
		}

		if (enviarEmissao.isChecked()) {
			this.selectedRecord.setEnviarEmissao(true);
			this.requisicao.setLockEdit(Boolean.TRUE);
		} else {
			this.selectedRecord.setEnviarEmissao(false);
			this.requisicao.setLockEdit(Boolean.FALSE);
		}
		this.selectedRecord.setDataValidade(dataValidade.getValue());

		String valorPDF417 = this.selectedRecord.getNumeroCarteira() + "_";
		valorPDF417 += requisitante.getNomeCompleto();
		valorPDF417 += "_" + DateUtil.ptDate(requisitante.getDataNascimento())
				+ "_" + requisitante.getSexo();

		if (requisitante.getCategoria() != null) {
			valorPDF417 += "_" + requisitante.getCategoria().getDesignacao();
		}

		valorPDF417 += "_" + DateUtil.ptDate(selectedRecord.getDataEmissao())
				+ "_" + DateUtil.ptDate(selectedRecord.getDataValidade()) + "_"
				+ requisitante.getNumeroDoc() + "_"
				+ requisitante.getTipoDoc().getDesignacao();
		// Produzir o PDF417
		BufferedImage pdf417 = BarcodeUtil.encodePDF417(valorPDF417);

		selectedRecord.setPdf417(Images.encode("pdf417.png", pdf417)
				.getByteData());
		// Colocar a Imagem PDF417 no directorio /WEB-INF/data/
		File dataDir = new File(dataDirectory);
		if (dataDir.exists()) {
			ImageIO.write(pdf417, "PNG", new File(dataDirectory + "/"
					+ this.selectedRecord.getNumeroCarteira() + ".png"));
		} else {
			if (dataDir.mkdir()) {
				ImageIO.write(pdf417, "PNG", new File(dataDirectory + "/"
						+ this.selectedRecord.getNumeroCarteira() + ".png"));
			}
		}

		// Produzir o tamplate PDF guardar na pasta WEB-INF/data e colocar
		this.selectedRecord.setTamplate(WritePDFCarteira.produzirPDFCarteira(
				selectedRecord, requisitante));

		requisicao.setTemCarteira(true);

		carteiraService.saveCarteira(selectedRecord);

		requisicaoService.saveRequisicao(requisicao);

		if (this.selectedRecord.isEnviarEmissao()) {

			try {
				Clients.showBusy("Enviando email com dados para produção da carteira");
				ZipDirUtil.zipDir(dataDirectory, dataZipDir + "/"
						+ this.selectedRecord.getNumeroCarteira() + ".zip");

				String sms = "A APM Solicita a V.Excia a produção da carteira com o número: "
						+ this.selectedRecord.getNumeroCarteira() + "\n\n";
				sms += "Os outros dados da carteira encontram-se no anexo deste email. \n\n";
				sms += "Obrigado.";

				EnviarEmail.sendEmail(
						"circlemz2@yahoo.com",
						"Envio de Dados Para Produção de Carteira: "
								+ this.selectedRecord.getNumeroCarteira(),
						sms,
						dataZipDir + "/"
								+ this.selectedRecord.getNumeroCarteira()
								+ ".zip");
				Clients.clearBusy();
			} catch (Exception e) {
				Clients.clearBusy();
				Clients.showNotification("Dados gravados com sucesso. Mas houve erro no envio de email com dados para a produção da carteira");
			}

		}	

		
		Clients.showNotification("Carteira: "
				+ this.selectedRecord.getNumeroCarteira()
				+ " Registada/Actualizada com sucesso");
		
		frmCriarCarteira.detach();
	}

	@Command
	public void cancel() {
		frmCriarCarteira.detach();
	}
	
	public void validate(){
		if(dataEmissao.getValue()==null || dataEmissao.toString().isEmpty()){
			throw new WrongValueException(dataEmissao, "A data de emissão deve ser preenchida");
		}
		
		if(valorCobrado.getValue()==null ){
			throw new WrongValueException(valorCobrado, "O valor cobrado deve ser preenchido");
		}
		
		//return true;
	}
}
