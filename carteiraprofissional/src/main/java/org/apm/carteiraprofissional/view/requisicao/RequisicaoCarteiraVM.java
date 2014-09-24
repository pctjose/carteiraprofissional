package org.apm.carteiraprofissional.view.requisicao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import org.apm.carteiraprofissional.utils.PageUtils;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

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

	@Wire
	Listbox categoria;

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

	@Wire
	Checkbox membro;

	@Wire
	Textbox numeroMembro;

	@Wire
	Tab tabDadosPessoas;

	@Wire
	Tab tabFormacao;

	@Wire
	Tab tabExperiencia;

	@Wire
	Listbox escolaridadesLista;

	@Wire
	Listbox tipoDocsLista;

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

		formacao = new Formacao();
		experiencia = new Experiencia();

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

	@Command
	@NotifyChange({ "experiencia", "listaExperiencia",
			"experienciasAdicionadas" })
	public void onAddExperiencia() {

		if (empregador == null || empregador.getValue().trim().isEmpty()) {
			throw new WrongValueException(empregador,
					"O Campo Empregador deve ser preenchido");
		}
		if (categoria == null || categoria.getSelectedItem() == null
				|| categoria.getSelectedItem().getValue() == null) {
			throw new WrongValueException(categoria,
					"Deve seleccionar a Categoria exercida");
		}
		if (dataInicial == null || dataInicial.getValue() == null) {
			throw new WrongValueException(dataInicial,
					"Deve indicar a data de inicio de trabalhos com este empregador");
		}
		if (funcaoExercida == null
				|| funcaoExercida.getValue().trim().isEmpty()) {
			throw new WrongValueException(funcaoExercida,
					"A Função exercida deve ser preenchida");
		}
		if (experienciaRelevante == null
				|| experienciaRelevante.getValue().trim().isEmpty()) {
			throw new WrongValueException(experienciaRelevante,
					"Deve indicar as experiencias relevantes com este empregador");
		}
		if (actual.isChecked()) {
			for (Experiencia e : experienciasAdicionadas) {
				if (e.isActual()) {
					throw new WrongValueException(actual,
							"Existe uma experiencia marcada como actual na lista");
				}
			}
		}

		experiencia = new Experiencia();
		experiencia.setEmpregador(empregador.getText());
		experiencia.setActual(actual.isChecked());
		// experiencia.setActual((Boolean) actual.getValue());
		experiencia.setCategoria((Categoria) categoria.getSelectedItem()
				.getValue());
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

	}

	@Command
	@NotifyChange({"formacoesAdicionadas" })
	public void onAddFormacao() {

		if (instituicao == null || instituicao.getValue().trim().isEmpty()) {
			throw new WrongValueException(instituicao,
					"O Campo Instituição deve ser preenchido");
		}

		if (localizacao == null || localizacao.getValue().trim().isEmpty()) {
			throw new WrongValueException(localizacao,
					"O Campo Localização deve ser preenchido");
		}

		if (ano == null || ano.getValue() == null || ano.getValue() < 1900
				|| ano.getValue() > Calendar.getInstance().get(Calendar.YEAR)) {
			throw new WrongValueException(ano,
					"O campo 'Ano' deve ser preechido ou foi preenchido incorrectamente");
		}

		if (grauObtido == null || grauObtido.getSelectedItem() == null
				|| grauObtido.getSelectedItem().getValue() == null) {
			throw new WrongValueException(grauObtido,
					"Deve seleccionar o Grau obtido");
		}

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
		
		
	}

	@Command
	@NotifyChange("experienciasAdicionadas")
	public void onRemoveExperiencia(@BindingParam("expRemoved") Experiencia exp) {
		/*
		 * if (exp.getId() != null) { // } else { for (Experiencia e :
		 * experienciasAdicionadas) { if (e.equals(exp)) {
		 * experienciasAdicionadas.remove(e);
		 * this.selectedRecord.getExperiencias().remove(e); return; }
		 * 
		 * } }
		 */

		for (Experiencia e : experienciasAdicionadas) {
			if (e.equals(exp)) {
				experienciasAdicionadas.remove(e);
				this.selectedRecord.getExperiencias().remove(e);
				return;
			}

		}
	}

	@Command
	@NotifyChange("formacoesAdicionadas")
	public void onRemoveFormacao(@BindingParam("eduRemoved") Formacao edu) {
		/*
		 * if (edu.getId() != null) { // } else { for (Formacao f :
		 * formacoesAdicionadas) { if (f.equals(edu)) {
		 * formacoesAdicionadas.remove(edu);
		 * this.selectedRecord.getFormacoes().remove(edu); return; }
		 * 
		 * } }
		 */
		for (Formacao f : formacoesAdicionadas) {
			if (f.equals(edu)) {
				formacoesAdicionadas.remove(edu);
				this.selectedRecord.getFormacoes().remove(edu);
				return;
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

		Sessions.getCurrent().setAttribute("requisitante", selectedRecord);

		// Include incConteudo = (Include)
		// Sessions.getCurrent().getAttribute("setContent");
		// incConteudo.setSrc("/pages/anonimo/requisicao/Requisicao.zul");
		PageUtils.redirectTo("/pages/anonimo/requisicao/Requisicao.zul");

	}

	public void validate() {

		if (nome == null || nome.getValue().isEmpty()) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(nome, "Nome é um campo obrigatório");
		}

		if (sexo == null || sexo.getSelectedItem() == null
				|| sexo.getSelectedItem().getValue() == null) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(sexo,
					"Deve seleccionar o Sexo do requisitante");
		}

		if (dataNascimento == null || dataNascimento.getValue() == null) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(dataNascimento,
					"Data de Nascimento é um campo obrigatório");
		}

		Calendar cal = Calendar.getInstance();

		cal.setTime(dataNascimento.getValue());

		int age = AgeHelper.calculateMyAge(cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

		if (age < 18) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(dataNascimento,
					"A idade não deve ser inferior a 18");
		}

		if (escolaridadesLista == null
				|| escolaridadesLista.getSelectedItem() == null
				|| escolaridadesLista.getSelectedItem().getValue() == null) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(escolaridadesLista,
					"Deve seleccionar o nivel de escolaridade do requisitante");
		}

		if (membro.isChecked()) {
			if (numeroMembro == null || numeroMembro.getValue().isEmpty()) {
				tabDadosPessoas.setSelected(true);
				throw new WrongValueException(numeroMembro,
						"Como indicou que este é um membro da APM, deve indicar o nº do Membro");
			}
		}

		if (paises == null || paises.getSelectedItem() == null
				|| paises.getSelectedItem().getValue() == null) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(paises, "Pais é um campo obrigatorio");
		}

		if (selectedRecord.getProvincia() == null) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(provincias,
					"Provincia é um campo obrigatorio");
		}

		if (cidade.getText().isEmpty()) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(cidade,
					"Cidade é um campo obrigatorio");
		}

		if (endereco.getText().isEmpty()) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(endereco,
					"Endereco é um campo obrigatorio");
		}

		if (contacto1.getText().isEmpty()) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(contacto1,
					"Contacto é um campo obrigatorio");
		}

		if (email.getText().isEmpty()) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(email, "Email é um campo obrigatorio");
		}

		if (!email.getText().matches(".+@.+\\.[a-z]+")) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(email,
					"O Email introduzido é inválido");
		}

		if (numeroBi.getText().isEmpty()) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(numeroBi,
					"Nº Doc Ident. é um campo obrigatorio");
		}

		if (tipoDocsLista == null || tipoDocsLista.getSelectedItem() == null
				|| tipoDocsLista.getSelectedItem().getValue() == null) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(tipoDocsLista,
					"Deve Indicar o tipo de documento");
		}

		if (dataEmissao.getValue() == null) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(dataEmissao,
					"Data Emissão é um campo obrigatorio");
		}

		if (dataValidade.getValue() == null) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(dataValidade,
					"Data Validade é um campo obrigatorio");
		}

		if (localEmissao.getValue().isEmpty()) {
			tabDadosPessoas.setSelected(true);
			throw new WrongValueException(localEmissao,
					"Local Emissão é um campo obrigatorio");
		}
		if (this.selectedRecord.getFormacoes().isEmpty()) {
			tabFormacao.setSelected(true);
			throw new WrongValueException(instituicao,
					"Deve indicar pelo menos uma formação academica ou técnico profissional");
		}

		if (this.selectedRecord.getExperiencias().isEmpty()) {
			tabExperiencia.setSelected(true);
			throw new WrongValueException(empregador,
					"Deve indicar pelo menos uma experiência profissional");
		}

		Requisitante tempReq = requisitanteService
				.getRequisitanteByEmail(this.email.getText());

		if (tempReq != null) {
			if (this.selectedRecord.getId() == null) {
				tabDadosPessoas.setSelected(true);
				throw new WrongValueException(email,
						"O Email introduzido está em uso");
			} else {
				if (!tempReq.equals(selectedRecord)) {
					tabDadosPessoas.setSelected(true);
					throw new WrongValueException(email,
							"O Email introduzido está em uso");
				}
			}

		}

		tempReq = requisitanteService.getRequisitanteByBI(this.numeroBi
				.getText());

		if (tempReq != null) {
			if (this.selectedRecord.getId() == null) {
				tabDadosPessoas.setSelected(true);
				throw new WrongValueException(numeroBi,
						"O Número de documento introduzido está em uso");
			} else {
				if (!tempReq.equals(selectedRecord)) {
					tabDadosPessoas.setSelected(true);
					throw new WrongValueException(numeroBi,
							"O Número de documento introduzido está em uso");
				}
			}

		}

		// return true;
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
	public void disableDataFinal() {
		if (actual.isChecked()) {
			dataFinal.setReadonly(Boolean.TRUE);
			dataFinal.setDisabled(Boolean.TRUE);
			dataFinal.setValue(null);
		} else {
			dataFinal.setReadonly(Boolean.FALSE);
			dataFinal.setDisabled(Boolean.FALSE);
		}
	}

	@Command
	public void disableNumeroMembro() {
		if (membro.isChecked()) {
			numeroMembro.setReadonly(false);
		} else {
			numeroMembro.setReadonly(true);
			numeroMembro.setValue(null);
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
				// selectedRecord.setNumeroDoc(numeroBi.getValue());
				// selectedRecord.setDataEmissao(dataEmissao.getValue());
				// selectedRecord.setDataValidade(dataValidade.getValue());
				// selectedRecord.setLocalEmissao(localEmissao.getValue());
				selectedRecord.setCopiaDoc(media.getByteData());
				// selectedRecord.setNumeroNuit(numeroNuit.getValue());
				// selectedRecord.setTipoDoc(selectedRecord.getTipoDoc());

			}
		}
	}
}
