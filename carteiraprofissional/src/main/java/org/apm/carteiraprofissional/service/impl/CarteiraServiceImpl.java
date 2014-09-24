package org.apm.carteiraprofissional.service.impl;

import java.util.Date;
import java.util.List;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.dao.CarteiraDAO;
import org.apm.carteiraprofissional.service.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarteiraServiceImpl implements CarteiraService {

	@Autowired
	private CarteiraDAO carteiraDAO;

	public CarteiraDAO getCarteiraDAO() {
		return carteiraDAO;
	}

	public void setCarteiraDAO(CarteiraDAO carteiraDAO) {
		this.carteiraDAO = carteiraDAO;
	}

	public void saveCarteira(Carteira carteira) {
		carteiraDAO.saveCarteira(carteira);

	}

	public Carteira getCarteiraByID(Integer id) {

		return carteiraDAO.getCarteiraByID(id);
	}

	public List<Carteira> getAllCarteira(Requisitante requisitante,
			Boolean emitida) {

		return carteiraDAO.getAllCarteira(requisitante, emitida);
	}

	public List<Carteira> getAllCarteira() {

		return carteiraDAO.getAllCarteira(null, null);
	}

	public List<Carteira> getAllCarteiraEmitidas() {

		return carteiraDAO.getAllCarteira(null, true);
	}

	public List<Carteira> getAllCarteiraNaoEmitidas() {

		return carteiraDAO.getAllCarteira(null, false);
	}

	public List<Carteira> getAllCarteiraByDataEmissao(Date startDate,
			Date endDate) {

		return carteiraDAO.getAllCarteiraByDataEmissao(startDate, endDate);
	}

	public List<Carteira> getAllCarteiraByDataValidade(Date startDate,
			Date endDate) {

		return carteiraDAO.getAllCarteiraByDataValidade(startDate, endDate);
	}

	public Carteira getCarteiraByUUID(String uuid) {

		return carteiraDAO.getCarteiraByUUID(uuid);
	}

	public Carteira getCarteiraByRequisitante(Requisitante requisitante) {

		return carteiraDAO.getCarteiraByRequisitante(requisitante);
	}

	public List<Carteira> getAllByAttributes(String numeroCarteira,
			String nomeTitular, Date startDateEmissao,
			Date endDateEmissao, Date startDateValidade, Date endDateValidade,
			Boolean emitida) {

		return carteiraDAO.getAllByAttributes(numeroCarteira, nomeTitular,
				startDateEmissao, endDateEmissao,
				startDateValidade, endDateValidade, emitida);
	}

	public Carteira getCarteiraByRequisicao(Requisicao requisicao) {
		
		return carteiraDAO.getCarteiraByRequisicao(requisicao);
	}

}
