package org.apm.carteiraprofissional.view.escolaridade;

import java.util.HashMap;
import java.util.List;

import org.apm.carteiraprofissional.Escolaridade;
import org.apm.carteiraprofissional.service.EscolaridadeService;
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

public class ListaEscolaridadeVM {
	
	@WireVariable
	private EscolaridadeService escolaridadeService;
	private Escolaridade selectedItem;
	private List<Escolaridade> allReordsInDB=null;
	public Escolaridade getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(Escolaridade selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	 @AfterCompose
	   public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
	     Selectors.wireComponents(view, this, false);
	     escolaridadeService = (EscolaridadeService) SpringUtil.getBean("escolaridadeService");
	     allReordsInDB = escolaridadeService.getAllNiveis();//CRUDService.getAll(UserProfile.class);
	  }
	 
	   public List<Escolaridade> getDataSet() {
	       return allReordsInDB;
	   }
	 
	   @Command
	    public void onAddNew() {
	        final HashMap<String, Object> map = new HashMap<String, Object>();
	      map.put("selectedRecord", null);
	        map.put("recordMode", "NEW");
	       Sessions.getCurrent().setAttribute("allmyvalues", map);
	     Executions.sendRedirect("/pages/pagebased/index-escolaridade.zul");
	    }
	 
	   @Command
	    public void onEdit(@BindingParam("userRecord") Escolaridade userProfile) {
	 
	       final HashMap<String, Object> map = new HashMap<String, Object>();
	      map.put("selectedRecord", userProfile);
	     map.put("recordMode", "EDIT");
	      Sessions.getCurrent().setAttribute("allmyvalues", map);
	     Executions.sendRedirect("Escolaridade.zul");
	    }
	 
	   @Command
	    public void openAsReadOnly(
	         @BindingParam("userRecord") Escolaridade userProfile) {
	 
	      final HashMap<String, Object> map = new HashMap<String, Object>();
	      map.put("selectedRecord", userProfile);
	     map.put("recordMode", "READ");
	      Sessions.getCurrent().setAttribute("allmyvalues", map);
	     Executions.sendRedirect("Escolaridade.zul");
	    }
	 
	   @SuppressWarnings({ "rawtypes", "unchecked" })
	  @Command
	    public void onDelete(@BindingParam("userRecord") Escolaridade userProfile) {
	     int OkCancel;
	       this.selectedItem = userProfile;
	        String str = "Escolaridade  \"" + userProfile.getDesignacao()
	              + "\" sera removida.";
	        OkCancel = Messagebox.show(str, "Confirma", Messagebox.OK
	                | Messagebox.CANCEL, Messagebox.QUESTION);
	      if (OkCancel == Messagebox.CANCEL) {
	            return;
	     }
	 
	       str = "Escolaridade \""
	              + userProfile.getDesignacao()
	              + "\" Será removido completamente e sem retorno.";
	 
	        Messagebox.show(str, "Confirma", Messagebox.OK | Messagebox.CANCEL,
	              Messagebox.QUESTION, new EventListener() {
	                  public void onEvent(Event event) throws Exception {
	                     if (((Integer) event.getData()).intValue() == Messagebox.OK) {
	 
	                    	 escolaridadeService.delete(selectedItem);
	                           allReordsInDB.remove(allReordsInDB
	                                  .indexOf(selectedItem));
	                            BindUtils.postNotifyChange(null, null,
	                            		ListaEscolaridadeVM.this, "dataSet");
	 
	                        }
	                   }
	               });
	 }
	 
	
	
	

}
