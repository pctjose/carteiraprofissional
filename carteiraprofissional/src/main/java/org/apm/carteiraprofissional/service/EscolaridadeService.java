package org.apm.carteiraprofissional.service;

import java.util.List;

import org.apm.carteiraprofissional.Escolaridade;

public interface EscolaridadeService {
	
	
	
    public void saveUpdateEscolaridade(Escolaridade nivel);
     
    
    public Escolaridade getNivelByID(Integer id);
    
    public Escolaridade getNivelByUUID(String uuid);
    
   public Escolaridade getNivelByDesignacao(String designacao);

    
    public List<Escolaridade> getAllNiveis();
    
    public void delete(Escolaridade nivel);

}
