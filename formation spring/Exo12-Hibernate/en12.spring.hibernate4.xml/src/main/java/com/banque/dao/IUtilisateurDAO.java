package com.banque.dao;

import org.hibernate.Session;

import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IUtilisateurEntity;

/**
 * Interface representant l'utilisateur.
 */
public interface IUtilisateurDAO extends IDAO<IUtilisateurEntity> {

	/**
	 * Selectionne le premier utilisateur ayant le login indique.
	 *
	 * @param pLogin
	 *            un login.
	 * @param pSession
	 *            session hibernate
	 * @return l'utilisateur
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	public abstract IUtilisateurEntity selectLogin(String pLogin, Session pSession) throws ExceptionDao;

}