package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.Categoria;

public interface CategoriaDAO {

	public void saveCategoria(Categoria categoria);

	public List<Categoria> getAllCategorias();

	public Categoria getByID(Integer id);

	public Categoria getByUUID(String uuid);

	public Categoria getByDesignacao(String designacao);
	
	public void deleCategoria(Categoria categoria);

}
