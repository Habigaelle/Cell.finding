package com.banque.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.impl.AbstractEntity;

/**
 * Dao generique.
 *
 * @param <T>
 *            le type d'entite
 */
public interface IDAO<T extends AbstractEntity> {

	/**
	 * Insert un element.
	 *
	 * @param uneEntite
	 *            l'element a inserer.
	 * @return l'element insere.
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	public abstract T insert(T uneEntite) throws ExceptionDao;

	/**
	 * Met a jour un element.
	 *
	 * @param uneEntite
	 *            l'element a mettre a jour.
	 * @return l'element mis a jour.
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	public abstract T update(T uneEntite) throws ExceptionDao;

	/**
	 * Supprime un element.
	 *
	 * @param pUneEntite
	 *            l'element a supprimer.
	 * @return true si il a bien ete supprime
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	public abstract boolean delete(T pUneEntite) throws ExceptionDao;

	/**
	 * Selectionne l'element ayant comme valeur de clef primaire le parametre.
	 *
	 * @param pUneClef
	 *            la valeur de la clef primaire
	 *
	 * @return l'element trouve ou null si aucun
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	public abstract T select(int pUneClef) throws ExceptionDao;

	/**
	 * Selectionne tous les elements qui correspondent aux criteres.
	 *
	 * @param pAWhere
	 *            une clause where (sans 'where')
	 * @param pAnOrderBy
	 *            une clause orderby (sans 'orderby')
	 *
	 * @return la liste des elements trouves ou une liste vide si aucun
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	public abstract List<T> selectAll(String pAWhere, String pAnOrderBy) throws ExceptionDao;

	/**
	 * Recupere une transaction.
	 *
	 * @return une transaction.
	 * @throws ExceptionDAO
	 *             si un probleme survient
	 */
	public abstract EntityTransaction getEntityTransaction() throws ExceptionDao;

	/**
	 * Donne la valeur de l'attribut. <br/>
	 *
	 * @param EntityManager
	 *            la propriete entityManager
	 */
	public abstract void setEntityManager(EntityManager pEntityManager);

}