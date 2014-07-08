package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.GrupoUtilizador;
import org.apm.carteiraprofissional.dao.GrupoUtilizadorDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GrupoUtilizadorDAOImpl implements GrupoUtilizadorDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveGrupo(GrupoUtilizador grupo) {
		sessionFactory.getCurrentSession().saveOrUpdate(grupo);

	}

	public GrupoUtilizador getById(Integer id) {
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();
		GrupoUtilizador grupo = (GrupoUtilizador) sessao.get(
				GrupoUtilizador.class, id);
		//tx.commit();
		return grupo;
	}

	public GrupoUtilizador getByDesignacao(String designacao) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(GrupoUtilizador.class);
		cr.add(Restrictions.eq("designacao", designacao));
		GrupoUtilizador grupo = (GrupoUtilizador) cr.uniqueResult();
		tx.commit();
		return grupo;
	}

	
	public List<GrupoUtilizador> getAllGrupos() {
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();

		Criteria c = sessao.createCriteria(GrupoUtilizador.class);

		List<GrupoUtilizador> grupos = c.list();
		//tx.commit();
		return grupos;
	}

}
