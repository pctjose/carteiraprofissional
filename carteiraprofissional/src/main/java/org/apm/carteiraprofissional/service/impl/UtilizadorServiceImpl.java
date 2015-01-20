package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.GrupoUtilizador;
import org.apm.carteiraprofissional.Utilizador;
import org.apm.carteiraprofissional.dao.UtilizadorDAO;
import org.apm.carteiraprofissional.service.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilizadorServiceImpl implements UtilizadorService, UserDetailsService {

	@Autowired
	private UtilizadorDAO utilizadorDAO;

	
	private static Logger log = Logger.getLogger(UtilizadorServiceImpl.class);

	
	
	public UtilizadorDAO getUtilizadorDAO() {
		return utilizadorDAO;
	}


	public void setUtilizadorDAO(UtilizadorDAO utilizadorDAO) {
		this.utilizadorDAO = utilizadorDAO;
	}

	@Transactional
	public void saveUtilizador(Utilizador utilizador) {

		utilizadorDAO.saveUtilizador(utilizador);
	}

	
	public Utilizador getUtilizadorByID(Integer id) {
		return utilizadorDAO.getUtilizadorByID(id);
	}

	
	public Utilizador getUtilizadorByUserNameAndPassword(String username,
			String senha) {
		return utilizadorDAO.getUtilizadorByUserNameAndPassword(username, senha);
	}

	
	public Utilizador getUtilizadorByUUID(String uuid) {
		return utilizadorDAO.getUtilizadorByUUID(uuid);
	}

	
	public List<Utilizador> getAllUtilizadores(Boolean includeVoided) {
		return utilizadorDAO.getAllUtilizadores(includeVoided);
	}

	
	
	public Utilizador getUtilizador(String login) {
		
		return utilizadorDAO.getUtilizador(login);
	}

	public Utilizador ValidateLogin(String username, String password) {
		// TODO Auto-generated method stub
		return utilizadorDAO.getUtilizadorByUserNameAndPassword(username, password);
	}


	@Transactional
	public void delete(Utilizador user) {
		utilizadorDAO.delete(user);
		
	}


	@Transactional(readOnly=true)
	public List<Utilizador> getUserByAttributes(String nomeCompleto,
			String sexo, GrupoUtilizador grupo, Boolean incluirAnulado) {
		
		return utilizadorDAO.getUserByAttributes(nomeCompleto, sexo, grupo, incluirAnulado);
	}
	
	
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {

		User user = null;
		Utilizador utilizador = null;
		
		try {
			utilizador =  (Utilizador) utilizadorDAO.getUtilizador(login);

			if(utilizador != null){
				//throw new UsernameNotFoundException("Nome do Utilizador/senha invalido");
				user = new User(utilizador.getUsername(), utilizador.getPassword(), true, true, true, true, utilizador.getAuthorities());

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
		}

        return user;
	}

	@Transactional
	public List<Utilizador> getAllUtilizador() {
		return utilizadorDAO.getAllUtilizador();
	}
	
	public void inserirUtilizador(){
		utilizadorDAO.inserirUtilizador();
	}
	
}
