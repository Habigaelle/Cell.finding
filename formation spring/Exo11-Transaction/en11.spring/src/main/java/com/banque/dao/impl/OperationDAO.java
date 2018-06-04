package com.banque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.banque.dao.IOperationDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.dao.mapper.OperationJdbcMapper;
import com.banque.entity.IOperationEntity;

/**
 * Gestion des operations.
 */
@Repository
public class OperationDAO extends AbstractDAO<IOperationEntity> implements IOperationDAO {
	private static final Logger LOG = LogManager.getLogger(OperationDAO.class);

	/**
	 * Constructeur de l'objet.
	 */
	public OperationDAO() {
		super();
	}

	@Override
	protected String getTableName() {
		return "operation";
	}

	@Override
	protected RowMapper<IOperationEntity> getMapper() {
		return new OperationJdbcMapper();
	}

	@Override
	protected String getAllColumnNames() {
		return "id,libelle,montant,date,compteId";
	}

	@Override
	protected PreparedStatement buildStatementForInsert(IOperationEntity pUneEntite, Connection connexion)
			throws SQLException {

		PreparedStatement ps = connexion.prepareStatement(
				"insert into " + this.getTableName() + " (libelle, montant, date, compteId) values (?,?,?,?);",
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, pUneEntite.getLibelle());
		ps.setDouble(2, pUneEntite.getMontant().doubleValue());
		ps.setTimestamp(3, pUneEntite.getDate());
		ps.setInt(4, pUneEntite.getCompteId().intValue());
		return ps;
	}

	@Override
	protected PreparedStatement buildStatementForUpdate(IOperationEntity pUneEntite, Connection connexion)
			throws SQLException {
		PreparedStatement ps = connexion.prepareStatement("update " + this.getTableName()
				+ " set libelle=?, montant=?, date=?, compteId=? where " + this.getPkName() + "=?;");
		ps.setString(1, pUneEntite.getLibelle());
		ps.setDouble(2, pUneEntite.getMontant().doubleValue());
		ps.setTimestamp(3, pUneEntite.getDate());
		ps.setInt(4, pUneEntite.getCompteId().intValue());
		ps.setInt(5, pUneEntite.getId().intValue());
		return ps;
	}

	@Override
	public List<IOperationEntity> selectCriteria(int unCompteId, Date unDebut, Date uneFin, Boolean pCreditDebit)
			throws ExceptionDao {
		List<IOperationEntity> result = new ArrayList<IOperationEntity>();
		OperationDAO.LOG.debug("selectCriteria sur " + this.getClass() + " unCompteId=" + String.valueOf(unCompteId)
				+ " unDebut=" + String.valueOf(unDebut) + " uneFin=" + String.valueOf(uneFin) + " pCreditDebit="
				+ String.valueOf(pCreditDebit));
		try {
			StringBuilder request = new StringBuilder();
			request.append("select ").append(this.getAllColumnNames()).append(" from ");
			request.append(this.getTableName());
			request.append(" where compteId=?");
			List<Object> gaps = new ArrayList<Object>();
			gaps.add(Integer.valueOf(unCompteId));
			if (unDebut != null && uneFin == null) {
				request.append(" and date >= ?");
				gaps.add(unDebut);
			}

			if (uneFin != null && unDebut == null) {
				// Probleme sur la date de fin car MySQL a des dates en
				// 2016-08-26 18:38:22
				// Mais nous on lui donne des date en 2016-08-26 00:00:00
				// Du coup, on doit gerer la date de fin en faisant +1 jour
				// Le < (et pas <=) evite d'avoir l'operation du lendemain a
				// 00:00:00
				request.append(" and date < DATE_ADD(?, INTERVAL 1 DAY)");
				gaps.add(uneFin);
			}

			if (uneFin != null && unDebut != null) {
				// Probleme sur la date de fin car MySQL a des dates en
				// 2016-08-26 18:38:22
				// Mais nous on lui donne des date en 2016-08-26 00:00:00
				// Du coup, on doit gerer la date de fin en faisant +1 jour
				// Le < (et pas <=) evite d'avoir l'operation du lendemain a
				// 00:00:00
				request.append(" and date >= ? and date < DATE_ADD(?, INTERVAL 1 DAY)");
				gaps.add(unDebut);
				gaps.add(uneFin);
			}

			if (pCreditDebit != null) {
				if (pCreditDebit.booleanValue()) {
					request.append(" and montant >= 0");
				} else {
					request.append(" and montant <= 0");
				}
			}
			request.append(" order by date DESC;");
			OperationDAO.LOG.debug("selectCriteria sur " + this.getClass() + " requete=" + request.toString());
			result = this.getJdbcTemplate().query(request.toString(), this.getMapper(),
					gaps.toArray(new Object[gaps.size()]));
		} catch (EmptyResultDataAccessException e) {
			OperationDAO.LOG.trace("Pas de resultat", e);
			return result;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return result;
	}

}