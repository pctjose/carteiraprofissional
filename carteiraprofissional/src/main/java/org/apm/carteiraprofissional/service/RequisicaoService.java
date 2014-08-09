package org.apm.carteiraprofissional.service;

import java.util.Date;
import java.util.List;

import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;

public interface RequisicaoService {
	
	public void saveRequisicao(Requisicao requisicao);

	public void deleteRequisicao(Requisicao requisicao);

	public Requisicao getRequisicaoByID(Integer id);

	public Requisicao getRequisicaoByUUID(String uuid);

	public Requisicao getRequisicaoByNumero(String numeroRequisicao);

	public List<Requisicao> getAllRequisicoes();
	
	public Requisicao getRequisicaoByRequisitante(Requisitante requisitante);
	
	public List<Requisicao> getRequisicaoByAttributes(String numeroRequisicao,
			String nome, String apelido, Date dataInicial, Date dataFinal,
			Boolean aceite, Boolean completa);

}
