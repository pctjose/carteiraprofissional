package org.apm.carteiraprofissional.dao.impl;

import java.util.List;

import org.apm.carteiraprofissional.CartaoProfissional;
import org.apm.carteiraprofissional.Empregador;
import org.apm.carteiraprofissional.Escolaridade;
import org.apm.carteiraprofissional.Membro;
import org.apm.carteiraprofissional.SubCategoria;
import org.apm.carteiraprofissional.dao.MembroDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class MembroDAOImpl implements MembroDAO {

	 private SessionFactory sessionFactory;

	    public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    
	    public void saveMembro(Membro membro) {
	        Session sessao = sessionFactory.getCurrentSession();
	        //Transaction tx = sessao.beginTransaction();
	        sessao.saveOrUpdate(membro);
	        //tx.commit();
	    }

	    
	    public Membro getMembroByID(Integer id) {
	        Session sessao = sessionFactory.getCurrentSession();
	        //Transaction tx = sessao.beginTransaction();
	        Membro membro = (Membro) sessao.get(Membro.class, id);
	        //tx.commit();
	        return membro;
	    }

	    
	    public Membro getMembroByUUID(String uuid) {
	        Session sessao = sessionFactory.getCurrentSession();
	        //Transaction tx = sessao.beginTransaction();
	        Criteria cr = sessao.createCriteria(Membro.class);
	        cr.add(Restrictions.eq("uuid", uuid));
	        Membro membro = (Membro) cr.uniqueResult();
	        //tx.commit();
	        return membro;
	    }

	    
	    public Membro getMembroByBI(String bi) {
	        Session sessao = sessionFactory.getCurrentSession();
	        //Transaction tx = sessao.beginTransaction();
	        Criteria cr = sessao.createCriteria(Membro.class);
	        cr.add(Restrictions.eq("numeroBi", bi));
	        Membro membro = (Membro) cr.uniqueResult();
	        //tx.commit();
	        return membro;
	    }

	    
	    public Membro getMembroByInss(String inss) {
	        Session sessao = sessionFactory.getCurrentSession();
	        //Transaction tx = sessao.beginTransaction();
	        Criteria cr = sessao.createCriteria(Membro.class);
	        cr.add(Restrictions.eq("numeroInss", inss));
	        Membro membro = (Membro) cr.uniqueResult();
	        //tx.commit();
	        return membro;
	    }

	    
	    public Membro getMembroByNumeroCartao(String numeroCartao) {
	        Session sessao = sessionFactory.getCurrentSession();
	        //Transaction tx = sessao.beginTransaction();
	        Criteria cr = sessao.createCriteria(Membro.class);
	        cr.add(Restrictions.eq("cartao.numeroCartao", numeroCartao));
	        Membro membro = (Membro) cr.uniqueResult();
	        //tx.commit();
	        return membro;
	    }

	    
	    public Membro getMembroByCartao(CartaoProfissional cartao) {
	        Session sessao = sessionFactory.getCurrentSession();
	        //Transaction tx = sessao.beginTransaction();
	        Criteria cr = sessao.createCriteria(Membro.class);
	        cr.add(Restrictions.eq("cartao", cartao));
	        Membro membro = (Membro) cr.uniqueResult();
	        //tx.commit();
	        return membro;
	    }

	    
	    @SuppressWarnings("unchecked")
		public List<Membro> getAllMembros() {
	        Session sessao = sessionFactory.getCurrentSession();
	        //Transaction tx = sessao.beginTransaction();
	        Criteria c = sessao.createCriteria(Membro.class);
	        c.addOrder(Order.asc("nome"));
	        List<Membro> membros = c.list();
	        //tx.commit();
	        return membros;
	    }

	    
	    @SuppressWarnings("unchecked")
		public List<Membro> getAllByAttributes(SubCategoria profissao, Escolaridade escolaridade, String nome, Empregador empregador) {
	        Session sessao = sessionFactory.getCurrentSession();
	        //Transaction tx = sessao.beginTransaction();
	        Criteria c = sessao.createCriteria(Membro.class);
	        c.addOrder(Order.asc("nome"));
	        if (profissao != null) {
	            c.add(Restrictions.eq("profissao", profissao));
	        }

	        if (escolaridade != null) {
	            c.add(Restrictions.eq("escolaridade", escolaridade));
	        }

	        if (empregador != null) {
	            c.add(Restrictions.eq("cartao.empregador", empregador));
	        }

	        if (nome != null && !nome.isEmpty()) {
	            c.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
	            c.add(Restrictions.like("apelido", nome, MatchMode.ANYWHERE));
	        }

	        List<Membro> membros = c.list();
	        //tx.commit();
	        return membros;
	    }

}
