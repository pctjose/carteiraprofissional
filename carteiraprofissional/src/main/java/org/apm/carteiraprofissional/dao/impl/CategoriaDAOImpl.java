package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.Categoria;
import org.apm.carteiraprofissional.dao.CategoriaDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaDAOImpl implements CategoriaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveCategoria(Categoria profissao) {
		Session sessao = sessionFactory.getCurrentSession();
		// Transaction tx = sessao.beginTransaction();
		sessao.saveOrUpdate(profissao);
		// tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> getAllCategorias() {
		Session sessao = sessionFactory.getCurrentSession();
		// Transaction tx = sessao.beginTransaction();
		Criteria c = sessao.createCriteria(Categoria.class);
		c.addOrder(Order.asc("designacao"));
		List<Categoria> profissoes = c.list();
		// tx.commit();
		return profissoes;
	}

	public Categoria getByID(Integer id) {
		Session sessao = sessionFactory.getCurrentSession();
		// Transaction tx = sessao.beginTransaction();
		Categoria prof = (Categoria) sessao.get(Categoria.class, id);
		// tx.commit();
		return prof;
	}

	public Categoria getByUUID(String uuid) {
		Session sessao = sessionFactory.getCurrentSession();
		// Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(Categoria.class);
		cr.add(Restrictions.eq("uuid", uuid));
		Categoria prof = (Categoria) cr.uniqueResult();
		// tx.commit();
		return prof;
	}

	public Categoria getByDesignacao(String designacao) {
		Session sessao = sessionFactory.getCurrentSession();
		// Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(Categoria.class);
		cr.add(Restrictions.eq("designacao", designacao));
		Categoria prof = (Categoria) cr.uniqueResult();
		// tx.commit();
		return prof;

	}

	public void deleCategoria(Categoria categoria) {
		sessionFactory.getCurrentSession().delete(categoria);

	}
}
