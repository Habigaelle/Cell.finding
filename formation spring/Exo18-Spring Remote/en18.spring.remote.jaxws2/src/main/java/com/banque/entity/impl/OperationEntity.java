package com.banque.entity.impl;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Le bean qui represente une operation. <br>
 */
@Entity
@Table(name = "operation")
public class OperationEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "date", length = 19)
	private Timestamp date;

	@Column(name = "libelle", length = 250)
	private String libelle;

	@Column(name = "montant", precision = 22, scale = 0)
	private Double montant;

	@ManyToOne
	@JoinColumn(name = "compteId", nullable = false, unique = true)
	private CompteEntity compte;

	/**
	 * Constructeur de l'objet. <br>
	 */
	public OperationEntity() {
		this(null, null, null, null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param pId
	 *            un id
	 */
	public OperationEntity(Integer pId) {
		this(pId, null, null, null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unId
	 *            unid
	 * @param uneDate
	 *            l'id d'un compte
	 * @param unLibelle
	 *            le libelle du compte
	 * @param unMontant
	 *            un montant
	 */
	public OperationEntity(Integer unId, Timestamp uneDate, String unLibelle, Double unMontant) {
		super(unId);
		this.setDate(uneDate);
		this.setLibelle(unLibelle);
		this.setMontant(unMontant);
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete date
	 */
	public Timestamp getDate() {
		return this.date;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param uneDate
	 *            la nouvelle valeur pour l'attribut date
	 */
	public void setDate(Timestamp uneDate) {
		if (uneDate == null) {
			this.date = new Timestamp(System.currentTimeMillis());
		} else {
			this.date = uneDate;
		}
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete libelle
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param unLibelle
	 *            la nouvelle valeur pour l'attribut libelle
	 */
	public void setLibelle(String unLibelle) {
		if (unLibelle == null || unLibelle.trim().isEmpty()) {
			this.libelle = null;
		} else {
			this.libelle = unLibelle;
		}
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete montant
	 */
	public Double getMontant() {
		return this.montant;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param montant
	 *            la nouvelle valeur pour l'attribut montant
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete compte
	 */
	public CompteEntity getCompte() {
		return this.compte;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param compte
	 *            la nouvelle valeur pour l'attribut compte
	 */
	public void setCompte(CompteEntity compte) {
		this.compte = compte;
	}

	/**
	 * Donne une representation chainee de l'objet
	 *
	 * @return une representation chainee de l'objet
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String parent = super.toString();
		parent = parent.substring(0, parent.length() - 1);
		sb.append(parent);
		sb.append(",compte=");
		sb.append(this.getCompte());
		sb.append(",libelle=");
		sb.append(this.getLibelle());
		sb.append(",montant=");
		sb.append(this.getMontant());
		sb.append("}");
		return sb.toString();
	}

}