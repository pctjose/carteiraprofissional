package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.SolicitarEditarCarteira;
import org.apm.carteiraprofissional.dao.SolicitarEditarCarteiraDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SolicitarEditarCarteiraDAOImpl implements
		SolicitarEditarCarteiraDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdate(SolicitarEditarCarteira solicitacao) {
		sessionFactory.getCurrentSession().saveOrUpdate(solicitacao);

	}

	@Transactional
	public SolicitarEditarCarteira getById(Integer id) {

		return (SolicitarEditarCarteira) sessionFactory.getCurrentSession()
				.get(SolicitarEditarCarteira.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<SolicitarEditarCarteira> getAll(Boolean tratada) {

		Criteria c = sessionFactory.getCurrentSession().createCriteria(
				SolicitarEditarCarteira.class);
		if (tratada != null) {
			c.add(Restrictions.eq("tratada", tratada));
		}
		return c.list();
	}

	@Transactional
	public void delete(SolicitarEditarCarteira solicitacao) {
		sessionFactory.getCurrentSession().delete(solicitacao);

	}

	public SolicitarEditarCarteira getByCarteira(Carteira carteira,boolean tratada) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(
				SolicitarEditarCarteira.class);
		c.add(Restrictions.eq("carteira", carteira));		
		c.add(Restrictions.eq("tratada", tratada));
		return (SolicitarEditarCarteira) c.uniqueResult();
	}

}
