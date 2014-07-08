package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.Empregador;
import org.apm.carteiraprofissional.dao.EmpregadorDAO;
import org.apm.carteiraprofissional.service.EmpregadorService;

public class EmpregadorServiceImpl implements EmpregadorService {

	private EmpregadorDAO empregadorDAO;

	public EmpregadorDAO getEmpregadorDAO() {
		return empregadorDAO;
	}

	public void setEmpregadorDAO(EmpregadorDAO empregadorDAO) {
		this.empregadorDAO = empregadorDAO;
	}

	public void saveEmpregador(Empregador emp) {
		// TODO Auto-generated method stub
		
	}

	public Empregador getByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Empregador getByUUID(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	public Empregador getByNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Empregador> getAllEmpregador() {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Empregador empregador) {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void saveEmpregador(Empregador emp) {
		empregadorDAO.saveEmpregador(emp);
	}

	@Override
	public Empregador getByID(Integer id) {
		return empregadorDAO.getByID(id);
	}

	@Override
	public Empregador getByUUID(String uuid) {
		return empregadorDAO.getByUUID(uuid);
	}

	@Override
	public Empregador getByNome(String nome) {
		return empregadorDAO.getByDesignacao(nome);
	}

	@Override
	public List<Empregador> getAllEmpregador() {
		return empregadorDAO.getAllEmpregadores();
	}

	@Override
	public void delete(Empregador empregador) {
		empregadorDAO.delete(empregador);
		
	}*/

}
