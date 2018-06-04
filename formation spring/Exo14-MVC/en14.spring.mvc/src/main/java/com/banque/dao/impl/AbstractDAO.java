package com.banque.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.banque.dao.IDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IEntity;

/**
 * DAO standard.
 *
 * @param <T>
 *            la cible du DAO
 */
@Repository
abstract class AbstractDAO<T extends IEntity> implements IDAO<T> {

	private static final Logger LOG = LogManager.getLogger(AbstractDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Constructeur de l'objet.
	 */
	AbstractDAO() {
		super();
	}

	/**
	 * Donne la classe des entites gerees
	 *
	 * @return la classe des entites gerees
	 */
	protected abstract Class<T> getEntityClass();

	/**
	 * Donne le nom de la classe des entites gerees
	 *
	 * @return le nom de la classe des entites gerees
	 */
	protected String getEntityClassName() {
		return this.getEntityClass().getName();
	}

	/**
	 * Retourne le nom de la colonne qui represente la clef primaire de T.
	 *
	 * @return e nom de la colonne qui represente la clef primaire de T.
	 */
	public String getPkName() {
		return "id";
	}

	/**
	 * Recupere la session factory
	 *
	 * @return la session factory
	 */
	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	@Override
	public T insert(T pUneEntite) throws ExceptionDao {
		if (pUneEntite == null) {
			return null;
		}
		AbstractDAO.LOG.debug("insert " + pUneEntite.getClass());
		try {
			Session pSession = this.getSessionFactory().getCurrentSession();
			pSession.save(pUneEntite);
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return pUneEntite;
	}

	@Override
	public T update(T pUneEntite) throws ExceptionDao {
		if (pUneEntite == null) {
			return null;
		}
		AbstractDAO.LOG.debug("update " + pUneEntite.getClass());
		try {
			Session pSession = this.getSessionFactory().getCurrentSession();
			pSession.update(pUneEntite);
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return pUneEntite;
	}

	@Override
	public boolean delete(T pUneEntite) throws ExceptionDao {
		if (pUneEntite == null) {
			return false;
		}
		AbstractDAO.LOG.debug("delete " + pUneEntite.getClass());
		if (pUneEntite.getId() == null) {
			throw new ExceptionDao("L'entite n'a pas d'ID");
		}
		try {
			Session pSession = this.getSessionFactory().getCurrentSession();
			pSession.delete(pUneEntite);
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T select(int pUneClef) throws ExceptionDao {

		List<T> resu = null;
		AbstractDAO.LOG.debug("select sur " + this.getClass().getName() + " via sa pk=" + String.valueOf(pUneClef));
		try {
			Session pSession = this.getSessionFactory().getCurrentSession();
			DetachedCriteria detachedCriteria = DetachedCriteria.forEntityName(this.getEntityClassName());
			detachedCriteria.add(Restrictions.eq(this.getPkName(), Integer.valueOf(pUneClef)));
			Criteria executableCriteria = detachedCriteria.getExecutableCriteria(pSession);
			resu = executableCriteria.list();
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		if (resu != null && !resu.isEmpty()) {
			return resu.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> selectAll(String pAWhere, String pAnOrderBy) throws ExceptionDao {
		List<T> result = new ArrayList<T>();
		AbstractDAO.LOG.debug(
				"select sur " + this.getClass().getName() + " avec where=" + pAWhere + " et orderBy=" + pAnOrderBy);
		try {
			Session pSession = this.getSessionFactory().getCurrentSession();
			StringBuilder request = new StringBuilder();
			request.append("select entity from ").append(this.getEntityClassName());
			request.append(" as entity");
			if (pAWhere != null) {
				request.append(" where ");
				request.append(pAWhere);
			}
			if (pAnOrderBy != null) {
				request.append(" order by ");
				request.append(pAnOrderBy);
			}
			AbstractDAO.LOG.debug("Requete OQL: " + request.toString());

			Query queryObject = pSession.createQuery(request.toString());
			result.addAll(queryObject.list());
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return result;
	}
}
