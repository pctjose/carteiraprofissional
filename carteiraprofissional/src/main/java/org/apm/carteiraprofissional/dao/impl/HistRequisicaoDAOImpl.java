package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.HistRequisicao;
import org.apm.carteiraprofissional.dao.HistRequisicaoDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HistRequisicaoDAOImpl implements HistRequisicaoDAO {

	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void saveOrUpdate(HistRequisicao histRequisicao) {
		sessionFactory.getCurrentSession().saveOrUpdate(histRequisicao);

	}

	@Transactional(readOnly=true)
	public HistRequisicao getById(Integer id) {
		
		return (HistRequisicao)sessionFactory.getCurrentSession().get(HistRequisicao.class, id);
	}

	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public List<HistRequisicao> getAll() {
		
		return sessionFactory.getCurrentSession()
				.createCriteria(HistRequisicao.class).list();
	}

}
