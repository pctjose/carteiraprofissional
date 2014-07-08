package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.Escolaridade;
import org.apm.carteiraprofissional.dao.EscolaridadeDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EscolaridadeDAOImpl implements EscolaridadeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Transactional
	public void saveUpdateEscolaridade(Escolaridade nivel) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		sessao.saveOrUpdate(nivel);
		//sessionFactory.getCurrentSession().saveOrUpdate(nivel);
		 tx.commit();
	}

	
	public List<Escolaridade> getAllNiveis() {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();

		Criteria c = sessao.createCriteria(Escolaridade.class);
		c.addOrder(Order.asc("designacao"));

		List<Escolaridade> escolaridades = c.list();
		 tx.commit();
		return escolaridades;
	}

	
	public Escolaridade getByID(Integer id) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		Escolaridade nivel = (Escolaridade) sessao.get(Escolaridade.class, id);
		tx.commit();
		return nivel;
	}

	
	public Escolaridade getByUUID(String uuid) {
		Session sessao = sessionFactory.getCurrentSession();
		// Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(Escolaridade.class);
		cr.add(Restrictions.eq("uuid", uuid));
		Escolaridade nivel = (Escolaridade) cr.uniqueResult();
		// tx.commit();
		return nivel;
	}

	
	public Escolaridade getByDesignacao(String designacao) {
		Session sessao = sessionFactory.getCurrentSession();
		// Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(Escolaridade.class);
		cr.add(Restrictions.eq("designacao", designacao));
		Escolaridade nivel = (Escolaridade) cr.uniqueResult();
		// tx.commit();
		return nivel;
	}

	
	public void delete(Escolaridade nivel) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		sessao.delete(nivel);
		tx.commit();

	}

}
