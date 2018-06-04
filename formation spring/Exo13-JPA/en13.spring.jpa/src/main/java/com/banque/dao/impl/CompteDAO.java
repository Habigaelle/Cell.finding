package com.banque.dao.impl;

import com.banque.dao.ICompteDAO;
import com.banque.entity.impl.CompteEntity;

/**
 * Gestion des comptes.
 */
public class CompteDAO extends AbstractDAO<CompteEntity> implements ICompteDAO {

	/**
	 * Constructeur de l'objet.
	 */
	public CompteDAO() {
		super();
	}

	@Override
	protected Class<CompteEntity> getEntityClass() {
		return CompteEntity.class;
	}

}