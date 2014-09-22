package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.NumeroRequisicao;

public interface NumeroRequisicaoDAO {
	public void saveNumeroRequisicao(NumeroRequisicao numero);

	public NumeroRequisicao getNumeroRequisicao(Integer id);
	
	public List<NumeroRequisicao>getAllNumeros();
}
