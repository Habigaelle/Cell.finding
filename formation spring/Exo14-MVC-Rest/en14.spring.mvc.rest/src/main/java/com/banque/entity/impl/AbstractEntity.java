package com.banque.entity.impl;

import java.io.Serializable;

import com.banque.entity.IEntity;

/**
 * Le bean qui represente une entite. <br>
 */
abstract class AbstractEntity implements Serializable, IEntity {

	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * Constructeur de l'objet. <br>
	 */
	protected AbstractEntity() {
		super();
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unId
	 *            l'id d'un compte
	 */
	public AbstractEntity(Integer unId) {
		super();
		this.id = unId;
	}

	@Override
	public final Integer getId() {
		return this.id;
	}

	@Override
	public final void setId(Integer unId) {
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
		if (obj instanceof IEntity && this.getClass() == obj.getClass()) {
			return ((IEntity) obj).getId() == this.getId() || ((IEntity) obj).getId().equals(this.getId());
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{class=");
		sb.append(this.getClass().getName());
		sb.append(",id=");
		sb.append(this.getId());
		sb.append('}');
		return sb.toString();
	}
}