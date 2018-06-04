package com.banque.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Represente un utilisateur.
 */
public interface IUtilisateurEntity extends IEntity {

	/**
	 * Recupere le telephone.
	 *
	 * @return le telephone.
	 */
	public abstract String getTelephone();

	/**
	 * Modifie le telephone
	 *
	 * @param aTelephone
	 *            le nouveau telephone
	 */
	public abstract void setTelephone(String aTelephone);

	/**
	 * Recupere la date de naissance.
	 *
	 * @return la date de naissance.
	 */
	public abstract Date getDateDeNaissance();

	/**
	 * Modifie la date de naissance.
	 *
	 * @param aDateDeNaissance
	 *            le nouvelle date de naissance.
	 */
	public abstract void setDateDeNaissance(Date aDateDeNaissance);

	/**
	 * Recupere l'adresse
	 *
	 * @return l'adresse
	 */
	public abstract String getAdresse();

	/**
	 * Modifie l'adresse
	 *
	 * @param aAdresse
	 *            le nouvelle adresse
	 */
	public abstract void setAdresse(String aAdresse);

	/**
	 * Recupere le code postal
	 *
	 * @return le code postal
	 */
	public abstract Integer getCodePostal();

	/**
	 * Modifie le code postal
	 *
	 * @param aCodePostal
	 *            le nouveau code postal
	 */
	public abstract void setCodePostal(Integer aCodePostal);

	/**
	 * Recupere le sex
	 *
	 * @return le sex
	 */
	public abstract Boolean getSex();

	/**
	 * Modifie le sex.
	 *
	 * @param pSex
	 *            le sex
	 */
	public abstract void setSex(Boolean pSex);

	/**
	 * Recupere le login
	 *
	 * @return le login
	 */
	public abstract String getLogin();

	/**
	 * Modifie le login
	 *
	 * @param pLogin
	 *            le nouveau login
	 */
	public abstract void setLogin(String pLogin);

	/**
	 * Recupere le password
	 *
	 * @return le password
	 */
	public abstract String getPassword();

	/**
	 * Modifie le mot de passe
	 *
	 * @param pPassword
	 *            le nouveau mot de passe
	 */
	public abstract void setPassword(String pPassword);

	/**
	 * Recupere le nom
	 *
	 * @return le nom
	 */
	public abstract String getNom();

	/**
	 * Modifie le nom
	 *
	 * @param pNom
	 *            le nouveau nom
	 */
	public abstract void setNom(String pNom);

	/**
	 * Recupere le prenom
	 *
	 * @return le prenom
	 */
	public abstract String getPrenom();

	/**
	 * Modifie le prenom
	 *
	 * @param pPrenom
	 *            le nouveau prenom
	 */
	public abstract void setPrenom(String pPrenom);

	/**
	 * Recupere la derniere connection.
	 *
	 * @return la derniere connection.
	 */
	public abstract Timestamp getDerniereConnection();

	/**
	 * Modifie la derniere connection.
	 *
	 * @param pDerniereConnection
	 *            le nouvelle la derniere connection.
	 */
	public abstract void setDerniereConnection(Timestamp pDerniereConnection);

}