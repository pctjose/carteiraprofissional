package org.apm.carteiraprofissional.view;

import java.io.IOException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

public class ShowReportVM extends SelectorComposer<Component> {

	/**
	 * 
	 */
	@Wire
	private Window frmShowReport;

	private static final long serialVersionUID = -9148788192016544345L;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {
		Selectors.wireComponents(view, this, false);
	}

	public Window getFrmShowReport() {
		return frmShowReport;
	}

	public void setFrmShowReport(Window frmShowReport) {
		this.frmShowReport = frmShowReport;
	}

	@Command
	public void fechar() throws Exception {
		
		Iframe iframePdf = (Iframe) frmShowReport.getFellow("reportShow");
		iframePdf.setContent(null);	
		Thread.sleep(200);
		frmShowReport.detach();
	}

}
