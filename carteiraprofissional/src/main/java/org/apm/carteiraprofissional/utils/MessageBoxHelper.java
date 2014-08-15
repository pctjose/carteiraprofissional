package org.apm.carteiraprofissional.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Window;

/**
 * 
 * @author Helio.Machabane
 *
 */
public class MessageBoxHelper {
	
	private static Logger _log = Logger.getLogger(MessageBoxHelper.class); 
	
	private static final String ERROR_ZUL = "/pages/WndErro.zul";
	
	/**
	 * Mostra num unico popup todas as mensgens de erro
	 * @param msgList A lista de mensagens de erro
	 * @param parentWindow A janela onde o erro será mostrado
	 */
	public static void showFormError(List<Msg> msgList, Component parentWindow) throws InterruptedException{
		
		//1. Cria a Janela de erro do zul
		Window errorZul = (Window) Executions.createComponents(ERROR_ZUL, null,null);
		
		//2. Proteger contra erros
		if(errorZul == null){_log.error("Não encontrou o ficherio "+ERROR_ZUL+".Certifica que o mesmo existe");return;}
		
		//3. Colocar na Janela onde deve aparecer
		errorZul.setParent(parentWindow);
		
		Tree treeError = (Tree) errorZul.getFellow("treeError");
		treeError.getChildren().clear();

		//4. Definindo o Header da Tree
		Treecols tcols = new Treecols();
		Treecol tcol = new Treecol();
		tcol.setLabel("Ocorreram Erros");
		tcol.setClass("error-title");
		tcols.appendChild(tcol);
		treeError.appendChild(tcols);

		//5. Definindo os Detalhes da Tree
		Treechildren tc = new Treechildren();
		//tc.setSclass("error-data");

		for (Msg msg : msgList) {
			Treeitem ti = new Treeitem();
			ti.setLabel(msg.getTxt());
			ti.setOpen(false);

			Treechildren tcti = new Treechildren();
			Treeitem titc = new Treeitem();
			titc.setLabel(msg.getHlp());
			tcti.appendChild(titc);

			ti.appendChild(tcti);

			tc.appendChild(ti);
		}
				
		treeError.appendChild(tc);
		treeError.setVisible(true);
		
		errorZul.doModal();	
	}
}
