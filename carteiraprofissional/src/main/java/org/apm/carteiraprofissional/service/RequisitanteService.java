package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.Requisitante;

public interface RequisitanteService {
	
	public void saveRequisitante(Requisitante requisitante);
	
	public Requisitante getRequisitanteById(Integer id);
	
	public Requisitante getRequisitanteByBI(String bi);
	
	public List<Requisitante> getAllRequisitantes();
	
	public void delete(Requisitante requisitante);
	
	public Requisitante getRequisitanteByEmail(String email);

}
