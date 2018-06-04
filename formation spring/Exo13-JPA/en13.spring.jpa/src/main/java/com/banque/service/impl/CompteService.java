package com.banque.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.banque.dao.ICompteDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.dao.impl.CompteDAO;
import com.banque.entity.impl.CompteEntity;
import com.banque.service.ICompteService;
import com.banque.service.ex.AucunDroitException;
import com.banque.service.ex.EntityIntrouvableException;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.FonctionnelleException;

/**
 * Gestion des comptes.
 */
public class CompteService extends AbstractService implements ICompteService {
	private static final Logger LOG = LogManager.getLogger(CompteService.class);
	private ICompteDAO compteDao;

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pEntityManager
	 *            l'entite manager
	 */
	public CompteService(EntityManager pEntityManager) {
		super();
		this.compteDao = new CompteDAO();
		this.compteDao.setEntityManager(pEntityManager);
	}

	/**
	 * Recupere la propriete <i>compteDao</i>.
	 *
	 * @return the compteDao la valeur de la propriete.
	 */
	protected ICompteDAO getCompteDao() {
		return this.compteDao;
	}

	@Override
	public CompteEntity select(int unUtilisateurId, int unCompteId)
			throws FonctionnelleException, ErreurTechniqueException {
		CompteService.LOG
				.debug("select compte uId=" + String.valueOf(unUtilisateurId) + " cpId=" + String.valueOf(unCompteId));
		if (unUtilisateurId < 0) {
			throw new IllegalArgumentException("utilisateurId<0");
		}
		if (unCompteId < 0) {
			throw new IllegalArgumentException("compteId<0");
		}
		CompteEntity resultat = null;
		try {
			resultat = this.getCompteDao().select(unCompteId);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		if (resultat == null) {
			throw new EntityIntrouvableException();
		}

		if (unUtilisateurId != resultat.getUtilisateur().getId().intValue()) {
			throw new AucunDroitException();
		}
		CompteService.LOG.debug("select compte resultat=" + resultat);
		return resultat;
	}

	@Override
	public List<CompteEntity> selectAll(int unUtilisateurId) throws FonctionnelleException, ErreurTechniqueException {
		CompteService.LOG.debug("selectAll compte uId=" + String.valueOf(unUtilisateurId));
		if (unUtilisateurId < 0) {
			throw new NullPointerException("utilisateurId");
		}
		List<CompteEntity> resultat = new ArrayList<CompteEntity>();
		try {
			resultat = this.getCompteDao().selectAll("utilisateur.id=" + unUtilisateurId, "libelle ASC");
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		CompteService.LOG.debug("selectAll compte trouve " + String.valueOf(resultat.size()) + " compte(s)");
		return resultat;
	}
}