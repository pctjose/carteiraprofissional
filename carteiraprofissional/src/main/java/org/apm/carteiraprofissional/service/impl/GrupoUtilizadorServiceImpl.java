package org.apm.carteiraprofissional.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apm.carteiraprofissional.GrupoUtilizador;
import org.apm.carteiraprofissional.dao.GrupoUtilizadorDAO;
import org.apm.carteiraprofissional.service.GrupoUtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoUtilizadorServiceImpl implements GrupoUtilizadorService {

	@Autowired
	private GrupoUtilizadorDAO grupoUtilizadorDAO;

	public GrupoUtilizadorDAO getGrupoUtilizadorDAO() {
		return grupoUtilizadorDAO;
	}

	public void setGrupoUtilizadorDAO(GrupoUtilizadorDAO grupoUtilizadorDAO) {
		this.grupoUtilizadorDAO = grupoUtilizadorDAO;
	}

	@Transactional
	public void saveGrupo(GrupoUtilizador grupo) {
		grupoUtilizadorDAO.saveGrupo(grupo);

	}

	@Transactional(readOnly = true)
	public GrupoUtilizador getById(Integer id) {

		return grupoUtilizadorDAO.getById(id);
	}

	@Transactional(readOnly = true)
	public GrupoUtilizador getByDesignacao(String designacao) {

		return grupoUtilizadorDAO.getByDesignacao(designacao);
	}

	@Transactional(readOnly = true)
	public List<GrupoUtilizador> getAllGrupos() {

		return grupoUtilizadorDAO.getAllGrupos();
	}

	public List<GrupoUtilizador> getDefaultGrupo() {
		List<GrupoUtilizador> defaults = new ArrayList<GrupoUtilizador>();
		GrupoUtilizador defaultGrupo = grupoUtilizadorDAO.getById(3);

		defaults.add(defaultGrupo);

		return defaults;
	}

}
