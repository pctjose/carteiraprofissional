package org.apm.carteiraprofissional.utils;

import org.apm.carteiraprofissional.PropriedadesGlobais;
import org.apm.carteiraprofissional.service.PropriedadesGlobaisService;
import org.zkoss.zkplus.spring.SpringUtil;

public class PropriedadeGlobalUtils {
	private static PropriedadesGlobaisService propriedadesGlobaisService=(PropriedadesGlobaisService) SpringUtil
			.getBean("propriedadesGlobaisService");;
	public static PropriedadesGlobais getEmailAPM(){
		return propriedadesGlobaisService.getPropriedadeById("email.apm");
	}
	
	public static PropriedadesGlobais getEmailGrafica(){
		return propriedadesGlobaisService.getPropriedadeById("email.grafica");
	}
}
