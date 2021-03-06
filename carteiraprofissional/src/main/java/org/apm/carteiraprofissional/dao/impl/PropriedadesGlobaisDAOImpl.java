package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.PropriedadesGlobais;
import org.apm.carteiraprofissional.dao.PropriedadesGlobaisDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PropriedadesGlobaisDAOImpl implements PropriedadesGlobaisDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void savePropriedade(PropriedadesGlobais propriedade) {
		sessionFactory.getCurrentSession().saveOrUpdate(propriedade);

	}

	@Transactional(readOnly = true)
	public PropriedadesGlobais getPropriedadeById(Integer id) {

		return (PropriedadesGlobais) sessionFactory.getCurrentSession().get(
				PropriedadesGlobais.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<PropriedadesGlobais> getAllPropriedades() {

		return sessionFactory.getCurrentSession()
				.createCriteria(PropriedadesGlobais.class).list();
	}

	public void delete(PropriedadesGlobais propriedade) {
		sessionFactory.getCurrentSession().delete(propriedade);

	}

	public PropriedadesGlobais getPropriedadeByName(String name) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(PropriedadesGlobais.class);
		cr.add(Restrictions.eq("propriedade", name));
		return (PropriedadesGlobais) cr.uniqueResult();
	}

}
