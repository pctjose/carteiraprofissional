/**
 * 
 */
package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.Role;
import org.apm.carteiraprofissional.dao.RoleDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * @author Ezequiel Barreto
 *
 */
public class RoleDAOImpl implements RoleDAO{
	
	private SessionFactory sessionFactory;
	private static Logger log = Logger.getLogger(RoleDAOImpl.class);

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	
	public void saveRole(Role role) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		try {
			sessao.saveOrUpdate(role);
			tx.commit();
		} catch (Exception e) {
			log.info(e.getMessage());
			tx.rollback();
		}
		
	}

	
	public Role getRole(Integer id) {
		/*
		 Session sessao = sessionFactory.getCurrentSession();
	     Role role = null;

		try {
		    sessao.beginTransaction();
		    role = (Role) sessao.get(Role.class, id);
		    sessao.getTransaction().commit();
		} catch (HibernateException e) {
		    e.printStackTrace();
		    sessao.getTransaction().rollback();
		} finally {
		    sessao.close();
		}
		*/
		Role role = (Role) sessionFactory.getCurrentSession().load(Role.class, id);
		return role;
	}

	@SuppressWarnings("unchecked")
	
	public List<Role> getAllRole() {
		Session sessao = sessionFactory.getCurrentSession();
		//Criteria criteria = null;
		List<Role> list = null;
		Transaction tx = sessao.beginTransaction();
		try{
			//criteria = sessao.createCriteria(Role.class);
			Query query = sessao.createQuery("from Role");
			list = query.list();

			tx.commit();
		}catch(Exception e){
			log.error(e.getMessage());
			tx.rollback();
		}
		log.info(list);
		return list;
	}

	
	public Role getByID(Integer id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Role.class);
		criteria.add(Restrictions.eq("id", id));
		return (Role) criteria.uniqueResult();
	}

	
	public Role getByUUID(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Role getByDesignacao(String designacao) {
		// TODO Auto-generated method stub
		return null;
	}


}
