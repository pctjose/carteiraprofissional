package org.apm.carteiraprofissional.view.requisicao;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.JFrame;

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
import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.Images;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

public class RequisicaoCarteiraVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Requisitante selectedRecord;
	private boolean makeAsReadOnly;
	private String recordMode;
	

	private Experiencia experiencia;
	private Formacao formacao;

	@Wire
	private org.zkoss.image.Image userImage;

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

	
	@WireVariable
	protected RequisitanteService requisitanteService;

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
	
	//=====================Dados pessoais==================//
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

	public org.zkoss.image.Image getUserImage() {
		return userImage;
	}

	public void setUserImage(org.zkoss.image.Image userImage) {
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

	@SuppressWarnings("unchecked")
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {

		Requisitante requisicao;
		Selectors.wireComponents(view, this, false);

		final HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("allmyvalues");	
		

		escolaridades = escolaridadeService.getAllNiveis();
		tipoDocs = tipoDocumentoService.getAllTipoDocumento();
		categorias = categoriaService.getAllCategorias();
		inPaises=paisService.getAllPaises();
		if (inPaises!=null && inPaises.size()>0){
			inProvincias=provinciaService.getAllProvincia(inPaises.get(0));
		}

		experienciasAdicionadas = new ArrayList<Experiencia>();
		formacoesAdicionadas = new ArrayList<Formacao>();

		experiencia = new Experiencia();
		formacao = new Formacao();

		if (map != null) {
			this.recordMode = (String) map.get("recordMode");
			requisicao = (Requisitante) map.get("selectedRecord");

		} else {
			this.recordMode = "EDIT";
			// userProfile = logedInUser;
		}

		if (recordMode.equals("NEW")) {
			this.selectedRecord = new Requisitante();
			//this.selectedRecord.setRequisitante(requisitante);
		}

		if (recordMode.equals("EDIT")) {
			// this.selectedRecord = requisicao;

		}

		if (recordMode.equals("READ")) {
			setMakeAsReadOnly(true);
			// this.selectedRecord = requisicao;

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

		
	}

	@Command
	@NotifyChange({ "formacao", "formacoesAdicionadas" })
	public void onAddFormacao() {

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

		Listitem li = paises.getSelectedItem();
		if (li != null) {
			Pais selectedPais = (Pais) li.getValue();
			inProvincias = provinciaService.getAllProvincia(selectedPais);

			BindUtils.postNotifyChange(null, null, this,
					"provincias");
		}

	}
	
	@Command
	public void saveThis() {
		
		//form validation
		validate();

		/*System.out.println("Formacoes:"+selectedRecord.getFormacoes().size());
		System.out.println("Experiencias:"+selectedRecord.getExperiencias().size());
		requisitanteService.saveRequisitante(selectedRecord);
		
		System.out.println("ID GRAVADO: "+selectedRecord.getId());*/
		
		Sessions.getCurrent().setAttribute("requisitante", selectedRecord);
		Executions.sendRedirect("/pages/requisicao/Requisicao.zul");

	}

	private boolean validate() {
		
		if (apelido.getValue().isEmpty()) {
			throw new WrongValueException(apelido, "Apelido é um campo obrigatório.");
		}
		
		if (nome.getValue().isEmpty()) {
			throw new WrongValueException(nome, "Nome é um campo obrigatório.");
		}
		
		if ((sexoM.isChecked() ? "M" : "S").isEmpty()) {
			throw new WrongValueException(sexo, "Sexo é uma seleção obrigatória.");
		} 
		
		return true;
	}
}
