package org.apm.carteiraprofissional.dao.impl;

import java.util.Date;
import java.util.List;

import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.dao.RequisicaoDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public void saveRequisicao(Requisicao requisicao) {		
		
		sessionFactory.getCurrentSession().saveOrUpdate(requisicao);

	}

	@Transactional
	public void deleteRequisicao(Requisicao requisicao) {
		sessionFactory.getCurrentSession().delete(requisicao);

	}

	@Transactional(readOnly=true)
	public Requisicao getRequisicaoByID(Integer id) {
		
		return (Requisicao) sessionFactory.getCurrentSession().get(
				Requisicao.class, id);
	}

	@Transactional(readOnly=true)
	public Requisicao getRequisicaoByUUID(String uuid) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Requisicao.class);
		cr.add(Restrictions.eq("uuid", uuid));
		return (Requisicao) cr.uniqueResult();
	}

	@Transactional(readOnly=true)
	public Requisicao getRequisicaoByNumero(String numeroRequisicao) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Requisicao.class);
		cr.add(Restrictions.eq("numeroRequisicao", numeroRequisicao));
		return (Requisicao) cr.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Requisicao> getAllRequisicoes() {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Requisicao.class);
		return (List<Requisicao>) cr.list();
	}

	@Transactional(readOnly=true)
	public Requisicao getRequisicaoByRequisitante(Requisitante requisitante) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Requisicao.class);
		cr.add(Restrictions.eq("requisitante", requisitante));
		return (Requisicao) cr.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Requisicao> getRequisicaoByAttributes(String numeroRequisicao,
			String nome, Date dataInicial, Date dataFinal,
			Boolean aceite, Boolean completa) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Requisicao.class);

		if (numeroRequisicao != null && !numeroRequisicao.trim().isEmpty()) {
			cr.add(Restrictions.eq("numeroRequisicao", numeroRequisicao));
		}
		
		cr.createAlias("requisitante", "req");
		
		if (nome != null && !nome.trim().isEmpty()) {
			cr.add(Restrictions.like("req.nome", nome,
					MatchMode.ANYWHERE));
		}
		

		if (dataInicial != null && dataFinal != null) {
			cr.add(Restrictions
					.between("dataRequisiao", dataInicial, dataFinal));
		}
		if (aceite != null) {
			cr.add(Restrictions.eq("aceite", aceite));
		}
		if (completa != null) {
			cr.add(Restrictions.eq("completa", completa));
		}

		return cr.list();
	}

}
