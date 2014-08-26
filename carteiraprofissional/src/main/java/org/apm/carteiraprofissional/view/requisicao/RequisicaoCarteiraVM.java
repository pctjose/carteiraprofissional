package org.apm.carteiraprofissional.view.requisicao;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.Categoria;
import org.apm.carteiraprofissional.Escolaridade;
import org.apm.carteiraprofissional.Experiencia;
import org.apm.carteiraprofissional.Formacao;
import org.apm.carteiraprofissional.Pais;
import org.apm.carteiraprofissional.Provincia;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.TipoDocumento;
import org.apm.carteiraprofissional.service.CategoriaService;
import org.apm.carteiraprofissional.service.EscolaridadeService;
import org.apm.carteiraprofissional.service.PaisService;
import org.apm.carteiraprofissional.service.ProvinciaService;
import org.apm.carteiraprofissional.service.RequisitanteService;
import org.apm.carteiraprofissional.service.TipoDocumentoService;
import org.apm.carteiraprofissional.utils.AgeHelper;
import org.apm.carteiraprofissional.utils.Msg;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.Image;
import org.zkoss.image.Images;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

public class RequisicaoCarteiraVM extends SelectorComposer<Component> {

	private static Logger _log = Logger.getLogger(RequisicaoCarteiraVM.class);

	private static final long serialVersionUID = 1L;

	private static String[] PDF_FORMAT = { "application/pdf" };

	private Requisitante selectedRecord;

	private boolean makeAsReadOnly;

	private String recordMode;

	private Experiencia experiencia;

	private Formacao formacao;

	@Wire
	private Image userImage;

	@Wire
	private List<Escolaridade> escolaridades;

	@Wire
	private List<TipoDocumento> tipoDocs;

	@Wire
	private List<Categoria> categorias;

	@Wire
	private List<Experiencia> experienciasAdicionadas;

	@Wire
	private List<Formacao> formacoesAdicionadas;

	private List<Provincia> inProvincias;

	private List<Pais> inPaises;

	@Wire
	Listbox paises;

	@Wire
	Listbox provincias;

	// @WireVariable
	// protected RequisitanteService requisitanteService;

	@WireVariable
	protected EscolaridadeService escolaridadeService;

	@WireVariable
	protected TipoDocumentoService tipoDocumentoService;

	@WireVariable
	protected CategoriaService categoriaService;

	@WireVariable
	protected PaisService paisService;

	@WireVariable
	protected ProvinciaService provinciaService;

	@WireVariable
	protected RequisitanteService requisitanteService;

	// ================= Experiencia =========================//
	@Wire
	Textbox empregador;

	@Wire
	Checkbox actual;

	@Wire
	Textbox empregadorEndereco;

	@Wire
	Textbox empregadorContacto;

	@Wire
	Datebox dataInicial;

	@Wire
	Datebox dataFinal;

	@Wire
	Textbox funcaoExercida;

	@Wire
	Textbox experienciaRelevante;

	// ==================== Formacao ==================//
	@Wire
	Textbox instituicao;

	@Wire
	Textbox localizacao;

	@Wire
	Intbox ano;

	@Wire
	Listbox grauObtido;

	@Wire
	Textbox apelido;

	@Wire
	Textbox nome;

	@Wire
	Radiogroup sexo;

	@Wire
	Radio sexoM;

	@Wire
	Radio sexoF;

	@Wire
	Datebox dataNascimento;

	@Wire
	Listitem lstEscola;

	@Wire
	Listitem li;

	@Wire
	Textbox cidade;

	@Wire
	Textbox endereco;

	@Wire
	Textbox contacto1;

	@Wire
	Textbox email;

	@Wire
	Textbox numeroBi;

	@Wire
	private Window novaRequisicao;

	@Wire
	Datebox dataEmissao;

	@Wire
	Datebox dataValidade;

	@Wire
	Textbox localEmissao;

