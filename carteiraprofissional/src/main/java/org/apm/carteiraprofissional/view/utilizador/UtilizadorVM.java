package org.apm.carteiraprofissional.view.utilizador;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apm.carteiraprofissional.GrupoUtilizador;
import org.apm.carteiraprofissional.Utilizador;
import org.apm.carteiraprofissional.service.GrupoUtilizadorService;
import org.apm.carteiraprofissional.service.UtilizadorService;
import org.apm.carteiraprofissional.utils.UtilizadorUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class UtilizadorVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@WireVariable
	protected UtilizadorService utilizadorService;
	@WireVariable
	protected GrupoUtilizadorService grupoUtilizadorService;
	
	@Wire
	private Window criarUtilizador;
		
	@Wire
	Textbox nomeCompleto;
	
	
	@Wire
	Textbox contacto;
	
	@Wire
	Textbox email;
	
	@Wire
	Textbox userName;
	
	@Wire
	Textbox senha;
	
	@Wire
	Listbox userGrupos;

	
	

	private Utilizador selectedRecord;
	private String recordMode;
	private boolean makeAsReadOnly;

	private String repetirSenha;	
	private List<GrupoUtilizador> inUserGrupos;
	private Utilizador logedInUser;

	public Utilizador getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(Utilizador selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}

	public boolean isMakeAsReadOnly() {
		return makeAsReadOnly;
	}

	public void setMakeAsReadOnly(boolean makeAsReadOnly) {
		this.makeAsReadOnly = makeAsReadOnly;
	}

	public String getRepetirSenha() {
		return repetirSenha;
	}

	public void setRepetirSenha(String repetirSenha) {
		this.repetirSenha = repetirSenha;
	}	

	public Textbox getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(Textbox nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Textbox getContacto() {
		return contacto;
	}

	public void setContacto(Textbox contacto) {
		this.contacto = contacto;
	}

	public Textbox getEmail() {
		return email;
	}

	public void setEmail(Textbox email) {
		this.email = email;
	}

	public Textbox getUserName() {
		return userName;
	}

	public void setUserName(Textbox userName) {
		this.userName = userName;
	}

	public Textbox getSenha() {
		return senha;
	}

	public void setSenha(Textbox senha) {
		this.senha = senha;
	}

	public void setUserGrupos(Listbox userGrupos) {
		this.userGrupos = userGrupos;
	}
	
	
	
	
	public Window getCriarUtilizador() {
		return criarUtilizador;
	}

	public void setCriarUtilizador(Window criarUtilizador) {
		this.criarUtilizador = criarUtilizador;
	}

	private void setDados(Utilizador user){
		user.setNomeCompleto(nomeCompleto.getValue());
		user.setContacto(contacto.getValue());
		user.setEmail(email.getValue());
		user.setUserName(userName.getValue());
		user.setSenha(senha.getValue());
		user.setGrupo((GrupoUtilizador)userGrupos.getSelectedItem().getValue());
	}

	@SuppressWarnings("unchecked")
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {

		Utilizador userProfile;
		Selectors.wireComponents(view, this, false);

		final HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("allmyvalues");
		
		
		

		logedInUser = (Utilizador) Sessions.getCurrent().getAttribute(
				"utilizador");

		//List<GrupoUtilizador> defGrupoAsList = grupoUtilizadorService
		//		.getDefaultGrupo();

		if (map != null) {
			this.recordMode = (String) map.get("recordMode");
			userProfile = (Utilizador) map.get("selectedRecord");
		} else {
			this.recordMode = "EDIT";
			userProfile = logedInUser;
		}

		inUserGrupos = grupoUtilizadorService.getAllGrupos();
		
		if (recordMode.equals("NEW")) {
			this.selectedRecord = new Utilizador();
		}

		if (recordMode.equals("EDIT")) {
			this.selectedRecord = userProfile;			
		}

		if (recordMode.equals("READ")) {
			setMakeAsReadOnly(true);
			this.selectedRecord = userProfile;			
		}
	}	

	public List<GrupoUtilizador> getUserGrupos() {
		return inUserGrupos;
	}

	public void setUserGrupos(List<GrupoUtilizador> inUserGrupos) {
		this.inUserGrupos = inUserGrupos;
	}

	@Command
	public void saveThis() {

		Utilizador logedInUser = UtilizadorUtils.getLogedUser();
		
		setDados(this.selectedRecord);
		if (this.selectedRecord.getId() == null) {
			this.selectedRecord.setDataCriacao(new Date());
			this.selectedRecord.setAnulado(false);
			this.selectedRecord.setUuid(UUID.randomUUID().toString());
			
			if (logedInUser != null) {
				this.selectedRecord.setCriadoPor(logedInUser);
			} else {
				this.selectedRecord.setCriadoPor(selectedRecord);
			}
			
		} else {
			this.selectedRecord.setDataAlteracao(new Date());
			if (logedInUser != null) {
				this.selectedRecord.setAlteradoPor(logedInUser);
			} else {
				this.selectedRecord.setAlteradoPor(selectedRecord);
			}
		}

		

		if (this.selectedRecord.isAnulado()) {
			this.selectedRecord.setDataAnulado(new Date());
			if (logedInUser != null) {
				this.selectedRecord.setAnuladoPor(logedInUser);
			} else {
				this.selectedRecord.setAnuladoPor(selectedRecord);
			}
		}
		
		utilizadorService.saveUtilizador(this.selectedRecord);
		
		criarUtilizador.detach();
		
		Clients.showNotification("Utilizador Registado/Actualizado");
		
		

	}

	@Command
	public void cancel() {		
		
		criarUtilizador.detach();

	}

}
