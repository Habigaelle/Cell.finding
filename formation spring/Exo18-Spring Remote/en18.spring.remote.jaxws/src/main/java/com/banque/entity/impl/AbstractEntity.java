package com.banque.entity.impl;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Classe abstraite qui represente toutes les entites. <br/>
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * Constructeur de l'objet. <br>
	 */
	public AbstractEntity() {
		this(null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unId
	 *            l'id d'un compte
	 */
	public AbstractEntity(Integer unId) {
		super();
		this.setId(unId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(this.getClass().getName());
		sb.append(",id=");
		sb.append(this.getId());
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Recupere l'id.
	 *
	 * @return l'id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Modifie l'id.
	 *
	 * @param unId
	 *            un id
	 */
	public void setId(Integer unId) {
		this.id = unId;
	}

	@Override
	public int hashCode() {
		if (this.getId() != null) {
			return (this.getClass().getName() + "-" + this.getId()).hashCode();
		}
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof AbstractEntity && this.getClass() == obj.getClass()) {
			return ((AbstractEntity) obj).getId() == this.getId()
					|| ((AbstractEntity) obj).getId().equals(this.getId());
		}
		return false;
	}
}