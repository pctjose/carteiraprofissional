package org.apm.carteiraprofissional.dao;

import java.util.List;

import org.apm.carteiraprofissional.Empregador;

public interface EmpregadorDAO {

public void saveEmpregador(Empregador empregador);
    
    public Empregador getByID(Integer id);
    
    public Empregador getByUUID(String uuid);
    
    public Empregador getByDesignacao(String designacao);
    
    public List<Empregador> getAllEmpregadores();
    
    public void delete(Empregador empregador);
}
