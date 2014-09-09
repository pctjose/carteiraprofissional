package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.SolicitarEditarRequisicao;

public interface SolicitarEditarRequisicaoDAO {
	public void saveOrUpdate(SolicitarEditarRequisicao solicitacao);

	public SolicitarEditarRequisicao getById(Integer id);

	public List<SolicitarEditarRequisicao> getAll(Boolean tratada);

	public void delete(SolicitarEditarRequisicao solicitacao);
}
