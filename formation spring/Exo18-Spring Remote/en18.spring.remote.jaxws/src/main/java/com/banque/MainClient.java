package com.banque;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Exemple de main client pour notre service distant SOAP.
 */
public final class MainClient {
	/** Main log. */
	private static final Logger LOG = LogManager.getLogger(MainClient.class);

	/**
	 * Constructeur.
	 */
	private MainClient() {
		super();
		MainClient.LOG.error("Ne pas utiliser le constructeur");
	}

	/**
	 * Exemple de fonctionnement.
	 *
	 * @param args
	 *            ne sert a rien
	 */
	public static void main(String[] args) {
		MainClient.LOG.info("-- Debut Client -- ");
		MainClient.LOG.info("-- Fin Client-- ");
		System.exit(0);
	}

}