	@Wire
	Textbox numeroNuit;

	public Requisitante getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(Requisitante selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public Experiencia getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(Experiencia experiencia) {
		this.experiencia = experiencia;
	}

	public Formacao getFormacao() {
		return formacao;
	}

	public void setFormacao(Formacao formacao) {
		this.formacao = formacao;
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

	public Image getUserImage() {
		return userImage;
	}

	public void setUserImage(Image userImage) {
		this.userImage = userImage;
	}

	public List<Escolaridade> getEscolaridades() {
		return escolaridades;
	}

	public void setEscolaridades(List<Escolaridade> escolaridades) {
		this.escolaridades = escolaridades;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<TipoDocumento> getTipoDocs() {
		return tipoDocs;
	}

	public void setTipoDocs(List<TipoDocumento> tipoDocs) {
		this.tipoDocs = tipoDocs;
	}

	public List<Experiencia> getExperienciasAdicionadas() {
		return experienciasAdicionadas;
	}

	public void setExperienciasAdicionadas(
			List<Experiencia> experienciasAdicionadas) {
		this.experienciasAdicionadas = experienciasAdicionadas;
	}

	public List<Formacao> getFormacoesAdicionadas() {
		return formacoesAdicionadas;
	}

	public void setFormacoesAdicionadas(List<Formacao> formacoesAdicionadas) {
		this.formacoesAdicionadas = formacoesAdicionadas;
	}

	public List<Provincia> getProvincias() {
		return inProvincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.inProvincias = provincias;
	}

	public List<Pais> getPaises() {
		return inPaises;
	}

	public void setPaises(List<Pais> paises) {
		this.inPaises = paises;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {

		// Requisitante requisicao;
		Selectors.wireComponents(view, this, false);

		// final HashMap<String, Object> map = (HashMap<String, Object>)
		// Sessions.getCurrent().getAttribute("allmyvalues");

		recordMode = Executions.getCurrent().getParameter("requisicaoMode");

		escolaridades = escolaridadeService.getAllNiveis();

		tipoDocs = tipoDocumentoService.getAllTipoDocumento();

		categorias = categoriaService.getAllCategorias();

		inPaises = paisService.getAllPaises();

		if (inPaises != null && inPaises.size() > 0) {
			inProvincias = provinciaService.getAllProvincia(inPaises.get(0));
		}

		if (recordMode.equalsIgnoreCase("NEW")) {
			this.selectedRecord = new Requisitante();
			experienciasAdicionadas = new ArrayList<Experiencia>();
			formacoesAdicionadas = new ArrayList<Formacao>();
		} else {
			this.selectedRecord = (Requisitante) Sessions.getCurrent()
					.getAttribute("requisitante");

			this.selectedRecord = requisitanteService
					.getRequisitanteById(this.selectedRecord.getId());
			if (this.selectedRecord.getExperiencias() != null)
				experienciasAdicionadas = this.selectedRecord.getExperiencias();
			else
				experienciasAdicionadas = new ArrayList<Experiencia>();

			if (this.selectedRecord.getFormacoes() != null)
				formacoesAdicionadas = this.selectedRecord.getFormacoes();
			else
				formacoesAdicionadas = new ArrayList<Formacao>();
		}
	}

	@Command("upload")
	@NotifyChange("userImage")
	public void upload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		UploadEvent upEvent = (UploadEvent) ctx.getTriggerEvent();

		org.zkoss.util.media.Media media = upEvent.getMedia();
		if (media instanceof org.zkoss.image.Image) {
			// userImage = org.zkoss.image.AImage();
			// userImage.setContent((org.zkoss.image.AImage) media);
			// org.zkoss.image.AImage e = new org.zkoss.image.AImage()

		} else {
			Messagebox.show("Not an image: " + media, "Error", Messagebox.OK,
					Messagebox.ERROR);
		}
		System.out.println("uploading " + upEvent.getMedia().getName());
	}

	@Command
	@NotifyChange("userImage")
	public void getWebCam() throws IOException, InterruptedException {
		Webcam webcam = Webcam.getDefault();

		WebcamPanel panel = new WebcamPanel(webcam);

		panel.setFillArea(true);

		JFrame window = new JFrame("Test webcam panel");
		window.add(panel);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);

		BufferedImage image = webcam.getImage();

		// ImageIO.write(image, "PNG", new File("/resources/teste.png"));

		userImage = Images.encode("Foto.png", image);
		// userImage=new AImage("",image);

		Thread.sleep(300);
		webcam.close();
		window.dispose();

	}

	@Command
	@NotifyChange({ "experiencia", "listaExperiencia",
			"experienciasAdicionadas" })
	public void onAddExperiencia() {

		if (!funcaoExercida.getText().isEmpty()
				&& !experienciaRelevante.getText().isEmpty()
				&& !empregadorEndereco.getText().isEmpty()
				&& !empregadorContacto.getText().isEmpty()) {
			experiencia = new Experiencia();
			experiencia.setEmpregador(empregador.getText());
			experiencia.setActual((Boolean) actual.getValue());
			experiencia.setEmpregadorContacto(empregadorContacto.getText());
			experiencia.setEmpregadorEndereco(empregadorEndereco.getText());
			experiencia.setDataFinal(dataFinal.getValue());
			experiencia.setDataInicial(dataInicial.getValue());
			experiencia.setFuncaoExercida(funcaoExercida.getText());
			experiencia.setExperienciaRelevante(experienciaRelevante.getText());
			experiencia.setUuid(UUID.randomUUID().toString());
			experiencia.setRequisitante(selectedRecord);
			experiencia.setDataRegisto(new Date());

			experienciasAdicionadas.add(experiencia);
			this.selectedRecord.getExperiencias().add(experiencia);
		} else {
			Clients.showNotification("Por favor adicione a experiência");
		}
	}

	@Command
	@NotifyChange({ "formacao", "formacoesAdicionadas" })
	public void onAddFormacao() {

		if (!instituicao.getValue().isEmpty()
				&& !localizacao.getValue().isEmpty()
				&& !ano.getValue().toString().isEmpty()
				&& (Escolaridade) grauObtido.getSelectedItem().getValue() != null) {
			formacao = new Formacao();
			formacao.setAno(ano.getValue());
			formacao.setGrauObtido((Escolaridade) grauObtido.getSelectedItem()
					.getValue());
			formacao.setInstituicao(instituicao.getValue());
			formacao.setLocalizacao(localizacao.getValue());
			formacao.setRequisitante(selectedRecord);
			formacao.setUuid(UUID.randomUUID().toString());
			formacao.setDataRegisto(new Date());

			formacoesAdicionadas.add(formacao);
			this.selectedRecord.getFormacoes().add(formacao);
		} else {
			Clients.showNotification("Por favor adicione a formação acadêmica e profissional");
		}
	}

	@Command
	@NotifyChange("experienciasAdicionadas")
	public void onRemoveExperiencia(@BindingParam("expRemoved") Experiencia exp) {
		if (exp.getId() != null) {
			//
		} else {
			for (Experiencia e : experienciasAdicionadas) {
				if (e.equals(exp)) {
					experienciasAdicionadas.remove(e);
					this.selectedRecord.getExperiencias().remove(e);
					return;
				}

			}
		}
	}

	@Command
	@NotifyChange("formacoesAdicionadas")
	public void onRemoveFormacao(@BindingParam("eduRemoved") Formacao edu) {
		if (edu.getId() != null) {
			//
		} else {
			for (Formacao f : formacoesAdicionadas) {
				if (f.equals(edu)) {
					formacoesAdicionadas.remove(edu);
					this.selectedRecord.getFormacoes().remove(edu);
					return;
				}

			}
		}
	}

	@Command
	public void changePais() {

		li = paises.getSelectedItem();
		if (li != null) {
			Pais selectedPais = (Pais) li.getValue();
			inProvincias = provinciaService.getAllProvincia(selectedPais);

			BindUtils.postNotifyChange(null, null, this, "provincias");
		}

	}

	@Command
	public void saveThis() {

		validate();

		// List<Msg> errors = validateInput();

		// if (errors.isEmpty()) {

		/*
		 * System.out.println("Formacoes:"+selectedRecord.getFormacoes().size());
		 * System
		 * .out.println("Experiencias:"+selectedRecord.getExperiencias().size
		 * ()); requisitanteService.saveRequisitante(selectedRecord);
		 * 
		 * System.out.println("ID GRAVADO: "+selectedRecord.getId());
		 */

		Sessions.getCurrent().setAttribute("requisitante", selectedRecord);
		// Executions.sendRedirect("/pages/anonimo/requisicao/Requisicao.zul");

		Window window = (Window) Executions.createComponents(
				"/pages/anonimo/requisicao/Requisicao.zul", null, null);

		window.doModal();

		// } else {

		/*
		 * try {
		 * 
		 * MessageBoxHelper.showFormError(errors,novaRequisicao); } catch
		 * (InterruptedException e) {
		 * _log.info("Nao foi possivel mostrar a lista de erros...");
		 * e.printStackTrace(); } }
		 */
	}

	public boolean validate() {

		if (apelido.getValue().isEmpty()) {
			throw new WrongValueException(apelido,
					"Apelido é um campo obrigatório");
		}

		if (nome.getValue().isEmpty()) {
			throw new WrongValueException(nome, "Nome é um campo obrigatório");
		}

		if (dataNascimento.getValue() == null) {
			throw new WrongValueException(dataNascimento,
					"Data de Nascimento é um campo obrigatório");
		}

		Calendar cal = Calendar.getInstance();

		cal.setTime(dataNascimento.getValue());

		int age = AgeHelper.calculateMyAge(cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

		if (age < 18) {
			throw new WrongValueException(dataNascimento,
					"A idade não deve ser inferior a 18");
		}

		if (li == null) {
			throw new WrongValueException(paises, "Pais é um campo obrigatorio");
		}

		if (selectedRecord.getProvincia() == null) {
			throw new WrongValueException(provincias,
					"Provincia é um campo obrigatorio");
		}

		if (cidade.getText().isEmpty()) {
			throw new WrongValueException(cidade,
					"Cidade é um campo obrigatorio");
		}

		if (endereco.getText().isEmpty()) {
			throw new WrongValueException(endereco,
					"Endereco é um campo obrigatorio");
		}

		if (contacto1.getText().isEmpty()) {
			throw new WrongValueException(contacto1,
					"Contacto é um campo obrigatorio");
		}

		if (email.getText().isEmpty()) {
			throw new WrongValueException(email, "Email é um campo obrigatorio");
		}

		if (numeroBi.getText().isEmpty()) {
			throw new WrongValueException(numeroBi,
					"Nº Doc Ident. é um campo obrigatorio");
		}

		if (dataEmissao.getValue() == null) {
			throw new WrongValueException(dataEmissao,
					"Data Emissão é um campo obrigatorio");
		}

		if (dataValidade.getValue() == null) {
			throw new WrongValueException(dataValidade,
					"Data Validade é um campo obrigatorio");
		}

		if (localEmissao.getValue().isEmpty()) {
			throw new WrongValueException(localEmissao,
					"Local Emissão é um campo obrigatorio");
		}

		if (instituicao.getValue().isEmpty()) {
			throw new WrongValueException(instituicao,
					"Instituição é um campo obrigatorio");
		}

		if (localizacao.getValue().isEmpty()) {
			throw new WrongValueException(localizacao,
					"Localização é um campo obrigatorio");
		}

		if (ano.getValue().toString().isEmpty()) {
			throw new WrongValueException(ano, "Ano é um campo obrigatorio");
		}

		if (empregador.getValue().isEmpty()) {
			throw new WrongValueException(empregador,
					"Empregador é um campo obrigatorio");
		}

		if (funcaoExercida.getText().isEmpty()) {
			throw new WrongValueException(funcaoExercida,
					"Função é um campo obrigatorio");
		}

		if (experienciaRelevante.getValue().isEmpty()) {
			throw new WrongValueException(experienciaRelevante,
					"Experiência Relevante é um campo obrigatorio");
		}

		return true;
	}

	public List<Msg> validateInput() {

		List<Msg> errors = new ArrayList<Msg>();

		int errorId = 0;

		if (selectedRecord.getEscolaridade() == null) {

			Msg error = new Msg();
			error.setId(++errorId);
			error.setTxt("O Nivel Acadêmico é um campo obrigatório");
			error.setHlp("Selecione um nível academico");
			errors.add(error);
		}

		if (selectedRecord.getTipoDoc() == null) {

			Msg error = new Msg();
			error.setId(++errorId);
			error.setTxt("Tipo de Identificação é um campo obrigatório");
			error.setHlp("Selecione um Tipo de Identificação");
			errors.add(error);

		}

		Date _dataEmissao = dataEmissao.getValue();

		Date _dataValidade = dataValidade.getValue();

		if (_dataEmissao.compareTo(_dataValidade) > 0) {

			Msg error = new Msg();
			error.setId(++errorId);
			error.setTxt("A Data Emissão não deve ser maior que a Data de Validade");
			error.setHlp("Selecione uma Data Emissão menor que a Data de Validade");
			errors.add(error);
		}

		if (formacao.getGrauObtido() == null) {

			Msg error = new Msg();
			error.setId(++errorId);
			error.setTxt("O Grau é um campo obrigatório");
			error.setHlp("Selecione um grau");
			errors.add(error);
		}

		if (selectedRecord.getCategoria() == null) {

			Msg error = new Msg();
			error.setId(++errorId);
			error.setTxt("Categoria é um campo obrigatório");
			error.setHlp("Selecione uma categoria");
			errors.add(error);
		}

		Date _dataInicial = dataInicial.getValue();

		Date _dataFinal = dataFinal.getValue();

		if (_dataInicial.compareTo(_dataFinal) > 0) {

			Msg error = new Msg();
			error.setId(++errorId);
			error.setTxt("A Data Inicio não deve ser maior que a Data Final");
			error.setHlp("Selecione uma Data Inicio menor que a Data Final");
			errors.add(error);
		}

		return errors;
	}

	@Command
	@NotifyChange({ "disableDataFinal" })
	public void disableDataFinal() {
		if (actual.isChecked()) {
			dataFinal.setReadonly(Boolean.TRUE);
			dataFinal.setDisabled(Boolean.TRUE);
		} else {
			dataFinal.setReadonly(Boolean.FALSE);
			dataFinal.setDisabled(Boolean.FALSE);
		}
	}

	@Command
	public void onUploadPDF(
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		_log.info("upload doc called...");

		UploadEvent upEvent = null;

		Object objUploadEvent = ctx.getTriggerEvent();

		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}

		if (upEvent != null) {
			Media media = upEvent.getMedia();

			if (Arrays.binarySearch(PDF_FORMAT, media.getContentType()) >= 0) {

				// selectedRecord = new Requisitante();
				selectedRecord.setNumeroDoc(numeroBi.getValue());
				selectedRecord.setDataEmissao(dataEmissao.getValue());
				selectedRecord.setDataValidade(dataValidade.getValue());
				selectedRecord.setLocalEmissao(localEmissao.getValue());
				selectedRecord.setCopiaDoc(media.getByteData());
				selectedRecord.setNumeroNuit(numeroNuit.getValue());
				selectedRecord.setTipoDoc(selectedRecord.getTipoDoc());

			}
		}
	}
}
