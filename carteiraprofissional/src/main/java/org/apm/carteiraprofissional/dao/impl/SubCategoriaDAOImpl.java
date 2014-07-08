package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.SubCategoria;
import org.apm.carteiraprofissional.dao.SubCategoriaDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class SubCategoriaDAOImpl implements SubCategoriaDAO{
	
	
	private SessionFactory sessionFactory;
	private static Logger log = Logger.getLogger(SubCategoriaDAOImpl.class);

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public void addSubCategoria(SubCategoria profissao) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		try {
			sessao.saveOrUpdate(profissao);
			tx.commit();
		} catch (Exception e) {
			log.info(e.getMessage());
			tx.rollback();
		}
		
	}

	
	public List<SubCategoria> getAllProfissoes() {
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();
		Criteria c = sessao.createCriteria(SubCategoria.class);
		c.addOrder(Order.asc("designacao"));
		List<SubCategoria> profissoes = c.list();
		//tx.commit();
		return profissoes;
	}

	
	public SubCategoria getByID(Integer id) {
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();
		SubCategoria prof = (SubCategoria) sessao.get(SubCategoria.class, id);
		//tx.commit();
		return prof;
	}

	
	public SubCategoria getByUUID(String uuid) {
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(SubCategoria.class);
		cr.add(Restrictions.eq("uuid", uuid));
		SubCategoria prof = (SubCategoria) cr.uniqueResult();
		//tx.commit();
		return prof;
	}

	
	public SubCategoria getByDesignacao(String designacao) {
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(SubCategoria.class);
		cr.add(Restrictions.eq("designacao", designacao));
		SubCategoria prof = (SubCategoria) cr.uniqueResult();
		//tx.commit();
		return prof;

	}
	

}
