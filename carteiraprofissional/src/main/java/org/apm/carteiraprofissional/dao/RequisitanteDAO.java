package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.Requisitante;

public interface RequisitanteDAO {

	public void saveRequisitante(Requisitante requisitante);

	public Requisitante getRequisitanteById(Integer id);

	public Requisitante getRequisitanteByBI(String bi);

	public List<Requisitante> getAllRequisitantes();

	public void delete(Requisitante requisitante);

}
