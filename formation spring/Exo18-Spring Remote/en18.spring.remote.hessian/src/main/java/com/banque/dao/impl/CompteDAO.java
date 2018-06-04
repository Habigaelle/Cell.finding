package com.banque.dao.impl;

import org.springframework.stereotype.Repository;

import com.banque.dao.ICompteDAO;
import com.banque.entity.ICompteEntity;

/**
 * Gestion des comptes.
 */
@Repository
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