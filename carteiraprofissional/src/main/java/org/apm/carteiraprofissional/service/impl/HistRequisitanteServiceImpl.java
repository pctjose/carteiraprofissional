package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.HistRequisitante;
import org.apm.carteiraprofissional.dao.HistRequisitanteDAO;
import org.apm.carteiraprofissional.service.HistRequisitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistRequisitanteServiceImpl implements HistRequisitanteService {

	@Autowired
	private HistRequisitanteDAO histRequisitanteDAO;
	
	
	public HistRequisitanteDAO getHistRequisitanteDAO() {
		return histRequisitanteDAO;
	}

	public void setHistRequisitanteDAO(HistRequisitanteDAO histRequisitanteDAO) {
		this.histRequisitanteDAO = histRequisitanteDAO;
	}

	public void saveOrUpdate(HistRequisitante histRequisitante) {
		histRequisitanteDAO.saveOrUpdate(histRequisitante);

	}

	public HistRequisitante getById(Integer id) {
		
		return histRequisitanteDAO.getById(id);
	}

	public List<HistRequisitante> getAll() {
		
		return histRequisitanteDAO.getAll();
	}

}
