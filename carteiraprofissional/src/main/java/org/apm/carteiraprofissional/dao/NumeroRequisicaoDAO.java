package org.apm.carteiraprofissional.dao;

import org.apm.carteiraprofissional.NumeroRequisicao;

public interface NumeroRequisicaoDAO {
	public void saveNumeroRequisicao(NumeroRequisicao numero);

	public NumeroRequisicao getNumeroRequisicao(Integer id);
}
