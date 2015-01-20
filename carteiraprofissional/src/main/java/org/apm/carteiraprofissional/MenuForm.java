package org.apm.carteiraprofissional;

import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Include;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;

@SuppressWarnings("rawtypes")
public class MenuForm extends GenericForwardComposer  {
	
	//private Logger log = Logger.getLogger(getClass());

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Tree treeMenu;
	
	private Include incConteudo;
		
	public void onClick$treeMenu(Event event) {
		//log.info("loadPage called...");
		Treeitem item = treeMenu.getSelectedItem();
		String paginaPretendida = item != null ? (String) item.getValue() : null;
		//log.info("paginaPretendida "+paginaPretendida);

		incConteudo = (Include)Path.getComponent("//main/wndMain/contentSrc");
		
		Sessions.getCurrent().setAttribute("setContent", incConteudo);
		
		incConteudo.setSrc(null);
		incConteudo.setSrc(paginaPretendida);
		
		//log.info("Src "+incConteudo.getSrc());
	}

}
