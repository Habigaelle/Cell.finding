package com.banque.entity;

import java.sql.Timestamp;

/**
 * Represente une operation.
 */
public interface IOperationEntity extends IEntity {

	/**
	 * Recupere la date.
	 *
	 * @return la date.
	 */
	public abstract Timestamp getDate();

	/**
	 * Recupere le libelle.
	 *
	 * @return le libelle
	 */
	public abstract String getLibelle();

	/**
	 * Recupere le montant.
	 *
	 * @return le montant
	 */
	public abstract Double getMontant();

	/**
	 * Modifie la date.
	 *
	 * @param uneDate
	 *            le nouvelle date
	 */
	public abstract void setDate(Timestamp uneDate);

	/**
	 * Modifie le libelle.
	 *
	 * @param unLibelle
	 *            le nouveau libelle
	 */
	public abstract void setLibelle(String unLibelle);

	/**
	 * Modifie le montant.
	 *
	 * @param unMontant
	 *            le nouveau montant
	 */
	public abstract void setMontant(Double unMontant);

	/**
	 * Recupere l'id du compte.
	 *
	 * @return l'id du compte.
	 */
	public abstract Integer getCompteId();

	/**
	 * Modifie l'id du compte.
	 *
	 * @param compteId
	 *            le nouveau id du compte.
	 */
	public abstract void setCompteId(Integer compteId);

}