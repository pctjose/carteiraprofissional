package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.HistCarteira;
import org.apm.carteiraprofissional.dao.HistCarteiraDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HistCarteiraDAOImpl implements HistCarteiraDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdate(HistCarteira histCarteira) {
		sessionFactory.getCurrentSession().saveOrUpdate(histCarteira);

	}

	@Transactional(readOnly=true)
	public HistCarteira getById(Integer id) {

		return (HistCarteira) sessionFactory.getCurrentSession().get(
				HistCarteira.class, id);
	}

	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public List<HistCarteira> getAll() {

		return sessionFactory.getCurrentSession()
				.createCriteria(HistCarteira.class).list();
	}

}
