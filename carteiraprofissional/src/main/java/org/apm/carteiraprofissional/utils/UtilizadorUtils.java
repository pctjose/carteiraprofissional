package org.apm.carteiraprofissional.utils;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.Utilizador;
import org.apm.carteiraprofissional.service.UtilizadorService;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zkplus.spring.SpringUtil;

public class UtilizadorUtils {

	private static Utilizador utilizador;
	private static UtilizadorService utilizadorService;
	private static Logger log = Logger.getLogger(UtilizadorUtils.class);

	public static UtilizadorService getUtilizadorService() {
		return utilizadorService;
	}

	public static void setUtilizadorService(UtilizadorService utilizadorService) {
		UtilizadorUtils.utilizadorService = utilizadorService;
	}

	public static Utilizador getLogedUser() {

		Utilizador user = (Utilizador) Sessions.getCurrent().getAttribute(
				"utilizador");

		if (user == null) {
			utilizadorService = (UtilizadorService) SpringUtil
					.getBean("utilizadorService");
			user = utilizadorService.getUtilizador("anonimo.user");
		}
		return user;
	}

	/*
	 * public static Utilizador getAuthenticateUser() { User user = (User)
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * String login = user.getUsername(); try { utilizador =
	 * utilizadorService.getUtilizador(login);
	 * log.info(utilizador.getUtilizadorId()); } catch (Exception e) {
	 * e.printStackTrace(); log.info(utilizadorService.getClass()); }
	 * 
	 * return utilizador; }
	 */

}
