package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.Escolaridade;

public interface EscolaridadeDAO {
	
	public void saveUpdateEscolaridade(Escolaridade nivel);
    public List<Escolaridade>getAllNiveis();
    public Escolaridade getByID(Integer id);
    public Escolaridade getByUUID(String uuid);
    public Escolaridade getByDesignacao(String designacao); 
    public void delete(Escolaridade nivel);

}
