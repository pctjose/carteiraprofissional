package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.PropriedadesGlobais;

public interface PropriedadesGlobaisService {
public void savePropriedade(PropriedadesGlobais propriedade);
	
	public PropriedadesGlobais getPropriedadeById(Integer id);
	
	public List<PropriedadesGlobais> getAllPropriedades();
	
	public void delete(PropriedadesGlobais propriedade);
	
	public PropriedadesGlobais getPropriedadeByName(String name);
}
