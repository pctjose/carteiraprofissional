package org.apm.carteiraprofissional.service;

import org.apm.carteiraprofissional.NumeroRequisicao;

public interface NumeroRequisicaoService {

	public void saveNumeroRequisicao(NumeroRequisicao numero);

	public NumeroRequisicao getNumeroRequisicao(Integer id);
}
