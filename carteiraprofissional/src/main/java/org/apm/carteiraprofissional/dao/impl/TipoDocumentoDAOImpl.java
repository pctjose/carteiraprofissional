package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.TipoDocumento;
import org.apm.carteiraprofissional.dao.TipoDocumentoDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TipoDocumentoDAOImpl implements TipoDocumentoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveTipoDocumento(TipoDocumento doc) {
		sessionFactory.getCurrentSession().saveOrUpdate(doc);

	}

	public void deleteTipoDocumento(TipoDocumento doc) {
		sessionFactory.getCurrentSession().delete(doc);

	}

	@SuppressWarnings("unchecked")
	public List<TipoDocumento> getAllTipoDocumento() {

		return sessionFactory.getCurrentSession()
				.createCriteria(TipoDocumento.class).list();
	}

	public TipoDocumento getByID(Integer id) {

		return (TipoDocumento) sessionFactory.getCurrentSession().get(
				TipoDocumento.class, id);
	}

}
