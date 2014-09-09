package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.HistRequisitante;
import org.apm.carteiraprofissional.dao.HistRequisitanteDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HistRequisitanteDAOImpl implements HistRequisitanteDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdate(HistRequisitante histRequisitante) {
		sessionFactory.getCurrentSession().saveOrUpdate(histRequisitante);

	}

	@Transactional(readOnly = true)
	public HistRequisitante getById(Integer id) {

		return (HistRequisitante) sessionFactory.getCurrentSession().get(
				HistRequisitante.class, id);
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<HistRequisitante> getAll() {
		return sessionFactory.getCurrentSession()
				.createCriteria(HistRequisitante.class).list();
	}

}
