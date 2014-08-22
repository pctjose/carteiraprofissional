package org.apm.carteiraprofissional.view.utilizador;

import java.util.HashMap;
import java.util.List;

import org.apm.carteiraprofissional.GrupoUtilizador;
import org.apm.carteiraprofissional.Utilizador;
import org.apm.carteiraprofissional.service.GrupoUtilizadorService;
import org.apm.carteiraprofissional.service.UtilizadorService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class ListaUtilizadorVM {

	@WireVariable
	private UtilizadorService utilizadorService;

	@WireVariable
	private GrupoUtilizadorService grupoUtilizadorService;

	private Utilizador selectedItem;
	private List<Utilizador> allReordsInDB = null;
	
	Window frmListaUtilizador;

	private String nome;
	private String apelido;
	private String sexo;
	private GrupoUtilizador grupo;
	private Boolean incluirAnulado;
	private List<GrupoUtilizador> inUserGrupos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public GrupoUtilizador getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoUtilizador grupo) {
		this.grupo = grupo;
	}

	public Boolean getIncluirAnulado() {
		return incluirAnulado;
	}

	public void setIncluirAnulado(Boolean incluirAnulado) {
		this.incluirAnulado = incluirAnulado;
	}

	public Utilizador getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Utilizador selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	

	public Window getFrmListaUtilizador() {
		return frmListaUtilizador;
	}

	public void setFrmListaUtilizador(Window frmListaUtilizador) {
		this.frmListaUtilizador = frmListaUtilizador;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		// CRUDService = (CRUDService) SpringUtil.getBean("CRUDService");
		// allReordsInDB = CRUDService.getAll(UserProfile.class);
		//utilizadorService = (UtilizadorService) SpringUtil
		//		.getBean("utilizadorService");

		//grupoUtilizadorService = (GrupoUtilizadorService) SpringUtil
		//		.getBean("grupoUtilizadorService");

		inUserGrupos = grupoUtilizadorService.getAllGrupos();
		allReordsInDB = utilizadorService.getAllUtilizadores(false);
	}

	public List<Utilizador> getDataSet() {
		return allReordsInDB;
	}

	public List<GrupoUtilizador> getUserGrupos() {
		return inUserGrupos;
	}

	@Command
	public void onAddNew() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectedRecord", null);
		map.put("recordMode", "NEW");
		Sessions.getCurrent().setAttribute("allmyvalues", map);		
		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/utilizador/NovoUtilizador.zul", null, null);
		cRequisicao.setParent(frmListaUtilizador);
		cRequisicao.doModal();
		
		
		//Executions.sendRedirect("/pages/admin/utilizador/NovoUtilizador.zul");
	}

	@Command
	public void onEdit(@BindingParam("userRecord") Utilizador userProfile) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectedRecord", userProfile);
		map.put("recordMode", "EDIT");
		Sessions.getCurrent().setAttribute("allmyvalues", map);
		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/utilizador/NovoUtilizador.zul", null, null);
		cRequisicao.setParent(frmListaUtilizador);
		cRequisicao.doModal();
		//Executions.sendRedirect("/pages/admin/utilizador/NovoUtilizador.zul");
	}

	@Command
	public void openAsReadOnly(
			@BindingParam("userRecord") Utilizador userProfile) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectedRecord", userProfile);
		map.put("recordMode", "READ");
		Sessions.getCurrent().setAttribute("allmyvalues", map);
		
		Window cRequisicao = (Window) Executions.createComponents(
				"/pages/admin/utilizador/NovoUtilizador.zul", null, null);
		cRequisicao.setParent(frmListaUtilizador);
		cRequisicao.doModal();
		
		//Executions.sendRedirect("/pages/admin/utilizador/NovoUtilizador.zul");
	}

	@Command
	public void pesquisar() {
		allReordsInDB = utilizadorService.getUserByAttributes(apelido, nome,
				sexo, grupo, incluirAnulado);
		BindUtils.postNotifyChange(null, null, ListaUtilizadorVM.this,
				"dataSet");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	public void onDelete(@BindingParam("userRecord") Utilizador userProfile) {
		int OkCancel;
		this.selectedItem = userProfile;
		String str = "Utilizador  \"" + userProfile.getUserName()
				+ "\" seleccionado será eliminado do sistema.";
		OkCancel = Messagebox.show(str, "Confirmar", Messagebox.OK
				| Messagebox.CANCEL, Messagebox.QUESTION);
		if (OkCancel == Messagebox.CANCEL) {
			return;
		}

		str = "O \""
				+ userProfile.getUserName()
				+ "\" será permanentemente apagado e todos seus registos associados. Esta acção é irreversível";

		Messagebox.show(str, "Confirmar", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener() {
					public void onEvent(Event event) throws Exception {
						if (((Integer) event.getData()).intValue() == Messagebox.OK) {

							// CRUDService.delete(selectedItem);

							utilizadorService.delete(selectedItem);

							allReordsInDB.remove(allReordsInDB
									.indexOf(selectedItem));
							BindUtils.postNotifyChange(null, null,
									ListaUtilizadorVM.this, "dataSet");

						}
					}
				});
	}

}
