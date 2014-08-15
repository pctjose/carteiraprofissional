package org.apm.carteiraprofissional.dao.impl;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apm.carteiraprofissional.GrupoUtilizador;
import org.apm.carteiraprofissional.Utilizador;
import org.apm.carteiraprofissional.dao.UtilizadorDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UtilizadorDAOImpl implements UtilizadorDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private static Logger log = Logger.getLogger(UtilizadorDAOImpl.class);

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void saveUtilizador(Utilizador utilizador) {
		String password = utilizador.getSenha();
		String senhaEncriptado = passwordEncoder.encodePassword(password, null);
		utilizador.setSenha(senhaEncriptado);
		
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();
		sessao.saveOrUpdate(utilizador);
		//tx.commit();

		/*
		 * Session sessao = sessionFactory.getCurrentSession();
		 * 
		 * 
		 * Transaction tx = sessao.beginTransaction();
		 * 
		 * try { sessao.saveOrUpdate(utilizador); tx.commit(); } catch
		 * (HibernateException e) { e.printStackTrace(); }finally{
		 * //sessao.close(); }
		 */

	}

	public Utilizador getUtilizadorByID(Integer id) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		Utilizador user = (Utilizador) sessao.get(Utilizador.class, id);
		tx.commit();
		return user;
	}

	public Utilizador getUtilizadorByUserNameAndPassword(String username,
			String senha) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(Utilizador.class);
		cr.add(Restrictions.eq("userName", username));
		cr.add(Restrictions.eq("senha", senha));
		Utilizador user = (Utilizador) cr.uniqueResult();
		tx.commit();
		return user;
	}

	public Utilizador getUtilizadorByUUID(String uuid) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(Utilizador.class);
		cr.add(Restrictions.eq("uuid", uuid));
		Utilizador user = (Utilizador) cr.uniqueResult();
		tx.commit();
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<Utilizador> getAllUtilizadores(Boolean includeVoided) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		Criteria c = sessao.createCriteria(Utilizador.class);
		c.addOrder(Order.asc("apelido"));
		if (!includeVoided) {
			c.add(Restrictions.eq("anulado", false));
		}
		List<Utilizador> users = c.list();
		tx.commit();
		return users;
	}

	@SuppressWarnings("unchecked")
	public List<Utilizador> ValidateLogin(String username, String password) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(Utilizador.class);
		cr.add(Restrictions.eq("userName", username));
		cr.add(Restrictions.eq("senha", password));
		List<Utilizador> users = cr.list();
		tx.commit();
		return users;
	}

	public Utilizador getUtilizador(String login) {
		Utilizador user = null;
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		try {
			Criteria cr = sessao.createCriteria(Utilizador.class);
			cr.add(Restrictions.eq("userName", login));
			user = (Utilizador) cr.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.info(e);
		}
		return user;
	}

	public String activarUtilizador(Integer id) {
		String hql = "update Utilizador set activo = :activo where id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("activo", "Y");
		query.setInteger("id", id);
		query.executeUpdate();
		return "";
	}

	public String disabilitarUtilizador(Integer id) {
		String hql = "update Utilizador set activo = :activo where id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger("activo", 0);
		query.setInteger("id", id);
		query.executeUpdate();
		return null;
	}

	public void updateUtilizador(Utilizador utilizador) {
		try {
			sessionFactory.getCurrentSession().update(utilizador);
		} catch (Exception e) {

		}

	}

	public void delete(Utilizador utilizador) {
		sessionFactory.getCurrentSession().delete(utilizador);

	}

	@SuppressWarnings("unchecked")
	public List<Utilizador> getUserByAttributes(String apelido, String nome,
			String sexo, GrupoUtilizador grupo, Boolean incluirAnulado) {

		Session sessao = sessionFactory.getCurrentSession();
		// Transaction tx = sessao.beginTransaction();
		Criteria c = sessao.createCriteria(Utilizador.class);

		if (apelido != null)
			c.add(Restrictions.like("apelido", apelido, MatchMode.ANYWHERE));

		if (nome != null)
			c.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));

		if (sexo != null)
			c.add(Restrictions.eq("sexo", sexo));

		if (grupo != null)
			c.add(Restrictions.eq("grupo", grupo));
		
		if(incluirAnulado!=null){
			if (!incluirAnulado) {
				c.add(Restrictions.eq("anulado", false));
			}
		}

		c.addOrder(Order.asc("apelido"));
		List<Utilizador> users = c.list();
		// tx.commit();
		return users;

	}

	@SuppressWarnings("unchecked")
	public List<Utilizador> getAllUtilizador() {
		Session sessao = sessionFactory.getCurrentSession();
		//Transaction tx = sessao.beginTransaction();
		Criteria c = sessao.createCriteria(Utilizador.class);
		List<Utilizador> users = c.list();
		//tx.commit();
		return users;
	}
	
	public void inserirUtilizador(){
		Utilizador utilizador = new Utilizador();
		GrupoUtilizador grupo = new GrupoUtilizador();
		
		if(getAllUtilizador().isEmpty()){
			utilizador.setActivo(true);
			utilizador.setApelido("Administrador");
			utilizador.setNome("Administrador");
			utilizador.setContacto("+258");
			utilizador.setEmail("email");
			grupo.setId(1);
			utilizador.setGrupo(grupo);
			utilizador.setUserName("admin");
			utilizador.setSenha("admin");
			utilizador.setUuid(UUID.randomUUID().toString());
			
			saveUtilizador(utilizador);
			//sessionFactory.getCurrentSession().save(utilizador);
		}
	}

}
