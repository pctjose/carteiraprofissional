package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.Pais;
import org.apm.carteiraprofissional.Provincia;
import org.apm.carteiraprofissional.dao.ProvinciaDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProvinciaDAOImpl implements ProvinciaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveProvincia(Provincia provincia) {
		sessionFactory.getCurrentSession().saveOrUpdate(provincia);

	}

	public Provincia getByID(Integer id) {
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();
		Provincia provincia = (Provincia) sessao.get(Provincia.class, id);
		//tx.commit();
		return provincia;
	}

	public Provincia getByDesignacao(String designacao) {
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(Provincia.class);
		cr.add(Restrictions.eq("designacao", designacao));
		Provincia provincia = (Provincia) cr.uniqueResult();
		//tx.commit();
		return provincia;
	}

	@SuppressWarnings("unchecked")
	public List<Provincia> getAllProvincia(Pais... paises) {
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();

		Criteria c = sessao.createCriteria(Provincia.class);
		if (paises.length > 0) {
			c.add(Restrictions.eq("pais", paises[0]));
		}

		List<Provincia> provincias = c.list();
		//tx.commit();
		return provincias;
	}

}
