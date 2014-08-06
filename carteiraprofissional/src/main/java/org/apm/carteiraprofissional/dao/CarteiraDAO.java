package org.apm.carteiraprofissional.dao;

import java.util.Date;
import java.util.List;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.Requisitante;

public interface CarteiraDAO {
	public void saveCarteira(Carteira carteira);
	
	public Carteira getCarteiraByID(Integer id);
	
	public List<Carteira> getAllCarteira(Requisitante requisitante,Boolean emitida);
	
	public List<Carteira> getAllCarteiraByDataEmissao(Date startDate,Date endDate);
	
	public List<Carteira> getAllCarteiraByDataValidade(Date startDate, Date endDate);
	
	public Carteira getCarteiraByUUID(String uuid);
	
	public Carteira getCarteiraByRequisitante(Requisitante requisitante);
	
	public List<Carteira> getAllByAttributes(String numeroCarteira,String nomeTitular,String apelidoTitular,Date startDateEmissao,Date endDateEmissao,Date startDateValidade,Date endDateValidade,Boolean emitida);
	
}
