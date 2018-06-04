package com.banque.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banque.dao.ICompteDAO;
import com.banque.dao.IOperationDAO;
import com.banque.dao.ex.ExceptionDao;
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
@Service
public class OperationService extends AbstractService implements IOperationService {
	private static final Logger LOG = LogManager.getLogger(OperationService.class);
	@Autowired
	private IOperationDAO operationDao;
	@Autowired
	private ICompteDAO compteDao;

	/**
	 * Constructeur de l'objet.
	 */
	public OperationService() {
		super();
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
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
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
	@Transactional(rollbackFor = Exception.class)
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

		CompteEntity compteSrc = null;
		try {
			compteSrc = this.getCompteDao().select(unCompteIdSrc);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		if (compteSrc == null) {
			throw new EntityIntrouvableException();
		}
		if (compteSrc.getUtilisateur() == null || unUtilisateurId != compteSrc.getUtilisateur().getId().intValue()) {
			throw new AucunDroitException();
		}
		CompteEntity compteDst = null;
		try {
			compteDst = this.getCompteDao().select(unCompteIdDst);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		if (compteDst == null) {
			throw new EntityIntrouvableException();
		}
		if (compteDst.getUtilisateur() == null || unUtilisateurId != compteDst.getUtilisateur().getId().intValue()) {
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
		if (soldeSrc <= decouvertSrc || soldeDst <= decouvertDst) {
			throw new DecouvertException();
		}

		Timestamp now = new Timestamp(System.currentTimeMillis());
		OperationEntity opSrc = new OperationEntity();
		opSrc.setCompte(compteSrc);
		opSrc.setDate(now);
		opSrc.setMontant(Double.valueOf(-montant));
		opSrc.setLibelle("Transaction avec le compte " + unCompteIdDst);

		OperationEntity opDst = new OperationEntity();
		opDst.setCompte(compteDst);
		opDst.setDate(now);
		opDst.setMontant(Double.valueOf(unMontant));
		opDst.setLibelle("Transaction avec le compte " + unCompteIdSrc);

		List<OperationEntity> resultat = new ArrayList<OperationEntity>(2);
		try {
			opSrc = this.getOperationDao().insert(opSrc);
			opDst = this.getOperationDao().insert(opDst);
			compteSrc.setSolde(BigDecimal.valueOf(soldeSrc));
			compteDst.setSolde(BigDecimal.valueOf(soldeDst));
			this.getCompteDao().update(compteSrc);
			this.getCompteDao().update(compteDst);

			resultat.add(opSrc);
			resultat.add(opDst);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}

		return resultat;
	}
}