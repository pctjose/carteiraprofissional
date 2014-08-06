package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.FormaPagamento;
import org.apm.carteiraprofissional.dao.FormaPagamentoDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FormaPagamentoDAOImpl implements FormaPagamentoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveFormaPagamento(FormaPagamento forma) {
		sessionFactory.getCurrentSession().saveOrUpdate(forma);

	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<FormaPagamento> getAllFormas() {
		return sessionFactory.getCurrentSession()
				.createCriteria(FormaPagamento.class).list();
	}

	@Transactional(readOnly=true)
	public FormaPagamento getFormaById(Integer id) {

		return (FormaPagamento) sessionFactory.getCurrentSession().get(
				FormaPagamento.class, id);
	}

	@Transactional(readOnly=true)
	public FormaPagamento getFormaByUUID(String uuid) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(FormaPagamento.class);
		cr.add(Restrictions.eq("uuid", uuid));
		FormaPagamento pagamento = (FormaPagamento) cr.uniqueResult();
		return pagamento;
	}

	@Transactional(readOnly=true)
	public FormaPagamento getFormaByName(String name) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(FormaPagamento.class);
		cr.add(Restrictions.eq("designacao", name));
		FormaPagamento pagamento = (FormaPagamento) cr.uniqueResult();
		return pagamento;
	}

	@Transactional
	public void deleteForma(FormaPagamento forma) {
		sessionFactory.getCurrentSession().delete(forma);

	}

}
