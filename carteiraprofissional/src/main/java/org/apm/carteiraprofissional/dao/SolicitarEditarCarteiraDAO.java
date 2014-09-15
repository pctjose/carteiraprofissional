package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.SolicitarEditarCarteira;

public interface SolicitarEditarCarteiraDAO {
	public void saveOrUpdate(SolicitarEditarCarteira solicitacao);

	public SolicitarEditarCarteira getById(Integer id);

	public List<SolicitarEditarCarteira> getAll(Boolean tratada);

	public void delete(SolicitarEditarCarteira solicitacao);
	
	public SolicitarEditarCarteira getByCarteiraAndNaoTratada(Carteira carteira);
}
