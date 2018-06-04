package com.banque.entity;

import java.io.Serializable;

/**
 * Represente une entite.
 */
public interface IEntity extends Serializable {

	/**
	 * Recupere l'id de l'entite.
	 *
	 * @return l'id de l'entite.
	 */
	public abstract Integer getId();

	/**
	 * Fixe l'id de l'entite.
	 *
	 * @param unId
	 *            l'id de l'entite.
	 */
	public abstract void setId(Integer unId);

}