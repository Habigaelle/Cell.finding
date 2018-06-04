package com.banque.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.banque.dao.IDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.impl.AbstractEntity;

/**
 * Un exemple de DAO generique. <br/>
 *
 * @param <T>
 *            Le type d'entite
 */
public abstract class AbstractDAO<T extends AbstractEntity> implements IDAO<T> {
	private static final Logger LOG = LogManager.getLogger(AbstractDAO.class);

	private EntityManager entityManager;

	/**
	 * Constructeur de l'objet.
	 */
	protected AbstractDAO() {
		super();
	}

	/**
	 * Donne la classe des entites gerees
	 *
	 * @return la classe des entites gerees
	 */
	protected abstract Class<T> getEntityClass();

	@Override
	public T insert(T uneEntite) throws ExceptionDao {
		AbstractDAO.LOG.trace("--> Insert pour " + uneEntite);
		if (uneEntite == null) {
			return null;
		}
		try {
			this.entityManager.persist(uneEntite);
		} catch (Exception e) {
			AbstractDAO.LOG.error("Erreur lors de l'insert de " + uneEntite, e);
			throw new ExceptionDao("Probleme d'insert pour l'objet " + uneEntite.getClass().getName(), e);
		}
		AbstractDAO.LOG.trace("<-- Insert pour " + uneEntite);
		return uneEntite;
	}

	@Override
	public T update(T uneEntite) throws ExceptionDao {
		AbstractDAO.LOG.trace("--> Update pour " + uneEntite);
		if (uneEntite == null) {
			return null;
		}
		try {
			this.entityManager.merge(uneEntite);
		} catch (Exception e) {
			AbstractDAO.LOG.error("Erreur lors de l'update de " + uneEntite, e);
			throw new ExceptionDao("Probleme d'update pour l'objet " + uneEntite.getClass().getName(), e);
		}
		AbstractDAO.LOG.trace("<-- Update pour " + uneEntite);
		return uneEntite;
	}

	@Override
	public boolean delete(T uneEntite) throws ExceptionDao {
		AbstractDAO.LOG.trace("--> Delete pour " + uneEntite);
		if (uneEntite == null) {
			throw new ExceptionDao("L'entite est null!");
		}

		try {
			this.entityManager.remove(uneEntite);
		} catch (Exception e) {
			AbstractDAO.LOG.error("Erreur lors du delete de " + uneEntite, e);
			throw new ExceptionDao("Probleme de delete pour l'objet " + uneEntite.getClass().getName(), e);
		}
		AbstractDAO.LOG.trace("<-- Delete pour " + uneEntite);
		return true;
	}

	@Override
	public T select(int unId) throws ExceptionDao {
		AbstractDAO.LOG.trace("--> Select pour " + String.valueOf(unId));
		T resultat = null;
		try {
			resultat = this.entityManager.find(this.getEntityClass(), Integer.valueOf(unId));
		} catch (Exception e) {
			AbstractDAO.LOG.error("Erreur lors du select sur " + String.valueOf(unId), e);
			throw new ExceptionDao("Probleme de recuperation pour l'objet " + this.getEntityClass().getName(), e);
		}
		AbstractDAO.LOG.trace("<-- Select pour " + String.valueOf(unId));
		return resultat;
	}

	@Override
	public List<T> selectAll(String pAWhere, String pAnOrderBy) throws ExceptionDao {
		List<T> result = new ArrayList<T>();
		AbstractDAO.LOG.debug(
				"select sur " + this.getClass().getName() + " avec where=" + pAWhere + " et orderBy=" + pAnOrderBy);
		try {
			StringBuilder request = new StringBuilder();
			request.append("SELECT OBJECT(entity) FROM ").append(this.getEntityClass().getName());
			request.append(" as entity");
			if (pAWhere != null) {
				request.append(" where ");
				request.append(pAWhere);
			}
			if (pAnOrderBy != null) {
				request.append(" order by ");
				request.append(pAnOrderBy);
			}
			AbstractDAO.LOG.debug("Requete JPQL: " + request.toString());

			TypedQuery<T> query = this.getEntityManager().createQuery(request.toString(), this.getEntityClass());
			result = query.getResultList();
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return result;
	}

	@Override
	public EntityTransaction getEntityTransaction() throws ExceptionDao {
		EntityTransaction resu = null;
		try {
			if (this.getEntityManager() != null && this.getEntityManager().isOpen()) {
				resu = this.getEntityManager().getTransaction();
			}
		} catch (Exception e) {
			AbstractDAO.LOG.error("Erreur lors de la recuperation d'une transaction", e);
			throw new ExceptionDao("Probleme pour obtenir une transaction", e);
		}
		return resu;
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete entityManager
	 */
	protected final EntityManager getEntityManager() {
		if (this.entityManager == null) {
			AbstractDAO.LOG.error("Vous avez oublier le lien avec entityManager",
					new NullPointerException("entityManager est null ..."));
		}
		return this.entityManager;
	}

	@Override
	public final void setEntityManager(EntityManager pEntityManager) {
		this.entityManager = pEntityManager;
	}
}