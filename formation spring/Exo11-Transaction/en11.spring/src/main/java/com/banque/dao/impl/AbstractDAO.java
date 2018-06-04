package com.banque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.banque.dao.IDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IEntity;

/**
 * DAO abstrait.
 *
 * @param <T>
 *            Un type d'entite
 */
@Repository
public abstract class AbstractDAO<T extends IEntity> implements IDAO<T> {

	private static final Logger LOG = LogManager.getLogger(AbstractDAO.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Constructeur de l'objet.
	 */
	protected AbstractDAO() {
		super();
	}

	/**
	 * Retourne le nom de la table.
	 *
	 * @return le nom de la table.
	 */
	protected abstract String getTableName();

	/**
	 * Retourne la clef primaire de la table.
	 *
	 * @return la clef primaire de la table.
	 */
	protected String getPkName() {
		return "id";
	}

	/**
	 * Retourne la liste des colonnes de la table
	 *
	 * @return la liste des colonnes de la table
	 */
	protected abstract String getAllColumnNames();

	/**
	 * Recupere le jdbc template
	 *
	 * @return le jdbc template
	 */
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	/**
	 * A la responsabilite de creer un statement qui servira pour l'insertion.
	 *
	 * @param pUneEntite
	 *            une entite a inserer
	 * @param connexion
	 *            une connexion
	 * @return un statement adapte a l'insertion
	 * @throws SQLException
	 *             si une erreur survient
	 */
	protected abstract PreparedStatement buildStatementForInsert(T pUneEntite, Connection connexion)
			throws SQLException;

	/**
	 * A la responsabilite de creer un statement qui servira pour la mise a
	 * jour.
	 *
	 * @param pUneEntite
	 *            une entite a mettre a jour
	 * @param connexion
	 *            une connexion
	 * @return un statement adapte a la mise a jour
	 * @throws SQLException
	 *             si une erreur survient
	 */
	protected abstract PreparedStatement buildStatementForUpdate(T pUneEntite, Connection connexion)
			throws SQLException;

	@Override
	public T insert(final T pUneEntite) throws ExceptionDao {
		if (pUneEntite == null) {
			return null;
		}
		AbstractDAO.LOG.debug("Insert " + pUneEntite.getClass());
		try {
			PreparedStatementCreator psc = new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connexion) throws SQLException {
					// Pour faire usage du parametre pUneEntite dans une inner
					// class on doit s'assurer qu'il est final
					// Qui fait appel a notre methode buildStatementForInsert

					return AbstractDAO.this.buildStatementForInsert(pUneEntite, connexion);
				}
			};
			KeyHolder kh = new GeneratedKeyHolder();
			this.getJdbcTemplate().update(psc, kh);
			pUneEntite.setId(Integer.valueOf(kh.getKey().intValue()));
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return pUneEntite;
	}

	@Override
	public T update(final T pUneEntite) throws ExceptionDao {
		if (pUneEntite == null) {
			return null;
		}
		AbstractDAO.LOG.debug("update " + pUneEntite.getClass());
		if (pUneEntite.getId() == null) {
			throw new ExceptionDao("L'entite n'a pas d'ID");
		}

		try {
			PreparedStatementCreator psc = new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connexion) throws SQLException {
					// Pour faire usage du parametre pUneEntite dans une inner
					// class on doit s'assurer qu'il est final
					// Qui fait appel a notre methode buildStatementForUpdate
					return AbstractDAO.this.buildStatementForUpdate(pUneEntite, connexion);
				}
			};
			if (this.getJdbcTemplate().update(psc) == 1) {
				return pUneEntite;
			}
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return null;
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
			return 1 == this.getJdbcTemplate().update(
					"delete from " + this.getTableName() + " where " + this.getPkName() + "=?;", pUneEntite.getId());
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
	}

	/**
	 * Donne un mapper.
	 *
	 * @return un mapper.
	 */
	protected abstract RowMapper<T> getMapper();

	@Override
	public T select(int pUneClef) throws ExceptionDao {
		T result = null;
		AbstractDAO.LOG.debug("select sur " + this.getClass() + " avec id=" + String.valueOf(pUneClef));
		try {
			result = this.getJdbcTemplate().queryForObject("select " + this.getAllColumnNames() + " from "
					+ this.getTableName() + " where " + this.getPkName() + "=?;", this.getMapper(),
					Integer.valueOf(pUneClef));
		} catch (EmptyResultDataAccessException e) {
			AbstractDAO.LOG.trace("Pas de resultat", e);
			return result;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return result;
	}

	@Override
	public List<T> selectAll(String pAWhere, String pAnOrderBy) throws ExceptionDao {
		List<T> result = new ArrayList<T>();
		AbstractDAO.LOG.debug("selectAll sur " + this.getClass() + "avec where=" + pAWhere + " prderBy=" + pAnOrderBy);
		try {

			StringBuilder request = new StringBuilder();
			request.append("select ").append(this.getAllColumnNames()).append(" from ");
			request.append(this.getTableName());
			if (pAWhere != null) {
				request.append(" where ");
				request.append(pAWhere);
			}
			if (pAnOrderBy != null) {
				request.append(" order by ");
				request.append(pAnOrderBy);
			}
			request.append(';');
			AbstractDAO.LOG.debug("selectAll sur " + this.getClass() + "- requete=" + request.toString());
			result = this.getJdbcTemplate().query(request.toString(), this.getMapper());
		} catch (EmptyResultDataAccessException e) {
			AbstractDAO.LOG.trace("Pas de resultat", e);
			return result;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return result;
	}
}