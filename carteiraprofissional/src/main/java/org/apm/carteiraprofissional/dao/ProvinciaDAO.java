package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.Pais;
import org.apm.carteiraprofissional.Provincia;

public interface ProvinciaDAO {
	
	public void saveProvincia(Provincia provincia);
	public Provincia getByID(Integer id);
	public Provincia getByDesignacao(String designacao);
	public List<Provincia> getAllProvincia(Pais...paises );
	public void delete(Provincia provincia);

}
