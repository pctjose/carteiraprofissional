package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.Empregador;


public interface EmpregadorService {

	public void saveEmpregador(Empregador emp);

	public Empregador getByID(Integer id);

	public Empregador getByUUID(String uuid);

	public Empregador getByNome(String nome);

	public List<Empregador> getAllEmpregador();
	
	public void delete(Empregador empregador);

}
