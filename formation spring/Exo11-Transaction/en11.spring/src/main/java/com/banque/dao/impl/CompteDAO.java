package com.banque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.banque.dao.ICompteDAO;
import com.banque.dao.mapper.CompteJdbcMapper;
import com.banque.entity.ICompteEntity;

/**
 * Gestion des comptes.
 */
@Repository
public class CompteDAO extends AbstractDAO<ICompteEntity> implements ICompteDAO {
	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger(CompteDAO.class);

	/**
	 * Constructeur de l'objet.
	 */
	public CompteDAO() {
		super();
	}

	@Override
	protected String getTableName() {
		return "compte";
	}

	@Override
	protected String getAllColumnNames() {
		return "id,libelle,solde,decouvert,taux,utilisateurId";
	}

	@Override
	protected RowMapper<ICompteEntity> getMapper() {
		return new CompteJdbcMapper();
	}

	@Override
	protected PreparedStatement buildStatementForInsert(ICompteEntity pUneEntite, Connection connexion)
			throws SQLException {
		PreparedStatement ps = connexion.prepareStatement(
				"insert into " + this.getTableName()
						+ " (libelle, solde, decouvert, taux, utilisateurId) values (?,?,?,?,?);",
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, pUneEntite.getLibelle());
		ps.setDouble(2, pUneEntite.getSolde().doubleValue());
		if (pUneEntite.getDecouvert() != null) {
			ps.setDouble(3, pUneEntite.getDecouvert().doubleValue());
		} else {
			ps.setNull(3, Types.DOUBLE);
		}
		if (pUneEntite.getTaux() != null) {
			ps.setDouble(4, pUneEntite.getTaux().doubleValue());
		} else {
			ps.setNull(4, Types.DOUBLE);
		}
		ps.setInt(5, pUneEntite.getUtilisateurId().intValue());
		return ps;
	}

	@Override
	protected PreparedStatement buildStatementForUpdate(ICompteEntity pUneEntite, Connection connexion)
			throws SQLException {
		PreparedStatement ps = connexion.prepareStatement("update " + this.getTableName()
				+ " set libelle=?, solde=?, decouvert=?, taux=?, utilisateurId=? where " + this.getPkName() + "=?;");
		ps.setString(1, pUneEntite.getLibelle());
		ps.setDouble(2, pUneEntite.getSolde().doubleValue());
		if (pUneEntite.getDecouvert() != null) {
			ps.setDouble(3, pUneEntite.getDecouvert().doubleValue());
		} else {
			ps.setNull(3, Types.DOUBLE);
		}
		if (pUneEntite.getTaux() != null) {
			ps.setDouble(4, pUneEntite.getTaux().doubleValue());
		} else {
			ps.setNull(4, Types.DOUBLE);
		}
		ps.setInt(5, pUneEntite.getUtilisateurId().intValue());
		ps.setInt(6, pUneEntite.getId().intValue());
		return ps;
	}

}