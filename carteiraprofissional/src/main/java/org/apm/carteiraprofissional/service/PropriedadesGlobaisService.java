package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.PropriedadesGlobais;

public interface PropriedadesGlobaisService {
public void savePropriedade(PropriedadesGlobais propriedade);
	
	public PropriedadesGlobais getPropriedadeById(String propName);
	
	public List<PropriedadesGlobais> getAllPropriedades();
	
	public void delete(PropriedadesGlobais propriedade);
}
