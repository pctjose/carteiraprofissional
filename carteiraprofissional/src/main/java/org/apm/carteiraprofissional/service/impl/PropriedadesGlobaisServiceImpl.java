package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.PropriedadesGlobais;
import org.apm.carteiraprofissional.dao.PropriedadesGlobaisDAO;
import org.apm.carteiraprofissional.service.PropriedadesGlobaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropriedadesGlobaisServiceImpl implements
		PropriedadesGlobaisService {

	@Autowired
	private PropriedadesGlobaisDAO propriedadesGlobaisDAO;

	public PropriedadesGlobaisDAO getPropriedadesGlobaisDAO() {
		return propriedadesGlobaisDAO;
	}

	public void setPropriedadesGlobaisDAO(
			PropriedadesGlobaisDAO propriedadesGlobaisDAO) {
		this.propriedadesGlobaisDAO = propriedadesGlobaisDAO;
	}

	public void savePropriedade(PropriedadesGlobais propriedade) {
		propriedadesGlobaisDAO.savePropriedade(propriedade);

	}

	public PropriedadesGlobais getPropriedadeById(Integer id) {

		return propriedadesGlobaisDAO.getPropriedadeById(id);
	}

	public List<PropriedadesGlobais> getAllPropriedades() {

		return propriedadesGlobaisDAO.getAllPropriedades();
	}

	public void delete(PropriedadesGlobais propriedade) {
		propriedadesGlobaisDAO.delete(propriedade);
		
	}

	public PropriedadesGlobais getPropriedadeByName(String name) {
		
		return propriedadesGlobaisDAO.getPropriedadeByName(name);
	}

}
