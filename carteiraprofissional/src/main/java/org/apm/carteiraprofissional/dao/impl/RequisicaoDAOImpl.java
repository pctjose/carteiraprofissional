package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.dao.RequisicaoDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RequisicaoDAOImpl implements RequisicaoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveRequisicao(Requisicao requisicao) {
		sessionFactory.getCurrentSession().saveOrUpdate(requisicao);

	}

	public void deleteRequisicao(Requisicao requisicao) {
		sessionFactory.getCurrentSession().delete(requisicao);

	}

	public Requisicao getRequisicaoByID(Integer id) {
		// TODO Auto-generated method stub
		return (Requisicao) sessionFactory.getCurrentSession().get(
				Requisicao.class, id);
	}

	public Requisicao getRequisicaoByUUID(String uuid) {
		Session sessao=sessionFactory.getCurrentSession();
		Criteria cr=sessao.createCriteria(Requisicao.class);
		cr.add(Restrictions.eq("uuid", uuid));
		return (Requisicao)cr.uniqueResult();
	}

	public Requisicao getRequisicaoByNumero(String numeroRequisicao) {
		Session sessao=sessionFactory.getCurrentSession();
		Criteria cr=sessao.createCriteria(Requisicao.class);
		cr.add(Restrictions.eq("numeroRequisicao", numeroRequisicao));
		return (Requisicao)cr.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Requisicao> getAllRequisicoes() {
		Session sessao=sessionFactory.getCurrentSession();
		Criteria cr=sessao.createCriteria(Requisicao.class);
		return (List<Requisicao>) cr.list();
	}

	public Requisicao getRequisicaoByRequisitante(Requisitante requisitante) {
		Session sessao=sessionFactory.getCurrentSession();
		Criteria cr=sessao.createCriteria(Requisicao.class);
		cr.add(Restrictions.eq("requisitante", requisitante));
		return (Requisicao)cr.uniqueResult();
	}

}
