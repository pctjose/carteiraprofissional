package org.apm.carteiraprofissional.service.impl;

import org.apm.carteiraprofissional.NumeroRequisicao;
import org.apm.carteiraprofissional.dao.NumeroRequisicaoDAO;
import org.apm.carteiraprofissional.service.NumeroRequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumeroRequisicaoServiceImpl implements NumeroRequisicaoService {

	@Autowired
	private NumeroRequisicaoDAO numeroRequisicaoDAO;

	public NumeroRequisicaoDAO getNumeroRequisicaoDAO() {
		return numeroRequisicaoDAO;
	}

	public void setNumeroRequisicaoDAO(NumeroRequisicaoDAO numeroRequisicaoDAO) {
		this.numeroRequisicaoDAO = numeroRequisicaoDAO;
	}

	public void saveNumeroRequisicao(NumeroRequisicao numero) {
		numeroRequisicaoDAO.saveNumeroRequisicao(numero);

	}

	public NumeroRequisicao getNumeroRequisicao(Integer id) {

		return numeroRequisicaoDAO.getNumeroRequisicao(id);
	}

}
