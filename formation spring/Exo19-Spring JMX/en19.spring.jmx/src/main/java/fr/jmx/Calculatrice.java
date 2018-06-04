package fr.jmx;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Classe representant une calculatrice. <br/>
 */
public class Calculatrice {
	private static final Logger LOG = LogManager.getLogger(Calculatrice.class);

	/**
	 * Constructeur.
	 */
	public Calculatrice() {
		super();
		Calculatrice.LOG.trace("Constructeur");
	}
}
