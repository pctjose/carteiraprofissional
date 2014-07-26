package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.FormaPagamento;
import org.apm.carteiraprofissional.dao.FormaPagamentoDAO;
import org.apm.carteiraprofissional.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormaPagamentoServiceImpl implements FormaPagamentoService {

	@Autowired
	private FormaPagamentoDAO formaPagamentoDAO;

	public FormaPagamentoDAO getFormaPagamentoDAO() {
		return formaPagamentoDAO;
	}

	public void setFormaPagamentoDAO(FormaPagamentoDAO formaPagamentoDAO) {
		this.formaPagamentoDAO = formaPagamentoDAO;
	}

	public void saveFormaPagamento(FormaPagamento forma) {
		formaPagamentoDAO.saveFormaPagamento(forma);

	}

	public List<FormaPagamento> getAllFormas() {

		return formaPagamentoDAO.getAllFormas();
	}

	public FormaPagamento getFormaById(Integer id) {

		return formaPagamentoDAO.getFormaById(id);
	}

	public FormaPagamento getFormaByUUID(String uuid) {

		return formaPagamentoDAO.getFormaByUUID(uuid);
	}

	public FormaPagamento getFormaByName(String name) {
		return formaPagamentoDAO.getFormaByName(name);

	}

	public void deleteForma(FormaPagamento forma) {
		formaPagamentoDAO.deleteForma(forma);

	}

}
