package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.Empregador;
import org.apm.carteiraprofissional.dao.EmpregadorDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EmpregadorDAOImpl implements EmpregadorDAO {
	
	@Autowired
	 private SessionFactory sessionFactory;

	    public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    @Transactional
	    public void saveEmpregador(Empregador empregador) {

	        Session sessao = sessionFactory.getCurrentSession();
	       
	        sessao.saveOrUpdate(empregador);

	       

	    }

	    @Transactional(readOnly=true)
	    public Empregador getByID(Integer id) {
	        Session sessao = sessionFactory.getCurrentSession();
	       
	        Empregador empregador = (Empregador) sessao.get(Empregador.class, id);
	       
	        return empregador;
	    }

	    @Transactional(readOnly=true)
	    public Empregador getByUUID(String uuid) {
	        Session sessao = sessionFactory.getCurrentSession();
	       
	        Criteria cr = sessao.createCriteria(Empregador.class);
	        cr.add(Restrictions.eq("uuid", uuid));
	        Empregador empregador = (Empregador) cr.uniqueResult();
	       
	        return empregador;
	    }

	    @Transactional(readOnly=true)
	    public Empregador getByDesignacao(String designacao) {
	        Session sessao = sessionFactory.getCurrentSession();
	       
	        Criteria cr = sessao.createCriteria(Empregador.class);
	        cr.add(Restrictions.eq("designacao", designacao));
	        Empregador empregador = (Empregador) cr.uniqueResult();
	       
	        return empregador;
	    }

	    
	    @SuppressWarnings("unchecked")
	    @Transactional(readOnly=true)
		public List<Empregador> getAllEmpregadores() {
	        Session sessao = sessionFactory.getCurrentSession();
	        
	        Criteria c = sessao.createCriteria(Empregador.class);
	        c.addOrder(Order.asc("designacao"));
	       
			List<Empregador> empregadores = c.list();
	       
	        return empregadores;
	    }

		@Transactional
		public void delete(Empregador empregador) {
			Session sessao = sessionFactory.getCurrentSession();
	      
	        sessao.delete(empregador);
	      
	        
			
		}

}
