package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.NumeroRequisicao;

public interface NumeroRequisicaoService {

	public void saveNumeroRequisicao(NumeroRequisicao numero);

	public NumeroRequisicao getNumeroRequisicao(Integer id);
	
	public List<NumeroRequisicao> getAllNumeros();
}
