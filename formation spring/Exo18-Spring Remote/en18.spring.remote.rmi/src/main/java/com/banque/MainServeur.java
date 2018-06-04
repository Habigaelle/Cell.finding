package com.banque;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Exemple de serveur pour notre service distant RMI.
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
}
