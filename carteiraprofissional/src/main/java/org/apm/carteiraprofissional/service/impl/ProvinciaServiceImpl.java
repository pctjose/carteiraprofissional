package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.Pais;
import org.apm.carteiraprofissional.Provincia;
import org.apm.carteiraprofissional.dao.ProvinciaDAO;
import org.apm.carteiraprofissional.service.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {

	@Autowired
	private ProvinciaDAO provinciaDAO;
	
	
	

	public ProvinciaDAO getProvinciaDAO() {
		return provinciaDAO;
	}

	public void setProvinciaDAO(ProvinciaDAO provinciaDAO) {
		this.provinciaDAO = provinciaDAO;
	}

	public void saveProvincia(Provincia provincia) {
		provinciaDAO.saveProvincia(provincia);

	}

	public Provincia getByID(Integer id) {

		return provinciaDAO.getByID(id);
	}

	public Provincia getByDesignacao(String designacao) {

		return provinciaDAO.getByDesignacao(designacao);
	}

	public List<Provincia> getAllProvincia() {

		return provinciaDAO.getAllProvincia();
	}

	public List<Provincia> getAllProvincia(Pais pais) {

		return provinciaDAO.getAllProvincia(pais);
	}

	public void delete(Provincia provincia) {
		provinciaDAO.delete(provincia);
		
	}

}
