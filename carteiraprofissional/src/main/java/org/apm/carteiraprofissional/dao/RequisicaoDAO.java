package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;

public interface RequisicaoDAO {
	public void saveRequisicao(Requisicao requisicao);

	public void deleteRequisicao(Requisicao requisicao);

	public Requisicao getRequisicaoByID(Integer id);

	public Requisicao getRequisicaoByUUID(String uuid);

	public Requisicao getRequisicaoByNumero(String numeroRequisicao);

	public List<Requisicao> getAllRequisicoes();
	
	public Requisicao getRequisicaoByRequisitante(Requisitante requisitante);
}
