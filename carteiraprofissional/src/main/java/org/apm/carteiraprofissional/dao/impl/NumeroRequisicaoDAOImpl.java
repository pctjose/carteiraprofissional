package org.apm.carteiraprofissional.dao.impl;

import org.apm.carteiraprofissional.NumeroRequisicao;
import org.apm.carteiraprofissional.dao.NumeroRequisicaoDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NumeroRequisicaoDAOImpl implements NumeroRequisicaoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveNumeroRequisicao(NumeroRequisicao numero) {
		sessionFactory.getCurrentSession().saveOrUpdate(numero);

	}

	@Transactional(readOnly=true)
	public NumeroRequisicao getNumeroRequisicao(Integer id) {

		return (NumeroRequisicao) sessionFactory.getCurrentSession().get(
				NumeroRequisicao.class, id);
	}

}
