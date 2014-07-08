package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.GrupoUtilizador;
import org.apm.carteiraprofissional.Pais;
import org.apm.carteiraprofissional.dao.PaisDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaisDAOImpl implements PaisDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

	public void savePais(Pais pais) {
		sessionFactory.getCurrentSession().saveOrUpdate(pais);

	}

	public Pais getByID(Integer id) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		Pais pais = (Pais) sessao.get(
				Pais.class, id);
		tx.commit();
		return pais;
	}

	public Pais getByDesignacao(String designacao) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(Pais.class);
		cr.add(Restrictions.eq("designacao", designacao));
		Pais pais = (Pais) cr.uniqueResult();
		tx.commit();
		return pais;
	}

	public List<Pais> getAllPaises() {
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();

		Criteria c = sessao.createCriteria(Pais.class);

		List<Pais> paises = c.list();
		//tx.commit();
		return paises;
	}

}
