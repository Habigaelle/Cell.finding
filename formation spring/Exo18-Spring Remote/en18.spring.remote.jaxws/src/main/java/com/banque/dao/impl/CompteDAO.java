package com.banque.dao.impl;

import org.springframework.stereotype.Repository;

import com.banque.dao.ICompteDAO;
import com.banque.entity.impl.CompteEntity;

/**
 * Gestion des comptes.
 */
@Repository
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