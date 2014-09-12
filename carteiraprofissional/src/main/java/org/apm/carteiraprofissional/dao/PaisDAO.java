package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.Pais;

public interface PaisDAO {

	public void savePais(Pais pais);
	public Pais getByID(Integer id);
	public Pais getByDesignacao(String designacao);
	public List<Pais> getAllPaises();
	public void delete(Pais pais);
}
