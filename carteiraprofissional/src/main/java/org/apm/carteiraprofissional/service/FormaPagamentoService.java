package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.FormaPagamento;

public interface FormaPagamentoService {
	public void saveFormaPagamento(FormaPagamento forma);

	public List<FormaPagamento> getAllFormas();

	public FormaPagamento getFormaById(Integer id);

	public FormaPagamento getFormaByUUID(String uuid);

	public FormaPagamento getFormaByName(String name);

	public void deleteForma(FormaPagamento forma);
}
