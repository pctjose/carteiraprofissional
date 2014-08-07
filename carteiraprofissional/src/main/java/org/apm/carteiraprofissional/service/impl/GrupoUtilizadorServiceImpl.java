package org.apm.carteiraprofissional.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apm.carteiraprofissional.GrupoUtilizador;
import org.apm.carteiraprofissional.dao.GrupoUtilizadorDAO;
import org.apm.carteiraprofissional.service.GrupoUtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	
	public void saveGrupo(GrupoUtilizador grupo) {
		grupoUtilizadorDAO.saveGrupo(grupo);

	}

	
	public GrupoUtilizador getById(Integer id) {

		return grupoUtilizadorDAO.getById(id);
	}

	
	public GrupoUtilizador getByDesignacao(String designacao) {

		return grupoUtilizadorDAO.getByDesignacao(designacao);
	}

	
	public List<GrupoUtilizador> getAllGrupos() {

		return grupoUtilizadorDAO.getAllGrupos();
	}

	public List<GrupoUtilizador> getDefaultGrupo() {
		List<GrupoUtilizador> defaults = new ArrayList<GrupoUtilizador>();
		GrupoUtilizador defaultGrupo = grupoUtilizadorDAO.getById(2);

		defaults.add(defaultGrupo);

		return defaults;
	}

}
