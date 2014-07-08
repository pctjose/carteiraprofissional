package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.Categoria;



public interface CategoriaService {

	public void saveCategoria(Categoria categoria);

	public Categoria getByID(Integer id);

	public List<Categoria> getAllCategorias();

	public Categoria getByUUID(String uuid);

	public Categoria getByDesignacao(String designacao);
	
	public void deleteCategoria(Categoria categoria);

}
