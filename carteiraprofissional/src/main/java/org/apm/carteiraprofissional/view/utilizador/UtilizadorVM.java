package org.apm.carteiraprofissional.view.utilizador;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apm.carteiraprofissional.GrupoUtilizador;
import org.apm.carteiraprofissional.Pais;
import org.apm.carteiraprofissional.Provincia;
import org.apm.carteiraprofissional.Utilizador;
import org.apm.carteiraprofissional.service.GrupoUtilizadorService;
import org.apm.carteiraprofissional.service.PaisService;
import org.apm.carteiraprofissional.service.ProvinciaService;
import org.apm.carteiraprofissional.service.UtilizadorService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

public class UtilizadorVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@WireVariable
	private UtilizadorService utilizadorService;
	@WireVariable
	private ProvinciaService provinciaService;
	@WireVariable
	private PaisService paisService;
	@WireVariable
	private GrupoUtilizadorService grupoUtilizadorService;

	@Wire
	Listbox paises;

	private Utilizador selectedRecord;
	private String recordMode;
	private boolean makeAsReadOnly;

	private String repetirSenha;

	private List<Pais> inPaises;
	private List<Provincia> inProvincias;
	private List<GrupoUtilizador> inUserGrupos;

	private Utilizador logedInUser;
	

	public UtilizadorService getUtilizadorService() {
		return utilizadorService;
	}

	public void setUtilizadorService(UtilizadorService utilizadorService) {
		this.utilizadorService = utilizadorService;
	}

	public ProvinciaService getProvinciaService() {
		return provinciaService;
	}

	public void setProvinciaService(ProvinciaService provinciaService) {
		this.provinciaService = provinciaService;
	}

	public PaisService getPaisService() {
		return paisService;
	}

	public void setPaisService(PaisService paisService) {
		this.paisService = paisService;
	}

	public GrupoUtilizadorService getGrupoUtilizadorService() {
		return grupoUtilizadorService;
	}

	public void setGrupoUtilizadorService(
			GrupoUtilizadorService grupoUtilizadorService) {
		this.grupoUtilizadorService = grupoUtilizadorService;
	}

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

	@SuppressWarnings("unchecked")
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {

		Utilizador userProfile;
		Selectors.wireComponents(view, this, false);

		final HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("allmyvalues");
		
		

		utilizadorService = (UtilizadorService) SpringUtil
				.getBean("utilizadorService");

		provinciaService = (ProvinciaService) SpringUtil
				.getBean("provinciaService");

		paisService = (PaisService) SpringUtil.getBean("paisService");

		grupoUtilizadorService = (GrupoUtilizadorService) SpringUtil
				.getBean("grupoUtilizadorService");

		inPaises = paisService.getAllPaises();
		inProvincias = provinciaService.getAllProvincia();

		Boolean logedIn = (Boolean) Sessions.getCurrent().getAttribute(
				"logedIn");
		logedInUser = (Utilizador) Sessions.getCurrent().getAttribute(
				"logedInUser");

		List<GrupoUtilizador> defGrupoAsList = grupoUtilizadorService
				.getDefaultGrupo();
		
		
		if(map!=null){
			this.recordMode = (String) map.get("recordMode");
			userProfile = (Utilizador) map.get("selectedRecord");
			// CRUDService = (CRUDService) SpringUtil.getBean("CRUDService");
		}else{
			this.recordMode = "EDIT";
			userProfile = logedInUser;
		}

		if (logedIn != null) {
			if (logedIn) {
				if (logedInUser != null) {
					if (logedInUser.getGrupo().equals(defGrupoAsList.get(0))) {
						inUserGrupos = defGrupoAsList;
					} else {
						inUserGrupos = grupoUtilizadorService.getAllGrupos();
					}

				} else {
					inUserGrupos = defGrupoAsList;
				}

			} else {
				inUserGrupos = defGrupoAsList;
			}
		}

		if (recordMode.equals("NEW")) {
			this.selectedRecord = new Utilizador();
		}

		if (recordMode.equals("EDIT")) {
			this.selectedRecord = userProfile;
			/*
			 * Listitem li = new Listitem();
			 * li.setValue(userProfile.getProvincia().getPais());
			 * paises.setSelectedItem(li);
			 */
		}

		if (recordMode.equals("READ")) {
			setMakeAsReadOnly(true);
			this.selectedRecord = userProfile;
			/*
			 * Listitem li = new Listitem();
			 * li.setValue(userProfile.getProvincia().getPais());
			 * paises.setSelectedItem(li);
			 */
		}
	}

	public List<Pais> getPaises() {
		return inPaises;
	}

	public List<Provincia> getProvincias() {
		return inProvincias;
	}

	public List<GrupoUtilizador> getUserGrupos() {
		return inUserGrupos;
	}

	@Command
	public void changePais() {

		Listitem li = paises.getSelectedItem();
		if (li != null) {
			Pais selectedPais = (Pais) li.getValue();
			inProvincias = provinciaService.getAllProvincia(selectedPais);

			BindUtils.postNotifyChange(null, null, UtilizadorVM.this,
					"provincias");
		}

	}

	@Command
	public void saveThis() {

		Utilizador logedInUser = (Utilizador) Sessions.getCurrent()
				.getAttribute("logedInUser");

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
		
		if(this.selectedRecord.isAnulado()){
			this.selectedRecord.setDataAnulado(new Date());
			if (logedInUser != null) {
				this.selectedRecord.setAnuladoPor(logedInUser);
			} else {
				this.selectedRecord.setAnuladoPor(selectedRecord);
			}
		}
		
		if(logedInUser!=null){
			if(!logedInUser.getGrupo().getUuid().equalsIgnoreCase("6b9a194d-e73d-11e3-8e8f-a4db30f2439a")){
				//home
				Executions.sendRedirect("/pages/pagebased/index.zul");
			}else{
				Executions.sendRedirect("/pages/pagebased/index-utilizador-lista.zul");
			}
			
		}else{
			Executions.sendRedirect("/pages/pagebased/index-login.zul");
		}
		
		utilizadorService.saveUtilizador(this.selectedRecord);
		
	}

	@Command
	public void cancel() {
		Boolean logedIn = (Boolean) Sessions.getCurrent().getAttribute(
				"logedIn");
		if (logedIn != null) {
			if (logedIn) {
				if (!logedInUser
						.getGrupo()
						.getUuid()
						.equalsIgnoreCase(
								"6b9a194d-e73d-11e3-8e8f-a4db30f2439a")) {
					Executions.sendRedirect("/pages/pagebased/index.zul");
				} else {
					Executions
							.sendRedirect("/pages/pagebased/index-utilizador-lista.zul");
				}

			} else {
				Executions.sendRedirect("/pages/pagebased/index-login.zul");
			}

		}

	}

}
