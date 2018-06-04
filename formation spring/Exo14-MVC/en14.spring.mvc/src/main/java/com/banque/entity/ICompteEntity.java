package com.banque.entity;

import java.math.BigDecimal;

/**
 * Represente un compte.
 */
public interface ICompteEntity extends IEntity {

	/**
	 * Recupere le taux.
	 *
	 * @return le taux
	 */
	public abstract BigDecimal getTaux();

	/**
	 * Modifie le taux.
	 *
	 * @param aTaux
	 *            le nouveau taux
	 */
	public abstract void setTaux(BigDecimal aTaux);

	/**
	 * Recupere le decouvert.
	 *
	 * @return le decouvert
	 */
	public abstract BigDecimal getDecouvert();

	/**
	 * Recupere le libelle.
	 *
	 * @return le libelle
	 */
	public abstract String getLibelle();

	/**
	 * Recupere le solde.
	 *
	 * @return le solde.
	 */
	public abstract BigDecimal getSolde();

	/**
	 * Modifie le decouvert.
	 *
	 * @param unDecouvert
	 *            le nouveau decouvert
	 */
	public abstract void setDecouvert(BigDecimal unDecouvert);

	/**
	 * Modifie le libelle.
	 *
	 * @param unLibelle
	 *            le nouveau libelle
	 */
	public abstract void setLibelle(String unLibelle);

	/**
	 * Modifie le solde.
	 *
	 * @param unSolde
	 *            le nouveau solde
	 */
	public abstract void setSolde(BigDecimal unSolde);

	/**
	 * Recupere l'id de l'utilisateur.
	 *
	 * @return l'id de l'utilisateur.
	 */
	public abstract Integer getUtilisateurId();

	/**
	 * Modifie l'id de l'utilisateur.
	 *
	 * @param utilisateurId
	 *            le nouveau l'id de l'utilisateur.
	 */
	public abstract void setUtilisateurId(Integer utilisateurId);

}