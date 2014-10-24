package org.apm.carteiraprofissional.dao.impl;

import java.util.Date;
import java.util.List;

import org.apm.carteiraprofissional.Carteira;
import org.apm.carteiraprofissional.Requisicao;
import org.apm.carteiraprofissional.Requisitante;
import org.apm.carteiraprofissional.dao.CarteiraDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CarteiraDAOImpl implements CarteiraDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveCarteira(Carteira carteira) {
		sessionFactory.getCurrentSession().saveOrUpdate(carteira);

	}

	@Transactional(readOnly = true)
	public Carteira getCarteiraByID(Integer id) {
		return (Carteira) sessionFactory.getCurrentSession().get(
				Carteira.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Carteira> getAllCarteira(Requisitante requisitante,
			Boolean emitida) {

		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);
		if (requisitante != null) {
			cr.add(Restrictions.eq("requisicao.requisitante", requisitante));
		}
		if (emitida != null) {
			cr.add(Restrictions.eq("emitida", emitida));
		}

		return cr.list();

	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Carteira> getAllCarteiraByDataEmissao(Date startDate,
			Date endDate) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);
		cr.add(Restrictions.between("dataEmissao", startDate, endDate));
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Carteira> getAllCarteiraByDataValidade(Date startDate,
			Date endDate) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);
		cr.add(Restrictions.between("dataValidade", startDate, endDate));
		return cr.list();
	}

	@Transactional(readOnly = true)
	public Carteira getCarteiraByUUID(String uuid) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);
		cr.add(Restrictions.eq("uuid", uuid));
		return (Carteira) cr.uniqueResult();
	}

	@Transactional(readOnly = true)
	public Carteira getCarteiraByRequisitante(Requisitante requisitante) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);
		cr.add(Restrictions.eq("requisicao.requisitante", requisitante));
		return (Carteira) cr.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Carteira> getAllByAttributes(String numeroCarteira,
			String nomeTitular,Date startDateEmissao,
			Date endDateEmissao, Date startDateValidade, Date endDateValidade,
			Boolean emitida) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);

		if (numeroCarteira != null && !numeroCarteira.trim().isEmpty()) {
			cr.add(Restrictions.eq("numeroCarteira", numeroCarteira));
		}

		cr.createAlias("requisicao", "req1");
		cr.createAlias("req1.requisitante", "req2");

		if (nomeTitular != null && !nomeTitular.trim().isEmpty()) {
			cr.add(Restrictions.like("req2.nome", nomeTitular,
					MatchMode.ANYWHERE));
		}		

		if (startDateEmissao != null && endDateEmissao != null) {
			cr.add(Restrictions.between("dataEmissao", startDateEmissao,
					endDateEmissao));
		}

		if (startDateValidade != null && endDateValidade != null) {
			cr.add(Restrictions.between("dataValidade", startDateValidade,
					endDateValidade));
		}
		if (emitida != null) {
			cr.add(Restrictions.eq("emitida", emitida));
		}
		return cr.list();
	}

	@Transactional(readOnly=true)
	public Carteira getCarteiraByRequisicao(Requisicao requisicao) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);
		cr.add(Restrictions.eq("requisicao", requisicao));
		return (Carteira) cr.uniqueResult();
	}

	@Transactional(readOnly=true)
	public Carteira getCarteiraByNumero(String numero) {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);
		cr.add(Restrictions.eq("numeroCarteira", numero));
		return (Carteira) cr.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Carteira> getAllParaGrafica() {
		Session sessao = sessionFactory.getCurrentSession();
		Criteria cr = sessao.createCriteria(Carteira.class);
		cr.add(Restrictions.eq("emitida", false));
		cr.add(Restrictions.eq("enviarEmissao", true));
		return cr.list();
	}

}
