package com.banque;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.banque.entity.ICompteEntity;
import com.banque.entity.IUtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ICompteService;
import com.banque.service.IOperationService;
import com.banque.service.impl.AuthentificationService;
import com.banque.service.impl.CompteService;
import com.banque.service.impl.OperationService;

/**
 * Exemple.
 */
public final class Main {
	/** Main log. */
	private static final Logger LOG = LogManager.getLogger(Main.class);

	/**
	 * Constructeur.
	 */
	private Main() {
		super();
		Main.LOG.error("Ne pas utiliser le constructeur");
	}

	/**
	 * Exemple de fonctionnement.
	 *
	 * @param args
	 *            ne sert a rien
	 */
	public static void main(String[] args) {
		Main.LOG.info("-- Debut -- ");
		try {
			// On instancie le service authentification afin de recuperer un
			// utilisateur
			IAuthentificationService serviceAuth = new AuthentificationService();
			IUtilisateurEntity utilisateur = serviceAuth.authentifier("dj", "dj");
			Main.LOG.info("Bonjour " + utilisateur.getNom() + " " + utilisateur.getPrenom());
			// On instancie le service de gestion des comptes pour recuperer la
			// liste de ses comptes
			ICompteService serviceCpt = new CompteService();
			List<ICompteEntity> listeCpts = serviceCpt.selectAll(utilisateur.getId().intValue());
			Main.LOG.info("Vous avez " + Integer.valueOf(listeCpts.size()) + " comptes");
			// On prend deux id de comptes pour faire un virement
			int[] deuxId = new int[2];
			Iterator<ICompteEntity> iter = listeCpts.iterator();
			int id = 0;
			while (iter.hasNext() && id < deuxId.length) {
				ICompteEntity compteEntity = iter.next();
				deuxId[id] = compteEntity.getId().intValue();
				id++;
			}

			// On Effectue un virement entre deux comptes, via le service des
			// operations
			IOperationService serviceOp = new OperationService();
			serviceOp.faireVirement(utilisateur.getId().intValue(), deuxId[0], deuxId[1], 5D);
			Main.LOG.info("Votre virement s'est bien effectue");
		} catch (Exception e) {
			Main.LOG.fatal("Erreur", e);
			System.exit(-1);
		}
		Main.LOG.info("-- Fin -- ");
		System.exit(0);
	}
}
