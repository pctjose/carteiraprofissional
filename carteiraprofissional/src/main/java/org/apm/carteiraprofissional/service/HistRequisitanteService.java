package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.HistRequisitante;

public interface HistRequisitanteService {
	public void saveOrUpdate(HistRequisitante histRequisitante);
	public HistRequisitante getById(Integer id);
	public List<HistRequisitante> getAll();
}
