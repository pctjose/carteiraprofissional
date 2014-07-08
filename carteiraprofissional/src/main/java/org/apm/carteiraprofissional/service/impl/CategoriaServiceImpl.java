package org.apm.carteiraprofissional.service.impl;

import java.util.List;

import org.apm.carteiraprofissional.Categoria;
import org.apm.carteiraprofissional.dao.CategoriaDAO;
import org.apm.carteiraprofissional.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaDAO categoriaDAO;

	public CategoriaDAO getCategoriaDAO() {
		return categoriaDAO;
	}

	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	@Transactional
	public void saveCategoria(Categoria categoria) {
		categoriaDAO.saveCategoria(categoria);

	}

	@Transactional(readOnly = true)
	public Categoria getByID(Integer id) {

		return categoriaDAO.getByID(id);
	}

	@Transactional(readOnly = true)
	public List<Categoria> getAllCategorias() {

		return categoriaDAO.getAllCategorias();
	}

	@Transactional(readOnly = true)
	public Categoria getByUUID(String uuid) {

		return categoriaDAO.getByUUID(uuid);
	}

	@Transactional(readOnly = true)
	public Categoria getByDesignacao(String designacao) {

		return categoriaDAO.getByDesignacao(designacao);
	}

	@Transactional
	public void deleteCategoria(Categoria categoria) {
		categoriaDAO.deleCategoria(categoria);

	}

}
