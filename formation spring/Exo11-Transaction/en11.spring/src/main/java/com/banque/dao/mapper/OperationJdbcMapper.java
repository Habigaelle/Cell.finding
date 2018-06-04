package com.banque.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.banque.entity.IOperationEntity;
import com.banque.entity.impl.OperationEntity;

/**
 * Objet utilitaire pour le JDBC spring.
 */
public class OperationJdbcMapper implements RowMapper<IOperationEntity> {

	/**
	 * Constructeur de l'objet.
	 */
	public OperationJdbcMapper() {
		super();
	}

	@Override
	public IOperationEntity mapRow(ResultSet rs, int id) throws SQLException {
		IOperationEntity result = new OperationEntity();
		result.setId(Integer.valueOf(rs.getInt("id")));
		result.setLibelle(rs.getString("libelle"));
		double vm = rs.getDouble("montant");
		// Le montant etait-il null ?
		boolean mnull = rs.wasNull();
		if (!mnull) {
			result.setMontant(Double.valueOf(vm));
		} else {
			result.setMontant(null);
		}
		result.setDate(rs.getTimestamp("date"));
		result.setCompteId(Integer.valueOf(rs.getInt("compteId")));
		return result;
	}

}
