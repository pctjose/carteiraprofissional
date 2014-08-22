package org.apm.carteiraprofissional.view.carteira;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.service.CarteiraService;
import org.apm.carteiraprofissional.utils.PathUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

public class ListaCarteiraGraficaVM {

	private Carteira selectedItem;
	private List<Carteira> listaCarteiras;

	@WireVariable
	protected CarteiraService carteiraService;

	public Carteira getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Carteira selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<Carteira> getListaCarteiras() {
		return listaCarteiras;
	}

	public void setListaCarteiras(List<Carteira> listaCarteiras) {
		this.listaCarteiras = listaCarteiras;
	}

	public List<Carteira> getDataSet() {
		return listaCarteiras;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		listaCarteiras = carteiraService.getAllCarteiraNaoEmitidas();

	}

	@Command
	public void onBaixarDados(@BindingParam("carteiraRecord") Carteira carteira) {

		File zipDocs = new File(PathUtils.getWebInfPath() + "/data/datazip/"
				+ carteira.getNumeroCarteira() + ".zip");

		if (zipDocs.exists()) {
			try {
				Filedownload.save(zipDocs, "zip");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })	
	@Command
	public void onActualizarEmitida(
			@BindingParam("carteiraRecord") final Carteira carteira) {

		Messagebox.show("Tem certeza que esta carteira já foi produzida?",
				"Confirmar Produção", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION,new org.zkoss.zk.ui.event.EventListener() {
		    public void onEvent(Event evt) throws InterruptedException {
		        if (evt.getName().equals("onOK")) {
		        	carteira.setEmitida(true);
		        	carteiraService.saveCarteira(carteira);
		        	Clients.showNotification("Carteira actualizada a sua produção");
		        }
		    }
		});
				
				
				
		//		) == Messagebox.OK) {
		//	
		//	carteiraService.saveCarteira(carteira);
		//	
		//}

	}
}
