package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.HistRequisitante;

public interface HistRequisitanteDAO {
	public void saveOrUpdate(HistRequisitante histRequisitante);
	public HistRequisitante getById(Integer id);
	public List<HistRequisitante> getAll();
}
