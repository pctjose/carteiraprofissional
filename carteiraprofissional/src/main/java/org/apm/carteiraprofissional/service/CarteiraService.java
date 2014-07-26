package org.apm.carteiraprofissional.service;

import java.util.Date;
import java.util.List;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.Requisitante;

public interface CarteiraService {
public void saveCarteira(Carteira carteira);
	
	public Carteira getCarteiraByID(Integer id);
	
	public List<Carteira> getAllCarteira(Requisitante requisitante,Boolean emitida);
	public List<Carteira> getAllCarteira();
	public List<Carteira> getAllCarteiraEmitidas();
	public List<Carteira> getAllCarteiraNaoEmitidas();
	
	public List<Carteira> getAllCarteiraByDataEmissao(Date startDate,Date endDate);
	
	public List<Carteira> getAllCarteiraByDataValidade(Date startDate, Date endDate);
	
	public Carteira getCarteiraByUUID(String uuid);
	
	public Carteira getCarteiraByRequisitante(Requisitante requisitante);
}
