package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.Pais;
import org.apm.carteiraprofissional.dao.PaisDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	
	@Transactional
	public void savePais(Pais pais) {
		sessionFactory.getCurrentSession().saveOrUpdate(pais);

	}

	@Transactional(readOnly=true)
	public Pais getByID(Integer id) {
		Session sessao = sessionFactory.getCurrentSession();
		
		Pais pais = (Pais) sessao.get(
				Pais.class, id);
		
		return pais;
	}

	@Transactional(readOnly=true)
	public Pais getByDesignacao(String designacao) {
		Session sessao = sessionFactory.getCurrentSession();
		
		Criteria cr = sessao.createCriteria(Pais.class);
		cr.add(Restrictions.eq("designacao", designacao));
		Pais pais = (Pais) cr.uniqueResult();
		
		return pais;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Pais> getAllPaises() {
		Session sessao = sessionFactory.getCurrentSession();
		

		Criteria c = sessao.createCriteria(Pais.class);

		
		List<Pais> paises = c.list();
		
		return paises;
	}

}
