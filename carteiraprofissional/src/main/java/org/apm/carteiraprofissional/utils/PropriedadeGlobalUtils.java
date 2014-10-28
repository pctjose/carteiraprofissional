package org.apm.carteiraprofissional.utils;

import org.apm.carteiraprofissional.PropriedadesGlobais;
import org.apm.carteiraprofissional.service.PropriedadesGlobaisService;
import org.zkoss.zkplus.spring.SpringUtil;

public class PropriedadeGlobalUtils {
	private static PropriedadesGlobaisService propriedadesGlobaisService=(PropriedadesGlobaisService) SpringUtil
			.getBean("propriedadesGlobaisService");;
	public static PropriedadesGlobais getEmailAPM(){
		return propriedadesGlobaisService.getPropriedadeByName("email.apm");
	}
	
	public static PropriedadesGlobais getEmailGrafica(){
		return propriedadesGlobaisService.getPropriedadeByName("email.grafica");
	}
}
