package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.HistRequisicao;
import org.apm.carteiraprofissional.dao.HistRequisicaoDAO;
import org.apm.carteiraprofissional.service.HistRequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistRequisicaoServiceImpl implements HistRequisicaoService {

	@Autowired
	private HistRequisicaoDAO histRequisicaoDAO;

	public HistRequisicaoDAO getHistRequisicaoDAO() {
		return histRequisicaoDAO;
	}

	public void setHistRequisicaoDAO(HistRequisicaoDAO histRequisicaoDAO) {
		this.histRequisicaoDAO = histRequisicaoDAO;
	}

	public void saveOrUpdate(HistRequisicao histRequisicao) {
		histRequisicaoDAO.saveOrUpdate(histRequisicao);

	}

	public HistRequisicao getById(Integer id) {

		return histRequisicaoDAO.getById(id);
	}

	public List<HistRequisicao> getAll() {

		return histRequisicaoDAO.getAll();
	}

}
