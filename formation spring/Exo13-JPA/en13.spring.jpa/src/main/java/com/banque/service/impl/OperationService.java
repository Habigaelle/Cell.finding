package com.banque.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.banque.dao.ICompteDAO;
import com.banque.dao.IOperationDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.dao.impl.CompteDAO;
import com.banque.dao.impl.OperationDAO;
import com.banque.entity.impl.CompteEntity;
import com.banque.entity.impl.OperationEntity;
import com.banque.service.IOperationService;
import com.banque.service.ex.AucunDroitException;
import com.banque.service.ex.DecouvertException;
import com.banque.service.ex.EntityIntrouvableException;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.FonctionnelleException;

/**
 * Gestion des operations.
 */
public class OperationService extends AbstractService implements IOperationService {
	private static final Logger LOG = LogManager.getLogger(OperationService.class);
	private IOperationDAO operationDao;
	private ICompteDAO compteDao;

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pEntityManager
	 *            l'entite manager
	 */
	public OperationService(EntityManager pEntityManager) {
		super();
		this.operationDao = new OperationDAO();
		this.operationDao.setEntityManager(pEntityManager);
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

	/**
	 * Recupere la propriete <i>operationDao</i>.
	 *
	 * @return the operationDao la valeur de la propriete.
	 */
	protected IOperationDAO getOperationDao() {
		return this.operationDao;
	}

	@Override
	public OperationEntity select(int unUtilisateurId, int unCompteId, int uneOperationId)
			throws FonctionnelleException, ErreurTechniqueException {
		OperationService.LOG.debug("select operation uId=" + String.valueOf(unUtilisateurId) + " cpId="
				+ String.valueOf(unCompteId) + " opId=" + String.valueOf(uneOperationId));

		if (unUtilisateurId < 0) {
			throw new IllegalArgumentException("utilisateurId<0");
		}
		if (unCompteId < 0) {
			throw new IllegalArgumentException("compteId<0");
		}
		if (uneOperationId < 0) {
			throw new IllegalArgumentException("operationId<0");
		}

		// On verifie que le compte appartient bien a l'utilisateur
		CompteEntity compte = null;
		try {
			compte = this.getCompteDao().select(unCompteId);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		if (compte == null) {
			throw new EntityIntrouvableException();
		}
		if (compte.getUtilisateur() == null || unUtilisateurId != compte.getUtilisateur().getId().intValue()) {
			throw new AucunDroitException();
		}

		OperationEntity resultat = null;
		try {
			resultat = this.getOperationDao().select(uneOperationId);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		if (resultat == null) {
			throw new EntityIntrouvableException();
		}
		if (resultat.getCompte() == null || unCompteId != resultat.getCompte().getId().intValue()) {
			throw new AucunDroitException();
		}
		OperationService.LOG.debug("select operation resultat=" + resultat);

		return resultat;
	}

	@Override
	public List<OperationEntity> selectAll(int unUtilisateurId, int unCompteId)
			throws FonctionnelleException, ErreurTechniqueException {
		OperationService.LOG.debug(
				"selectAll operation uId=" + String.valueOf(unUtilisateurId) + " cpId=" + String.valueOf(unCompteId));

		if (unUtilisateurId < 0) {
			throw new IllegalArgumentException("utilisateurId<0");
		}
		if (unCompteId < 0) {
			throw new IllegalArgumentException("compteId<0");
		}

		CompteEntity compte;
		try {
			compte = this.getCompteDao().select(unCompteId);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		if (compte == null) {
			throw new EntityIntrouvableException();
		}
		if (compte.getUtilisateur() == null || unUtilisateurId != compte.getUtilisateur().getId().intValue()) {
			throw new AucunDroitException();
		}

		List<OperationEntity> resultat = new ArrayList<OperationEntity>();
		try {
			resultat = this.getOperationDao().selectAll("compte.id=" + unCompteId, "date DESC");
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		OperationService.LOG.debug("selectAll trouve " + String.valueOf(resultat.size()) + " operation(s)");
		return resultat;
	}

	@Override
	public List<OperationEntity> selectCritere(int unUtilisateurId, int unCompteId, Date unDebut, Date uneFin,
			boolean pCredit, boolean pDebit) throws FonctionnelleException, ErreurTechniqueException {
		OperationService.LOG.debug("selectCritere operation uId=" + String.valueOf(unUtilisateurId) + " cpId="
				+ String.valueOf(unCompteId) + " debut=" + unDebut + " fin=" + uneFin + " credit="
				+ String.valueOf(pCredit) + " debit=" + String.valueOf(pDebit));

		if (unUtilisateurId < 0) {
			throw new IllegalArgumentException("utilisateurId<0");
		}
		if (unCompteId < 0) {
			throw new IllegalArgumentException("compteId<0");
		}
		Boolean crediDebit = null;
		if (pCredit && !pDebit) {
			crediDebit = Boolean.TRUE;
		} else if (!pCredit && pDebit) {
			crediDebit = Boolean.FALSE;
		}
		List<OperationEntity> resultat = new ArrayList<OperationEntity>();
		try {
			resultat = this.getOperationDao().selectCriteria(unCompteId, unDebut, uneFin, crediDebit);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		OperationService.LOG.debug("selectCritere trouve " + String.valueOf(resultat.size()) + " operation(s)");
		return resultat;
	}

	@Override
	public List<OperationEntity> faireVirement(int unUtilisateurId, int unCompteIdSrc, int unCompteIdDst,
			double unMontant) throws FonctionnelleException, ErreurTechniqueException {
		OperationService.LOG.debug("faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc="
				+ String.valueOf(unCompteIdSrc) + " cpIdDest=" + String.valueOf(unCompteIdDst));

		if (unUtilisateurId < 0) {
			throw new IllegalArgumentException("utilisateurId<0");
		}
		if (unCompteIdSrc < 0) {
			throw new IllegalArgumentException("compteIdSrc<0");
		}
		if (unCompteIdDst < 0) {
			throw new IllegalArgumentException("compteIdDst<0");
		}
		if (unMontant < 0) {
			throw new IllegalArgumentException("montant<0");
		}

		List<OperationEntity> resultat = new ArrayList<OperationEntity>(2);
		OperationEntity opSrc = null;
		OperationEntity opDst = null;
		EntityTransaction transaction = null;

		try {
			transaction = this.getCompteDao().getEntityTransaction();
			transaction.begin();
			CompteEntity compteSrc = null;
			try {
				compteSrc = this.getCompteDao().select(unCompteIdSrc);
			} catch (ExceptionDao e) {
				OperationService.LOG.error("faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc="
						+ String.valueOf(unCompteIdSrc) + " cpIdDest=" + String.valueOf(unCompteIdDst) + " - KO", e);
				throw new ErreurTechniqueException(e);
			}
			if (compteSrc == null) {
				OperationService.LOG.warn("faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc="
						+ String.valueOf(unCompteIdSrc) + " cpIdDest=" + String.valueOf(unCompteIdDst)
						+ " - cpt src pas trouve");
				throw new EntityIntrouvableException();
			}
			if (unUtilisateurId != compteSrc.getUtilisateur().getId().intValue()) {
				OperationService.LOG.warn("faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc="
						+ String.valueOf(unCompteIdSrc) + " cpIdDest=" + String.valueOf(unCompteIdDst)
						+ " - cpt src trouve mais n'appartient pas au bon utilisateur");
				throw new AucunDroitException();
			}
			CompteEntity compteDst = null;
			try {
				compteDst = this.getCompteDao().select(unCompteIdDst);
			} catch (ExceptionDao e) {
				OperationService.LOG.error("faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc="
						+ String.valueOf(unCompteIdSrc) + " cpIdDest=" + String.valueOf(unCompteIdDst) + " - KO", e);
				throw new ErreurTechniqueException(e);
			}
			if (compteDst == null) {
				OperationService.LOG.warn("faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc="
						+ String.valueOf(unCompteIdSrc) + " cpIdDest=" + String.valueOf(unCompteIdDst)
						+ " - cpt dest pas trouve");
				throw new EntityIntrouvableException();
			}
			if (unUtilisateurId != compteDst.getUtilisateur().getId().intValue()) {
				OperationService.LOG.warn("faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc="
						+ String.valueOf(unCompteIdSrc) + " cpIdDest=" + String.valueOf(unCompteIdDst)
						+ " - cpt dest trouve mais n'appartient pas au bon utilisateur");
				throw new AucunDroitException();
			}

			double montant = unMontant;
			// Simulation
			double soldeSrc = compteSrc.getSolde().doubleValue();
			final double decouvertSrc = compteSrc.getDecouvert() != null ? compteSrc.getDecouvert().doubleValue()
					: Double.MIN_VALUE;
			double soldeDst = compteDst.getSolde().doubleValue();
			final double decouvertDst = compteDst.getDecouvert() != null ? compteDst.getDecouvert().doubleValue()
					: Double.MIN_VALUE;

			// On retire de la source
			soldeSrc -= montant;
			// On ajoute a destination
			soldeDst += montant;
			// On regarde si les decouverts suivent
			if (soldeSrc > decouvertSrc) {
				// tout est ok
			} else {
				OperationService.LOG.warn("faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc="
						+ String.valueOf(unCompteIdSrc) + " cpIdDest=" + String.valueOf(unCompteIdDst)
						+ " - decouvert sur cpt src");
				throw new DecouvertException();
			}
			if (soldeDst > decouvertDst) {
				// tout est ok
			} else {
				OperationService.LOG.warn("faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc="
						+ String.valueOf(unCompteIdSrc) + " cpIdDest=" + String.valueOf(unCompteIdDst)
						+ " - decouvert sur cpt dest");
				throw new DecouvertException();
			}

			Timestamp now = new Timestamp(System.currentTimeMillis());
			opSrc = new OperationEntity();
			opSrc.setCompte(compteSrc);
			opSrc.setDate(now);
			opSrc.setMontant(Double.valueOf(-montant));
			opSrc.setLibelle("Transaction avec le compte " + compteDst.getLibelle());

			opDst = new OperationEntity();
			opDst.setCompte(compteDst);
			opDst.setDate(now);
			opDst.setMontant(Double.valueOf(montant));
			opDst.setLibelle("Transaction avec le compte " + compteSrc.getLibelle());

			compteSrc.setSolde(BigDecimal.valueOf(soldeSrc));
			compteDst.setSolde(BigDecimal.valueOf(soldeDst));

			OperationService.LOG.trace(
					"faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc=" + String.valueOf(unCompteIdSrc)
							+ " cpIdDest=" + String.valueOf(unCompteIdDst) + " - Avant insert des operations");
			opSrc = this.getOperationDao().insert(opSrc);
			opDst = this.getOperationDao().insert(opDst);

			OperationService.LOG.trace(
					"faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc=" + String.valueOf(unCompteIdSrc)
							+ " cpIdDest=" + String.valueOf(unCompteIdDst) + " - Avant MAJ des comptes");
			this.getCompteDao().update(compteSrc);
			this.getCompteDao().update(compteDst);
			transaction.commit();

			resultat.add(opSrc);
			resultat.add(opDst);
		} catch (Exception e) {

			try {
				if (transaction != null) {
					transaction.rollback();
				}
			} catch (Exception e1) {
				OperationService.LOG.warn("faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc="
						+ String.valueOf(unCompteIdSrc) + " cpIdDest=" + String.valueOf(unCompteIdDst) + " - KO", e1);
			}
			OperationService.LOG.error("faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc="
					+ String.valueOf(unCompteIdSrc) + " cpIdDest=" + String.valueOf(unCompteIdDst) + " - KO", e);
			throw new ErreurTechniqueException(e);
		}
		OperationService.LOG.debug("faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc="
				+ String.valueOf(unCompteIdSrc) + " cpIdDest=" + String.valueOf(unCompteIdDst) + " - OK");
		return resultat;

	}
}