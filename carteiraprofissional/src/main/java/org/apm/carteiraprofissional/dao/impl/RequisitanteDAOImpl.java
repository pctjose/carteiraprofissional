package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.dao.RequisitanteDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RequisitanteDAOImpl implements RequisitanteDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveRequisitante(Requisitante requisitante) {
		Session sessao = sessionFactory.getCurrentSession();
		
		sessao.saveOrUpdate(requisitante);
		

	}

	@Transactional(readOnly=true)
	public Requisitante getRequisitanteById(Integer id) {
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();
		Requisitante requisicao = (Requisitante) sessao.get(Requisitante.class,
				id);
		//tx.commit();
		return requisicao;
	}

	@Transactional(readOnly=true)
	public Requisitante getRequisitanteByBI(String bi) {
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(Requisitante.class);
		cr.add(Restrictions.eq("numeroBi", bi));
		Requisitante requisicao = (Requisitante) cr.uniqueResult();
		//tx.commit();
		return requisicao;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Requisitante> getAllRequisitantes() {
		Session sessao = sessionFactory.getCurrentSession();
		
		Criteria c = sessao.createCriteria(Requisitante.class);
		c.addOrder(Order.asc("nome"));
		List<Requisitante> requisicoes = c.list();
		
		return requisicoes;
	}

	@Transactional
	public void delete(Requisitante requisitante) {
		sessionFactory.getCurrentSession().delete(requisitante);

	}

	@Transactional(readOnly=true)
	public Requisitante getRequisitanteByEmail(String email) {
		Session sessao = sessionFactory.getCurrentSession();		
		Criteria cr = sessao.createCriteria(Requisitante.class);
		cr.add(Restrictions.eq("email", email));
		Requisitante requisicao = (Requisitante) cr.uniqueResult();
		
		return requisicao;
	}

}
