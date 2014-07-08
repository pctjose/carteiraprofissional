package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.Pais;

public interface PaisService {
	
	public void savePais(Pais pais);
	public Pais getByID(Integer id);
	public Pais getByDesignacao(String designacao);
	public List<Pais> getAllPaises();

}
