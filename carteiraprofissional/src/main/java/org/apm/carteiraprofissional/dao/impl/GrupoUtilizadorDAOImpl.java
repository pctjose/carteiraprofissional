package org.apm.carteiraprofissional.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apm.carteiraprofissional.GrupoUtilizador;
import org.apm.carteiraprofissional.dao.GrupoUtilizadorDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GrupoUtilizadorDAOImpl implements GrupoUtilizadorDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveGrupo(GrupoUtilizador grupo) {
		sessionFactory.getCurrentSession().saveOrUpdate(grupo);

	}

	@Transactional(readOnly = true)
	public GrupoUtilizador getById(Integer id) {
		Session sessao = sessionFactory.getCurrentSession();

		GrupoUtilizador grupo = (GrupoUtilizador) sessao.get(
				GrupoUtilizador.class, id);

		return grupo;
	}

	@Transactional(readOnly = true)
	public GrupoUtilizador getByDesignacao(String designacao) {
		Session sessao = sessionFactory.getCurrentSession();
		Transaction tx = sessao.beginTransaction();
		Criteria cr = sessao.createCriteria(GrupoUtilizador.class);
		cr.add(Restrictions.eq("designacao", designacao));
		GrupoUtilizador grupo = (GrupoUtilizador) cr.uniqueResult();
		tx.commit();
		return grupo;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<GrupoUtilizador> getAllGrupos() {
		Session sessao = sessionFactory.getCurrentSession();

		Criteria c = sessao.createCriteria(GrupoUtilizador.class);

		List<GrupoUtilizador> grupos = c.list();
		
		return grupos;
	}
	
	public void inserirGrupo(){
		GrupoUtilizador admin = new GrupoUtilizador();
		GrupoUtilizador supervisor = new GrupoUtilizador();
		GrupoUtilizador grafico = new GrupoUtilizador();
		
		List<GrupoUtilizador> lista = new ArrayList<GrupoUtilizador>();
		
		//if(getAllGrupos().isEmpty()){
			admin.setDesignacao("ROLE_ADMIN");
			admin.setNome("Administrador");
			admin.setUuid(UUID.randomUUID().toString());
			
			supervisor.setDesignacao("ROLE_SUPERVISOR");
			supervisor.setNome("Supervisor");
			supervisor.setUuid(UUID.randomUUID().toString());
			
			grafico.setDesignacao("ROLE_GRAFICO");
			grafico.setNome("Grafico");
			grafico.setUuid(UUID.randomUUID().toString());
			
			lista.add(admin);
			lista.add(supervisor);
			lista.add(grafico);
			
			for (GrupoUtilizador grupoUtilizador : lista) {
				sessionFactory.getCurrentSession().save(grupoUtilizador);
			}
		//}
		
	}

}
