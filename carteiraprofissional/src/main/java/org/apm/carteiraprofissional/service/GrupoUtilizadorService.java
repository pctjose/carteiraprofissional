package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.GrupoUtilizador;

public interface GrupoUtilizadorService {
	public void saveGrupo(GrupoUtilizador grupo);

	public GrupoUtilizador getById(Integer id);

	public GrupoUtilizador getByDesignacao(String designacao);

	public List<GrupoUtilizador> getAllGrupos();
	
	public List<GrupoUtilizador> getDefaultGrupo();
}
