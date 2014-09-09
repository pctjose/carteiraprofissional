package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.HistCarteira;

public interface HistCarteiraService {
	public void saveOrUpdate(HistCarteira histCarteira);
	public HistCarteira getById(Integer id);
	public List<HistCarteira> getAll();
}
