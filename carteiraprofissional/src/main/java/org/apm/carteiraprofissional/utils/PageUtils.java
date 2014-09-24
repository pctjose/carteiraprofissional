package org.apm.carteiraprofissional.utils;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Include;

public class PageUtils {
	public static void redirectTo(String page){
		Include incConteudo = (Include) Sessions.getCurrent().getAttribute("setContent");
		incConteudo.setSrc(page);
	}
}
