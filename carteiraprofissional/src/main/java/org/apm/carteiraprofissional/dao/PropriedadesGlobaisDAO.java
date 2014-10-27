package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.PropriedadesGlobais;

public interface PropriedadesGlobaisDAO {
	public void savePropriedade(PropriedadesGlobais propriedade);

	public PropriedadesGlobais getPropriedadeById(String propName);

	public List<PropriedadesGlobais> getAllPropriedades();

	public void delete(PropriedadesGlobais propriedade);
}
