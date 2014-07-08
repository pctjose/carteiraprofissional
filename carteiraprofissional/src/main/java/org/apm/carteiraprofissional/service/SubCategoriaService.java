package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.SubCategoria;

public interface SubCategoriaService {

	public void saveSubCategoria(SubCategoria subCateg);

	public SubCategoria getByID(Integer id);

	public List<SubCategoria> getAllSubCategorias();

	public SubCategoria getByUUID(String uuid);

	public SubCategoria getByDesignacao(String designacao);

}
