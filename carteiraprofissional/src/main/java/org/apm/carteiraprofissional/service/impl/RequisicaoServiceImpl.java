package org.apm.carteiraprofissional.service.impl;

import java.util.Date;
import java.util.List;

import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.dao.RequisicaoDAO;
import org.apm.carteiraprofissional.service.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequisicaoServiceImpl implements RequisicaoService {

	@Autowired
	private RequisicaoDAO requisicaoDAO;

	public RequisicaoDAO getRequisicaoDAO() {
		return requisicaoDAO;
	}

	public void setRequisicaoDAO(RequisicaoDAO requisicaoDAO) {
		this.requisicaoDAO = requisicaoDAO;
	}

	public void saveRequisicao(Requisicao requisicao) {
		requisicaoDAO.saveRequisicao(requisicao);

	}

	public void deleteRequisicao(Requisicao requisicao) {
		requisicaoDAO.deleteRequisicao(requisicao);

	}

	public Requisicao getRequisicaoByID(Integer id) {

		return requisicaoDAO.getRequisicaoByID(id);
	}

	public Requisicao getRequisicaoByUUID(String uuid) {

		return requisicaoDAO.getRequisicaoByUUID(uuid);
	}

	public Requisicao getRequisicaoByNumero(String numeroRequisicao) {

		return requisicaoDAO.getRequisicaoByNumero(numeroRequisicao);
	}

	public List<Requisicao> getAllRequisicoes() {

		return requisicaoDAO.getAllRequisicoes();
	}

	public Requisicao getRequisicaoByRequisitante(Requisitante requisitante) {

		return requisicaoDAO.getRequisicaoByRequisitante(requisitante);
	}

	public List<Requisicao> getRequisicaoByAttributes(String numeroRequisicao,
			String nome, Date dataInicial, Date dataFinal,
			Boolean aceite, Boolean completa) {

		return requisicaoDAO.getRequisicaoByAttributes(numeroRequisicao, nome,
				dataInicial, dataFinal, aceite, completa);
	}

}
