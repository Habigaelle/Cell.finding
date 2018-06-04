package com.banque.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.banque.entity.ICompteEntity;
import com.banque.entity.impl.CompteEntity;

/**
 * Objet utilitaire pour le JDBC spring.
 */
public class CompteJdbcMapper implements RowMapper<ICompteEntity> {

	/**
	 * Constructeur de l'objet.
	 */
	public CompteJdbcMapper() {
		super();
	}

	@Override
	public ICompteEntity mapRow(ResultSet rs, int id) throws SQLException {
		ICompteEntity result = new CompteEntity();
		result.setId(Integer.valueOf(rs.getInt("id")));
		result.setLibelle(rs.getString("libelle"));
		result.setSolde(Double.valueOf(rs.getDouble("solde")));
		result.setUtilisateurId(Integer.valueOf(rs.getInt("utilisateurId")));
		double vd = rs.getDouble("decouvert");
		// Le decouvert etait-il null ?
		boolean dnull = rs.wasNull();
		double vt = rs.getDouble("taux");
		// Le taux etait-il null ?
		boolean tnull = rs.wasNull();
		if (!dnull) {
			result.setDecouvert(Double.valueOf(vd));
		} else {
			result.setDecouvert(null);
		}
		if (!tnull) {
			result.setTaux(Double.valueOf(vt));
		} else {
			result.setTaux(null);
		}
		return result;
	}

}
