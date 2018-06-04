package com.banque.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.banque.dao.IDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IEntity;
import com.banque.util.HibernateSessionFactory;

/**
 * DAO standard.
 *
 * @param <T>
 *            la cible du DAO
 */
public abstract class AbstractDAO<T extends IEntity> implements IDAO<T> {

	private static final Logger LOG = LogManager.getLogger(AbstractDAO.class);

	/**
	 * Constructeur de l'objet.
	 */
	protected AbstractDAO() {
		super();
	}

	/**
	 * Retourne le nom de la colonne qui represente la clef primaire de T.
	 *
	 * @return e nom de la colonne qui represente la clef primaire de T.
	 */
	protected String getPkName() {
		return "id";
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
	 * Gere la fin de la transaction Hibernate.
	 *
	 * @param pSessionCreated
	 *            indique si la session vient d'etre creee
	 * @param pDoCommit
	 *            indique si il faut faire un commit
	 * @param pSession
	 *            l'objet session hibernate
	 */
	public static void handleTransaction(boolean pSessionCreated, boolean pDoCommit, Session pSession) {
		if (pSessionCreated && pSession != null) {
			if (pDoCommit) {
				pSession.getTransaction().commit();
			} else {
				pSession.getTransaction().rollback();
			}
			try {
				pSession.close();
			} catch (HibernateException e) {
				AbstractDAO.LOG.error("Impossible de fermer la pSession!", e);
			}
		}
	}

	@Override
	public T insert(T pUneEntite, Session pSession) throws ExceptionDao {
		if (pUneEntite == null) {
			return null;
		}
		AbstractDAO.LOG.debug("insert " + pUneEntite.getClass());
		boolean doCommit = false;
		boolean pSessionCreated = pSession == null;
		try {
			if (pSessionCreated) {
				pSession = HibernateSessionFactory.getInstance().openSession();
				pSession.beginTransaction();
			}
			pSession.save(pUneEntite);
			doCommit = true;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(pSessionCreated, doCommit, pSession);
		}

		return pUneEntite;
	}

	@Override
	public T update(T pUneEntite, Session pSession) throws ExceptionDao {
		if (pUneEntite == null) {
			return null;
		}
		AbstractDAO.LOG.debug("update " + pUneEntite.getClass());
		boolean doCommit = false;
		boolean pSessionCreated = pSession == null;
		try {
			if (pSessionCreated) {
				pSession = HibernateSessionFactory.getInstance().openSession();
				pSession.beginTransaction();
			}
			pSession.update(pUneEntite);
			doCommit = true;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(pSessionCreated, doCommit, pSession);
		}

		return pUneEntite;
	}

	@Override
	public boolean delete(T pUneEntite, Session pSession) throws ExceptionDao {
		if (pUneEntite == null) {
			return false;
		}
		AbstractDAO.LOG.debug("delete " + pUneEntite.getClass());
		if (pUneEntite.getId() == null) {
			throw new ExceptionDao("L'entite n'a pas d'ID");
		}
		boolean doCommit = false;
		boolean pSessionCreated = pSession == null;
		try {
			if (pSessionCreated) {
				pSession = HibernateSessionFactory.getInstance().openSession();
				pSession.beginTransaction();
			}
			pSession.delete(pUneEntite);
			doCommit = true;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(pSessionCreated, doCommit, pSession);
		}

		return doCommit;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T select(int pUneClef, Session pSession) throws ExceptionDao {

		List<T> resu = null;
		boolean pSessionCreated = pSession == null;
		boolean doCommit = false;
		AbstractDAO.LOG.debug("select sur " + this.getClass().getName() + " avec pk=" + Integer.valueOf(pUneClef));
		try {
			if (pSessionCreated) {
				pSession = HibernateSessionFactory.getInstance().openSession();
				pSession.beginTransaction();
			}
			DetachedCriteria detachedCriteria = DetachedCriteria.forEntityName(this.getEntityClassName());
			detachedCriteria.add(Restrictions.eq(this.getPkName(), Integer.valueOf(pUneClef)));
			Criteria executableCriteria = detachedCriteria.getExecutableCriteria(pSession);
			resu = executableCriteria.list();
			doCommit = true;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(pSessionCreated, doCommit, pSession);
		}
		if (resu != null && !resu.isEmpty()) {
			return resu.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> selectAll(String pAWhere, String pAnOrderBy, Session pSession) throws ExceptionDao {
		List<T> result = new ArrayList<T>();
		boolean pSessionCreated = pSession == null;
		boolean doCommit = false;
		AbstractDAO.LOG.debug(
				"select sur " + this.getClass().getName() + " avec where=" + pAWhere + " et orderBy=" + pAnOrderBy);
		try {
			if (pSessionCreated) {
				pSession = HibernateSessionFactory.getInstance().openSession();
				pSession.beginTransaction();
			}
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
			doCommit = true;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(pSessionCreated, doCommit, pSession);
		}
		return result;
	}
}
