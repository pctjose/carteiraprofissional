package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.SubCategoria;

public interface SubCategoriaDAO {

	public void addSubCategoria(SubCategoria subcategoria);

	public List<SubCategoria> getAllProfissoes();

	public SubCategoria getByID(Integer id);

	public SubCategoria getByUUID(String uuid);

	public SubCategoria getByDesignacao(String designacao);

}
