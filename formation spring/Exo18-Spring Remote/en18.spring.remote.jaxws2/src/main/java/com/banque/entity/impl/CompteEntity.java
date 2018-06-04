package com.banque.entity.impl;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Le bean qui represente un Compte. <br>
 */
@Entity
@Table(name = "compte")
public class CompteEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "libelle", length = 250)
	private String libelle;
	@Column(name = "solde", precision = 10)
	private BigDecimal solde;
	@Column(name = "decouvert", precision = 10)
	private BigDecimal decouvert;
	@Column(name = "taux", precision = 5)
	private BigDecimal taux;

	@OneToMany(mappedBy = "compte", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<OperationEntity> operations;

	@ManyToOne
	@JoinColumn(name = "utilisateurId", nullable = false, unique = true)
	private UtilisateurEntity utilisateur;

	/**
	 * Constructeur de l'objet. <br>
	 */
	public CompteEntity() {
		this(null, null, null, null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param pId
	 *            un id
	 */
	public CompteEntity(Integer pId) {
		this(pId, null, null, null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unId
	 *            l'id d'un compte
	 * @param unLibelle
	 *            le libelle du compte
	 * @param unSolde
	 *            le solde du compte
	 * @param unDecouvert
	 *            le decouvert du compte
	 */
	public CompteEntity(Integer unId, String unLibelle, BigDecimal unSolde, BigDecimal unDecouvert) {
		super(unId);
		this.setLibelle(unLibelle);
		this.setSolde(unSolde);
		this.setDecouvert(unDecouvert);
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
		if (unLibelle == null || unLibelle.trim().length() == 0) {
			this.libelle = null;
		} else {
			this.libelle = unLibelle;
		}
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete solde
	 */
	public BigDecimal getSolde() {
		return this.solde;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param solde
	 *            la nouvelle valeur pour l'attribut solde
	 */
	public void setSolde(BigDecimal solde) {
		this.solde = solde;
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete decouvert
	 */
	public BigDecimal getDecouvert() {
		return this.decouvert;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param decouvert
	 *            la nouvelle valeur pour l'attribut decouvert
	 */
	public void setDecouvert(BigDecimal decouvert) {
		this.decouvert = decouvert;
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete taux
	 */
	public BigDecimal getTaux() {
		return this.taux;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param taux
	 *            la nouvelle valeur pour l'attribut taux
	 */
	public void setTaux(BigDecimal taux) {
		this.taux = taux;
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete operations
	 */
	public Set<OperationEntity> getOperations() {
		return this.operations;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param operations
	 *            la nouvelle valeur pour l'attribut operations
	 */
	public void setOperations(Set<OperationEntity> operations) {
		this.operations = operations;
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete utilisateur
	 */
	public UtilisateurEntity getUtilisateur() {
		return this.utilisateur;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param utilisateur
	 *            la nouvelle valeur pour l'attribut utilisateur
	 */
	public void setUtilisateur(UtilisateurEntity utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String parent = super.toString();
		parent = parent.substring(0, parent.length() - 1);
		sb.append(parent);
		sb.append(",utilisateur=");
		if (this.getUtilisateur() != null) {
			// Attention a ne pas appeler le toString de utilisateur
			// sinon boucle infinie
			sb.append(this.getUtilisateur().getNom());
		}
		sb.append(",libelle=");
		sb.append(this.getLibelle());
		sb.append(",solde=");
		sb.append(this.getSolde());
		sb.append(",decouvert=");
		sb.append(this.getDecouvert());
		sb.append(",taux=");
		sb.append(this.getTaux());
		Set<OperationEntity> ops = this.getOperations();
		if (ops != null && !ops.isEmpty()) {
			sb.append(",operations=");
			for (OperationEntity operationEntity : ops) {
				// Attention a ne pas appeler le toString de operation
				// Sinon = boucle infinie
				sb.append(operationEntity.getId()).append(',');
			}
			sb.delete(sb.length() - 1, sb.length());
		}

		sb.append("}");
		return sb.toString();
	}

}