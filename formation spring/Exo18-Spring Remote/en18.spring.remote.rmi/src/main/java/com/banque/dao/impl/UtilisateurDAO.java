package com.banque.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.impl.UtilisateurEntity;

/**
 * Gestion des utilisateurs.
 */
@Repository
public class UtilisateurDAO extends AbstractDAO<UtilisateurEntity> implements IUtilisateurDAO {
	private static final Logger LOG = LogManager.getLogger(UtilisateurDAO.class);

	/**
	 * Constructeur de l'objet.
	 */
	public UtilisateurDAO() {
		super();
	}

	@Override
	public UtilisateurEntity selectLogin(String unLogin) throws ExceptionDao {
		UtilisateurDAO.LOG.trace("--> select pour " + unLogin);
		if (unLogin == null || unLogin.trim().isEmpty()) {
			UtilisateurDAO.LOG.warn("--  login est null");
			throw new ExceptionDao("Parametres invalides");
		}
		String request = "SELECT OBJECT(us) FROM UtilisateurEntity us WHERE us.login=:unLogin";
		TypedQuery<UtilisateurEntity> query = super.getEntityManager().createQuery(request, UtilisateurEntity.class);
		query.setParameter("unLogin", unLogin);

		List<UtilisateurEntity> resultat = query.getResultList();
		if (resultat == null || resultat.isEmpty()) {
			return null;
		}
		if (resultat.size() > 1) {
			UtilisateurDAO.LOG.error("--  Trouve plus de un utilisateur avec le login = " + unLogin);
			throw new ExceptionDao("Plus d'un resultat trouve!");
		}
		UtilisateurDAO.LOG.trace("<-- select pour " + unLogin);
		return resultat.get(0);
	}

	@Override
	protected Class<UtilisateurEntity> getEntityClass() {
		return UtilisateurEntity.class;
	}
}