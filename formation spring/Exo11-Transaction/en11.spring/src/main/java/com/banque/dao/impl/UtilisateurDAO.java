package com.banque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.dao.mapper.UtilisateurJdbcMapper;
import com.banque.entity.ESex;
import com.banque.entity.IUtilisateurEntity;

/**
 * Gestion des utilisateurs.
 */
@Repository
public class UtilisateurDAO extends AbstractDAO<IUtilisateurEntity> implements IUtilisateurDAO {
	private static final Logger LOG = LogManager.getLogger(UtilisateurDAO.class);

	/**
	 * Constructeur de l'objet.
	 */
	public UtilisateurDAO() {
		super();
	}

	@Override
	protected String getTableName() {
		return "utilisateur";
	}

	@Override
	protected String getAllColumnNames() {
		return "id,nom,prenom,login,password,sex,derniereConnection,dateDeNaissance,adresse,codePostal,telephone";
	}

	@Override
	protected RowMapper<IUtilisateurEntity> getMapper() {
		return new UtilisateurJdbcMapper();
	}

	@Override
	protected PreparedStatement buildStatementForInsert(IUtilisateurEntity pUneEntite, Connection connexion)
			throws SQLException {
		PreparedStatement ps = connexion.prepareStatement(
				"insert into " + this.getTableName()
						+ " (nom,prenom,login,password,sex,derniereConnection,dateDeNaissance,adresse,codePostal,telephone) values (?,?,?,?,?,?,?,?,?,?);",
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, pUneEntite.getNom());
		ps.setString(2, pUneEntite.getPrenom());
		ps.setString(3, pUneEntite.getLogin());
		ps.setString(4, pUneEntite.getPassword());
		if (pUneEntite.getSex().equals(ESex.HOMME)) {
			ps.setByte(5, (byte) 0);
		} else if (pUneEntite.getSex().equals(ESex.FEMME)) {
			ps.setByte(5, (byte) 1);
		} else {
			ps.setByte(5, (byte) 2);
		}
		ps.setTimestamp(6, pUneEntite.getDerniereConnection());
		ps.setDate(7, pUneEntite.getDateDeNaissance());
		ps.setString(8, pUneEntite.getAdresse());
		if (pUneEntite.getCodePostal() != null) {
			ps.setInt(9, pUneEntite.getCodePostal().intValue());
		} else {
			ps.setNull(9, Types.INTEGER);
		}
		ps.setString(10, pUneEntite.getTelephone());
		return ps;
	}

	@Override
	@SuppressWarnings("squid:S2068")
	protected PreparedStatement buildStatementForUpdate(IUtilisateurEntity pUneEntite, Connection connexion)
			throws SQLException {
		PreparedStatement ps = connexion.prepareStatement("update " + this.getTableName()
				+ " set nom=?,prenom=?,login=?,password=?,sex=?,derniereConnection=?,dateDeNaissance=?,adresse=?,codePostal=?,telephone=? where "
				+ this.getPkName() + "=?;");
		ps.setString(1, pUneEntite.getNom());
		ps.setString(2, pUneEntite.getPrenom());
		ps.setString(3, pUneEntite.getLogin());
		ps.setString(4, pUneEntite.getPassword());
		if (pUneEntite.getSex().equals(ESex.HOMME)) {
			ps.setByte(5, (byte) 0);
		} else if (pUneEntite.getSex().equals(ESex.FEMME)) {
			ps.setByte(5, (byte) 1);
		} else {
			ps.setByte(5, (byte) 2);
		}
		ps.setTimestamp(6, pUneEntite.getDerniereConnection());
		ps.setDate(7, pUneEntite.getDateDeNaissance());
		ps.setString(8, pUneEntite.getAdresse());
		if (pUneEntite.getCodePostal() != null) {
			ps.setInt(9, pUneEntite.getCodePostal().intValue());
		} else {
			ps.setNull(9, Types.INTEGER);
		}
		ps.setString(10, pUneEntite.getTelephone());
		ps.setInt(11, pUneEntite.getId().intValue());

		return ps;
	}

	@Override
	public IUtilisateurEntity selectLogin(String pLogin) throws ExceptionDao {
		IUtilisateurEntity result = null;
		UtilisateurDAO.LOG.debug("selectLogin sur " + this.getClass() + " pLogin=" + pLogin);
		try {
			result = this.getJdbcTemplate().queryForObject(
					"select " + this.getAllColumnNames() + " from " + this.getTableName() + " where login=?;",
					this.getMapper(), pLogin);
		} catch (EmptyResultDataAccessException e) {
			UtilisateurDAO.LOG.trace("Pas de resultat", e);
			return result;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return result;

	}

}