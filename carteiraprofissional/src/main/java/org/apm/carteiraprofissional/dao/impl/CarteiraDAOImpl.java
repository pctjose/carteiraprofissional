package org.apm.carteiraprofissional.dao.impl;

import java.util.Date;
import java.util.List;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.dao.CarteiraDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarteiraDAOImpl implements CarteiraDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveCarteira(Carteira carteira) {
		sessionFactory.getCurrentSession().saveOrUpdate(carteira);

	}

	public Carteira getCarteiraByID(Integer id) {
		return (Carteira) sessionFactory.getCurrentSession().get(
				Carteira.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Carteira> getAllCarteira(Requisitante requisitante,
			Boolean emitida) {

		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);
		if (requisitante != null) {
			cr.add(Restrictions.eq("requisicao.requisitante", requisitante));
		}
		if (emitida != null) {
			cr.add(Restrictions.eq("emitida", emitida));
		}

		return cr.list();

	}

	@SuppressWarnings("unchecked")
	public List<Carteira> getAllCarteiraByDataEmissao(Date startDate,
			Date endDate) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);
		cr.add(Restrictions.between("dataEmissao", startDate, endDate));
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	public List<Carteira> getAllCarteiraByDataValidade(Date startDate,
			Date endDate) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);
		cr.add(Restrictions.between("dataValidade", startDate, endDate));
		return cr.list();
	}

	public Carteira getCarteiraByUUID(String uuid) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);
		cr.add(Restrictions.eq("uuid", uuid));
		return (Carteira) cr.uniqueResult();
	}

	public Carteira getCarteiraByRequisitante(Requisitante requisitante) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);
		cr.add(Restrictions.eq("requisicao.requisitante", requisitante));
		return (Carteira) cr.uniqueResult();
	}

}
