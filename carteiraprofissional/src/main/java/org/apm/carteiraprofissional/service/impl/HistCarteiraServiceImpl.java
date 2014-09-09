package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.HistCarteira;
import org.apm.carteiraprofissional.dao.HistCarteiraDAO;
import org.apm.carteiraprofissional.service.HistCarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistCarteiraServiceImpl implements HistCarteiraService {

	@Autowired
	private HistCarteiraDAO histCarteiraDAO;

	public HistCarteiraDAO getHistCarteiraDAO() {
		return histCarteiraDAO;
	}

	public void setHistCarteiraDAO(HistCarteiraDAO histCarteiraDAO) {
		this.histCarteiraDAO = histCarteiraDAO;
	}

	public void saveOrUpdate(HistCarteira histCarteira) {
		histCarteiraDAO.saveOrUpdate(histCarteira);

	}

	public HistCarteira getById(Integer id) {

		return histCarteiraDAO.getById(id);
	}

	public List<HistCarteira> getAll() {

		return histCarteiraDAO.getAll();
	}

}
