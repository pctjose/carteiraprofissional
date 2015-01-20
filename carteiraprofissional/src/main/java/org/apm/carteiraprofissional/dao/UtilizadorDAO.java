package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.GrupoUtilizador;
import org.apm.carteiraprofissional.Utilizador;

public interface UtilizadorDAO {
	
	 	public void saveUtilizador(Utilizador utilizador);
	    
	    public Utilizador getUtilizadorByID(Integer id);
	    
	    public Utilizador getUtilizador(String login);
	    
	    public Utilizador getUtilizadorByUserNameAndPassword(String username,String senha);
	    
	    public Utilizador getUtilizadorByUUID(String uuid);
	    
	    public List<Utilizador> getAllUtilizadores(Boolean includeVoided);
	    
	    public void delete(Utilizador utilizador);
	    
	    public List<Utilizador> getUserByAttributes(String nomeCompleto,String sexo,GrupoUtilizador grupo,Boolean incluirAnulado);
	    
	   public List<Utilizador> getAllUtilizador();
	   
	   public void inserirUtilizador();

}
