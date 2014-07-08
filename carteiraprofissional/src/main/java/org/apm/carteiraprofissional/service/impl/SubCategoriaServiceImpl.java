package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.SubCategoria;
import org.apm.carteiraprofissional.dao.SubCategoriaDAO;
import org.apm.carteiraprofissional.service.SubCategoriaService;

public class SubCategoriaServiceImpl implements SubCategoriaService {

	private SubCategoriaDAO subCategoriaDAO;
	private static Logger log = Logger.getLogger(SubCategoriaServiceImpl.class);

	public SubCategoriaDAO getSubCategoriaDAO() {
		return subCategoriaDAO;
	}

	public void setSubCategoriaDAO(SubCategoriaDAO subCategoriaDAO) {
		this.subCategoriaDAO = subCategoriaDAO;
	}

	
	public SubCategoria getByID(Integer id) {

		return subCategoriaDAO.getByID(id);
	}

	
	public SubCategoria getByUUID(String uuid) {

		return subCategoriaDAO.getByUUID(uuid);
	}

	
	public SubCategoria getByDesignacao(String designacao) {

		return subCategoriaDAO.getByDesignacao(designacao);
	}

	
	public void saveSubCategoria(SubCategoria subcategoria) {
		log.info(subCategoriaDAO);
		subCategoriaDAO.addSubCategoria(subcategoria);
		
	}

	
	public List<SubCategoria> getAllSubCategorias() {
		
		return null;
	}

}
