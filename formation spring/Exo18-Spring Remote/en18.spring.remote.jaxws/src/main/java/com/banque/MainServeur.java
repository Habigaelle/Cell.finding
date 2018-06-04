package com.banque;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Pas de serveur J2EE ici, c'est le Spring qui va monter le service JAX-WS.
 */
public final class MainServeur {
	/** Main log. */
	private static final Logger LOG = LogManager.getLogger(MainServeur.class);

	/**
	 * Constructeur.
	 */
	private MainServeur() {
		super();
		MainServeur.LOG.error("Ne pas utiliser le constructeur");
	}

	/**
	 * Exemple de fonctionnement.
	 *
	 * @param args
	 *            ne sert a rien
	 */
	public static void main(String[] args) {
		MainServeur.LOG.info("-- Debut Serveur -- ");
		MainServeur.LOG.info("-- Fin Serveur -- ");
		System.exit(0);
	}

}
