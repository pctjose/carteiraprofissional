package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.Escolaridade;
import org.apm.carteiraprofissional.dao.EscolaridadeDAO;
import org.apm.carteiraprofissional.service.EscolaridadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("escolaridadeService")
public class EscolaridadeServiceImpl implements EscolaridadeService {

	@Autowired
	private EscolaridadeDAO escolaridadeDAO;

	

	public EscolaridadeDAO getEscolaridadeDAO() {
		return escolaridadeDAO;
	}

	public void setEscolaridadeDAO(EscolaridadeDAO escolaridadeDAO) {
		this.escolaridadeDAO = escolaridadeDAO;
	}

	@Transactional(readOnly=true)
	public Escolaridade getNivelByID(Integer id) {
		return escolaridadeDAO.getByID(id);
	}

	@Transactional(readOnly=true)
	public Escolaridade getNivelByUUID(String uuid) {

		return escolaridadeDAO.getByUUID(uuid);
	}

	
	@Transactional(readOnly=true)
	public Escolaridade getNivelByDesignacao(String designacao) {

		return escolaridadeDAO.getByDesignacao(designacao);

	}

	//@Transactional(readOnly=true)
	public List<Escolaridade> getAllNiveis() {

		return escolaridadeDAO.getAllNiveis();
	}

	@Transactional
	public void saveUpdateEscolaridade(Escolaridade nivel) {
		escolaridadeDAO.saveUpdateEscolaridade(nivel);
	}

	@Transactional
	public void delete(Escolaridade nivel) {
		escolaridadeDAO.delete(nivel);
	}

}
