package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.GrupoUtilizador;

public interface GrupoUtilizadorDAO {

	public void saveGrupo(GrupoUtilizador grupo);

	public GrupoUtilizador getById(Integer id);

	public GrupoUtilizador getByDesignacao(String designacao);

	public List<GrupoUtilizador> getAllGrupos();
	
	public void inserirGrupo();

}
