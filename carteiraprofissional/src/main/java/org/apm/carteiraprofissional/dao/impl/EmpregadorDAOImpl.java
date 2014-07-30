package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.Empregador;
import org.apm.carteiraprofissional.dao.EmpregadorDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class EmpregadorDAOImpl implements EmpregadorDAO {
	
	
	 private SessionFactory sessionFactory;

	    public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    
	    public void saveEmpregador(Empregador empregador) {

	        Session sessao = sessionFactory.getCurrentSession();
	       // Transaction tx = sessao.beginTransaction();
	        sessao.saveOrUpdate(empregador);

	        //tx.commit();

	    }

	   
	    public Empregador getByID(Integer id) {
	        Session sessao = sessionFactory.getCurrentSession();
	       // Transaction tx = sessao.beginTransaction();
	        Empregador empregador = (Empregador) sessao.get(Empregador.class, id);
	        //tx.commit();
	        return empregador;
	    }

	    
	    public Empregador getByUUID(String uuid) {
	        Session sessao = sessionFactory.getCurrentSession();
	       // Transaction tx = sessao.beginTransaction();
	        Criteria cr = sessao.createCriteria(Empregador.class);
	        cr.add(Restrictions.eq("uuid", uuid));
	        Empregador empregador = (Empregador) cr.uniqueResult();
	        //tx.commit();
	        return empregador;
	    }

	    
	    public Empregador getByDesignacao(String designacao) {
	        Session sessao = sessionFactory.getCurrentSession();
	        //Transaction tx = sessao.beginTransaction();
	        Criteria cr = sessao.createCriteria(Empregador.class);
	        cr.add(Restrictions.eq("designacao", designacao));
	        Empregador empregador = (Empregador) cr.uniqueResult();
	        //tx.commit();
	        return empregador;
	    }

	    
	    @SuppressWarnings("unchecked")
		public List<Empregador> getAllEmpregadores() {
	        Session sessao = sessionFactory.getCurrentSession();
	        //Transaction tx = sessao.beginTransaction();
	        Criteria c = sessao.createCriteria(Empregador.class);
	        c.addOrder(Order.asc("designacao"));
	       
			List<Empregador> empregadores = c.list();
	       //tx.commit();
	        return empregadores;
	    }

		
		public void delete(Empregador empregador) {
			Session sessao = sessionFactory.getCurrentSession();
	       // Transaction tx = sessao.beginTransaction();
	        sessao.delete(empregador);
	       // tx.commit();
	        
			
		}

}
