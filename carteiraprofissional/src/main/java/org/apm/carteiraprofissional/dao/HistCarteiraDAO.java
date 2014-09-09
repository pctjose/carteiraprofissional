package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.HistCarteira;

public interface HistCarteiraDAO {
	public void saveOrUpdate(HistCarteira histCarteira);
	public HistCarteira getById(Integer id);
	public List<HistCarteira> getAll();
}
