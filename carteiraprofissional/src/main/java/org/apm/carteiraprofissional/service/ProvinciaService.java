package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.Pais;
import org.apm.carteiraprofissional.Provincia;

public interface ProvinciaService {
	
	public void saveProvincia(Provincia provincia);
	public Provincia getByID(Integer id);
	public Provincia getByDesignacao(String designacao);
	public List<Provincia> getAllProvincia();
	public List<Provincia> getAllProvincia(Pais pais);
	public void delete(Provincia provincia);

}
