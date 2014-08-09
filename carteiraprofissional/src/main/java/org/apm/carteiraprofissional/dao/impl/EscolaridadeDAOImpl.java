package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.Escolaridade;
import org.apm.carteiraprofissional.dao.EscolaridadeDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		
		sessao.saveOrUpdate(nivel);
		
	}

	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Escolaridade> getAllNiveis() {
		Session sessao = sessionFactory.getCurrentSession();
		

		Criteria c = sessao.createCriteria(Escolaridade.class);
		c.addOrder(Order.asc("designacao"));

		List<Escolaridade> escolaridades = c.list();
		
		return escolaridades;
	}

	@Transactional(readOnly=true)
	public Escolaridade getByID(Integer id) {
		Session sessao = sessionFactory.getCurrentSession();
		
		Escolaridade nivel = (Escolaridade) sessao.get(Escolaridade.class, id);
		
		return nivel;
	}

	@Transactional(readOnly=true)
	public Escolaridade getByUUID(String uuid) {
		Session sessao = sessionFactory.getCurrentSession();
		
		Criteria cr = sessao.createCriteria(Escolaridade.class);
		cr.add(Restrictions.eq("uuid", uuid));
		Escolaridade nivel = (Escolaridade) cr.uniqueResult();
		
		return nivel;
	}

	@Transactional(readOnly=true)
	public Escolaridade getByDesignacao(String designacao) {
		Session sessao = sessionFactory.getCurrentSession();
		
		Criteria cr = sessao.createCriteria(Escolaridade.class);
		cr.add(Restrictions.eq("designacao", designacao));
		Escolaridade nivel = (Escolaridade) cr.uniqueResult();
		
		return nivel;
	}

	@Transactional
	public void delete(Escolaridade nivel) {
		Session sessao = sessionFactory.getCurrentSession();
		
		sessao.delete(nivel);
		

	}

}
