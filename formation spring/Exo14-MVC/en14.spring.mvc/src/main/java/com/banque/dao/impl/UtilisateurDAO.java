package com.banque.dao.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IUtilisateurEntity;

/**
 * Gestion des operations.
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
	protected Class<IUtilisateurEntity> getEntityClass() {
		return IUtilisateurEntity.class;
	}

	@Override
	public IUtilisateurEntity selectLogin(String pLogin) throws ExceptionDao {
		UtilisateurDAO.LOG.debug("selectLogin avec pLogin=" + pLogin);
		List<IUtilisateurEntity> allLogin = super.selectAll("entity.login='" + pLogin + "'", null);
		if (allLogin == null || allLogin.isEmpty()) {
			return null;
		}
		// On retourne le premier
		return allLogin.get(0);
	}

}