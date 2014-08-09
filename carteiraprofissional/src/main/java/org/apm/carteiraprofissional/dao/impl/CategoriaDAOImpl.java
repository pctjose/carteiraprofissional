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
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public void saveCategoria(Categoria profissao) {
		Session sessao = sessionFactory.getCurrentSession();		
		sessao.saveOrUpdate(profissao);		
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Categoria> getAllCategorias() {
		Session sessao = sessionFactory.getCurrentSession();		
		Criteria c = sessao.createCriteria(Categoria.class);
		c.addOrder(Order.asc("designacao"));
		List<Categoria> profissoes = c.list();		
		return profissoes;
	}

	@Transactional(readOnly=true)
	public Categoria getByID(Integer id) {
		Session sessao = sessionFactory.getCurrentSession();		
		Categoria prof = (Categoria) sessao.get(Categoria.class, id);		
		return prof;
	}
	
	@Transactional(readOnly=true)
	public Categoria getByUUID(String uuid) {
		Session sessao = sessionFactory.getCurrentSession();
		
		Criteria cr = sessao.createCriteria(Categoria.class);
		cr.add(Restrictions.eq("uuid", uuid));
		Categoria prof = (Categoria) cr.uniqueResult();
		
		return prof;
	}

	@Transactional(readOnly=true)
	public Categoria getByDesignacao(String designacao) {
		Session sessao = sessionFactory.getCurrentSession();		
		Criteria cr = sessao.createCriteria(Categoria.class);
		cr.add(Restrictions.eq("designacao", designacao));
		Categoria prof = (Categoria) cr.uniqueResult();
		
		return prof;

	}

	@Transactional
	public void deleCategoria(Categoria categoria) {
		sessionFactory.getCurrentSession().delete(categoria);

	}
}
