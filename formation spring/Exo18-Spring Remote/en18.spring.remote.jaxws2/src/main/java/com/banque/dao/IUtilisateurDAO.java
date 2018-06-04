package com.banque.dao;

import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.impl.UtilisateurEntity;

/**
 * Interface representant l'utilisateur.
 */
public interface IUtilisateurDAO extends IDAO<UtilisateurEntity> {

	/**
	 * Selectionne le premier utilisateur ayant le login indique.
	 *
	 * @param pLogin
	 *            un login.
	 * @return l'utilisateur
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	public abstract UtilisateurEntity selectLogin(String pLogin) throws ExceptionDao;

}