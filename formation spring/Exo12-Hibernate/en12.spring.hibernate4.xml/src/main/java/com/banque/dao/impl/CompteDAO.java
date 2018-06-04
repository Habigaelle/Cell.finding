package com.banque.dao.impl;

import com.banque.dao.ICompteDAO;
import com.banque.entity.ICompteEntity;

/**
 * Gestion des comptes.
 */
public class CompteDAO extends AbstractDAO<ICompteEntity> implements ICompteDAO {

	/**
	 * Constructeur de l'objet.
	 */
	public CompteDAO() {
		super();
	}

	@Override
	protected Class<ICompteEntity> getEntityClass() {
		return ICompteEntity.class;
	}

}