package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.HistRequisicao;

public interface HistRequisicaoDAO {
	
	public void saveOrUpdate(HistRequisicao histRequisicao);
	public HistRequisicao getById(Integer id);
	public List<HistRequisicao> getAll();
}
