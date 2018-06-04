package com.banque;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.banque.entity.impl.CompteEntity;
import com.banque.entity.impl.UtilisateurEntity;
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
		EntityManager em = null;
		try {
			// "JPABanque" est le name qui se trouve dans META-INF/persistence.xml
			em = Persistence.createEntityManagerFactory("JPABanque").createEntityManager();

			IAuthentificationService serviceAuth = new AuthentificationService(em);
			UtilisateurEntity utilisateur = serviceAuth.authentifier("dj", "dj");
			Main.LOG.info("Bonjour " + utilisateur.getNom() + " " + utilisateur.getPrenom());

			ICompteService serviceCpt = new CompteService(em);
			List<CompteEntity> listeCpts = serviceCpt.selectAll(utilisateur.getId().intValue());
			Main.LOG.info("Vous avez " + String.valueOf(listeCpts.size()) + " comptes");
			Integer[] deuxId = new Integer[2];
			Iterator<CompteEntity> iter = listeCpts.iterator();
			int id = 0;
			while (iter.hasNext() && id < deuxId.length) {
				CompteEntity compteEntity = iter.next();
				deuxId[id] = compteEntity.getId();
				id++;
			}

			IOperationService serviceOp = new OperationService(em);
			serviceOp.faireVirement(utilisateur.getId().intValue(), deuxId[0].intValue(), deuxId[1].intValue(), 5d);
			Main.LOG.info("Votre virement s'est bien effectue");
		} catch (Exception e) {
			Main.LOG.fatal("Erreur", e);
			System.exit(-1);
		} finally {
			if (em != null) {
				try {
					em.close();
				} catch (Exception e) {
					Main.LOG.fatal("Erreur lors de la fermeture de l'entity manager", e);
				}
			}
		}
		Main.LOG.info("-- Fin -- ");
		System.exit(0);
	}

}
