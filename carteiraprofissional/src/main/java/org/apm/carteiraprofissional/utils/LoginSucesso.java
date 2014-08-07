package org.apm.carteiraprofissional.utils;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apm.carteiraprofissional.Utilizador;
import org.apm.carteiraprofissional.service.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSucesso implements AuthenticationSuccessHandler{
	private Map<String, String> roleUrlMap;
	@Autowired
	private UtilizadorService utilizadorService;
	private Utilizador utilizador = null;
/*
 * (non-Javadoc)
 * Este metodo traz utilizador autenticado em funcao do role e redireciona para paginas especificas
 * de acordo como no spring-security.xml
 */
	public void onAuthenticationSuccess(HttpServletRequest req,
			HttpServletResponse resp, Authentication auth) throws IOException,
			ServletException {
		
		if (auth.getPrincipal() instanceof UserDetails){
			UserDetails utilizador1 = (UserDetails) auth.getPrincipal();
			utilizador = utilizadorService.getUtilizador(utilizador1.getUsername());
			if(utilizador1 != null && auth.isAuthenticated()){
				req.getSession().setAttribute("logedIn", utilizador.getNome());
				req.getSession().setAttribute("utilizador", utilizador);
				String grupo = utilizador1.getAuthorities().isEmpty() ? null : utilizador1.getAuthorities().toArray()[0].toString();
				resp.sendRedirect(req.getContextPath()+roleUrlMap.get(grupo));
			}

		}
	}
	public void setRoleUrlMap(Map<String, String> roleUrlMap) {
        this.roleUrlMap = roleUrlMap;
    }
	
}
