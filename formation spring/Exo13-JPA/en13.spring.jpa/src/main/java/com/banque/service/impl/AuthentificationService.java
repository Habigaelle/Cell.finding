package com.banque.service.impl;

import javax.persistence.EntityManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.dao.impl.UtilisateurDAO;
import com.banque.entity.impl.UtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.FonctionnelleException;
import com.banque.service.ex.MauvaisMotdepasseException;
import com.banque.service.ex.UtilisateurInconnuException;

/**
 * Gestion de l'authentification.
 */
public class AuthentificationService extends AbstractService implements IAuthentificationService {
	private static final Logger LOG = LogManager.getLogger(AuthentificationService.class);
	private IUtilisateurDAO utilisateurDAO;

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pEntityManager
	 *            l'entite manager
	 */
	public AuthentificationService(EntityManager pEntityManager) {
		super();
		this.utilisateurDAO = new UtilisateurDAO();
		this.utilisateurDAO.setEntityManager(pEntityManager);
	}

	/**
	 * Recupere la propriete <i>utilisateurDAO</i>.
	 *
	 * @return the utilisateurDAO la valeur de la propriete.
	 */
	protected IUtilisateurDAO getUtilisateurDAO() {
		return this.utilisateurDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public UtilisateurEntity authentifier(String pLogin, String pPassword)
			throws FonctionnelleException, ErreurTechniqueException {
		AuthentificationService.LOG.debug("authentifier " + pLogin);
		if (pLogin == null || pLogin.trim().length() == 0) {
			throw new IllegalArgumentException("login");
		}
		if (pPassword == null || pPassword.trim().length() == 0) {
			throw new IllegalArgumentException("password");
		}
		UtilisateurEntity resultat = null;
		try {
			resultat = this.getUtilisateurDAO().selectLogin(pLogin);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		if (resultat == null) {
			throw new UtilisateurInconnuException();
		}
		if (!pPassword.equals(resultat.getPassword())) {
			throw new MauvaisMotdepasseException();
		}
		AuthentificationService.LOG.debug("authentifier Resultat=" + resultat);
		return resultat;
	}
}
