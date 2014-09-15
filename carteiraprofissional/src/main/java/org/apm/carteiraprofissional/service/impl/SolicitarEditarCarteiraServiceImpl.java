package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.SolicitarEditarCarteira;
import org.apm.carteiraprofissional.dao.SolicitarEditarCarteiraDAO;
import org.apm.carteiraprofissional.service.SolicitarEditarCarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitarEditarCarteiraServiceImpl implements
		SolicitarEditarCarteiraService {
	@Autowired
	private SolicitarEditarCarteiraDAO solicitarEditarCarteiraDAO;

	public SolicitarEditarCarteiraDAO getSolicitarEditarCarteiraDAO() {
		return solicitarEditarCarteiraDAO;
	}

	public void setSolicitarEditarCarteiraDAO(
			SolicitarEditarCarteiraDAO solicitarEditarCarteiraDAO) {
		this.solicitarEditarCarteiraDAO = solicitarEditarCarteiraDAO;
	}

	public void saveOrUpdate(SolicitarEditarCarteira solicitacao) {
		solicitarEditarCarteiraDAO.saveOrUpdate(solicitacao);

	}

	public SolicitarEditarCarteira getById(Integer id) {

		return solicitarEditarCarteiraDAO.getById(id);
	}

	public List<SolicitarEditarCarteira> getAll(Boolean tratada) {

		return solicitarEditarCarteiraDAO.getAll(tratada);
	}

	public void delete(SolicitarEditarCarteira solicitacao) {
		solicitarEditarCarteiraDAO.delete(solicitacao);

	}

	public SolicitarEditarCarteira getByCarteiraAndNaoTratada(Carteira carteira) {

		return solicitarEditarCarteiraDAO.getByCarteiraAndNaoTratada(carteira);
	}

}
