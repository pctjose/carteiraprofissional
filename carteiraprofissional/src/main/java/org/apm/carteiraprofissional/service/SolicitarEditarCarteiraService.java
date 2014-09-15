package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.SolicitarEditarCarteira;

public interface SolicitarEditarCarteiraService {

	public void saveOrUpdate(SolicitarEditarCarteira solicitacao);

	public SolicitarEditarCarteira getById(Integer id);

	public List<SolicitarEditarCarteira> getAll(Boolean tratada);

	public void delete(SolicitarEditarCarteira solicitacao);
	
	public SolicitarEditarCarteira getByCarteiraAndNaoTratada(Carteira carteira);
}
