package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.PropriedadesGlobais;

public interface PropriedadesGlobaisDAO {
	public void savePropriedade(PropriedadesGlobais propriedade);

	public PropriedadesGlobais getPropriedadeById(Integer id);

	public List<PropriedadesGlobais> getAllPropriedades();

	public void delete(PropriedadesGlobais propriedade);
	
	public PropriedadesGlobais getPropriedadeByName(String name);
}
