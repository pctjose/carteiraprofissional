package org.apm.carteiraprofissional.view.utilizador;

import java.util.HashMap;

import org.apm.carteiraprofissional.Utilizador;
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
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

public class LoginVM extends SelectorComposer<Component> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	@Wire
	Label errorSMS;	

	@WireVariable
	private UtilizadorService utilizadorService;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

	@Command
	public void login() {
		
		Utilizador logedInUser=utilizadorService.getUtilizadorByUserNameAndPassword(this.userName, password);
		if(logedInUser!=null){
			Sessions.getCurrent().setAttribute("logedInUser", logedInUser);
			Sessions.getCurrent().setAttribute("logedIn", true);
			//Executions.sendRedirect("/pages/pagebased/index-utilizador-lista.zul");
			if(!logedInUser.getGrupo().getUuid().equalsIgnoreCase("6b9a194d-e73d-11e3-8e8f-a4db30f2439a")){
				Sessions.getCurrent().setAttribute("requisitante", logedInUser);
				Executions.sendRedirect("/pages/pagebased/index-lista-requisicao-single-user.zul");
			}else{
				Executions.sendRedirect("/pages/pagebased/index.zul");
			}
			
			
		}else{
			errorSMS.setValue("Nome de Utlizador ou Senha inválidos...");
			BindUtils.postNotifyChange(null, null, LoginVM.this,
					"errorSMS");
			BindUtils.postNotifyChange(null, null, LoginVM.this,
					"password");
		}

	}
	
	 @Command
	    public void onAddNew() {
	        final HashMap<String, Object> map = new HashMap<String, Object>();
	      map.put("selectedRecord", null);
	        map.put("recordMode", "NEW");
	       Sessions.getCurrent().setAttribute("allmyvalues", map);
	       Sessions.getCurrent().setAttribute("logedIn", false);
	       
	     Executions.sendRedirect("/pages/pagebased/index-utilizador-novo-not-loged.zul");
	    }

	

	public UtilizadorService getUtilizadorService() {
		return utilizadorService;
	}

	public void setUtilizadorService(UtilizadorService utilizadorService) {
		this.utilizadorService = utilizadorService;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		utilizadorService = (UtilizadorService) SpringUtil
				.getBean("utilizadorService");

	}

}
