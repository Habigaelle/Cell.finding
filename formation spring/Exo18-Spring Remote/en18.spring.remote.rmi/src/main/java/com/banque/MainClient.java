package com.banque;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Exemple de main client pour nos services distants RMI.
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

}
