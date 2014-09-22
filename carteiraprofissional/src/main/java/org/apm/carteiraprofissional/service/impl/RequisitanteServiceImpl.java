package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.dao.RequisitanteDAO;
import org.apm.carteiraprofissional.service.RequisitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequisitanteServiceImpl implements RequisitanteService {

	@Autowired
	private RequisitanteDAO requisitanteDAO;

	public RequisitanteDAO getRequisitanteDAO() {
		return requisitanteDAO;
	}

	public void setRequisitanteDAO(RequisitanteDAO requisitanteDAO) {
		this.requisitanteDAO = requisitanteDAO;
	}

	@Transactional
	public void saveRequisitante(Requisitante requisicao) {

		requisitanteDAO.saveRequisitante(requisicao);
	}

	@Transactional(readOnly = true)
	public Requisitante getRequisitanteById(Integer id) {
		return requisitanteDAO.getRequisitanteById(id);
	}

	@Transactional(readOnly = true)
	public Requisitante getRequisitanteByBI(String bi) {
		return requisitanteDAO.getRequisitanteByBI(bi);
	}

	@Transactional(readOnly = true)
	public List<Requisitante> getAllRequisitantes() {
		return requisitanteDAO.getAllRequisitantes();
	}

	public void delete(Requisitante requisitante) {
		requisitanteDAO.delete(requisitante);

	}

	public Requisitante getRequisitanteByEmail(String email) {
		
		return requisitanteDAO.getRequisitanteByEmail(email);
	}

}
