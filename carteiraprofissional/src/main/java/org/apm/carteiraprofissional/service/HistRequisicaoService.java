package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.HistRequisicao;

public interface HistRequisicaoService {
	public void saveOrUpdate(HistRequisicao histRequisicao);
	public HistRequisicao getById(Integer id);
	public List<HistRequisicao> getAll();
}
