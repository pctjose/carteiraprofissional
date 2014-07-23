package org.apm.carteiraprofissional.dao.impl;

import org.apm.carteiraprofissional.NumeroRequisicao;
import org.apm.carteiraprofissional.dao.NumeroRequisicaoDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

	public void saveNumeroRequisicao(NumeroRequisicao numero) {
		sessionFactory.getCurrentSession().saveOrUpdate(numero);

	}

	public NumeroRequisicao getNumeroRequisicao(Integer id) {

		return (NumeroRequisicao) sessionFactory.getCurrentSession().get(
				NumeroRequisicao.class, id);
	}

}
