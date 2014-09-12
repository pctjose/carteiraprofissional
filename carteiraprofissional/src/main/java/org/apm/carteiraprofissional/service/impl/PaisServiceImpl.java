package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.Pais;
import org.apm.carteiraprofissional.dao.PaisDAO;
import org.apm.carteiraprofissional.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaisServiceImpl implements PaisService {
	@Autowired
	private PaisDAO paisDAO;
	
	
	

	public PaisDAO getPaisDAO() {
		return paisDAO;
	}

	public void setPaisDAO(PaisDAO paisDAO) {
		this.paisDAO = paisDAO;
	}

	@Transactional
	public void savePais(Pais pais) {
		paisDAO.savePais(pais);

	}

	@Transactional(readOnly=true)
	public Pais getByID(Integer id) {

		return paisDAO.getByID(id);
	}

	@Transactional(readOnly=true)
	public Pais getByDesignacao(String designacao) {

		return paisDAO.getByDesignacao(designacao);
	}
	
	
	@Transactional(readOnly=true)
	public List<Pais> getAllPaises() {

		return paisDAO.getAllPaises();
	}

	public void delete(Pais pais) {
		paisDAO.delete(pais);
		
	}

}
