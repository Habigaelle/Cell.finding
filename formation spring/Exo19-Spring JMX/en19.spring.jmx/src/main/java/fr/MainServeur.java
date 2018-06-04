package fr;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Demarrage du serveur de bean JMX.
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
	 * Charge un fichier Spring.
	 *
	 * @param args
	 *            ne sert a rien
	 */
	public static void main(String[] args) {
		MainServeur.LOG.debug("-- Debut Serveur -- ");
		ClassPathXmlApplicationContext appContext = null;
		try {
			appContext = new ClassPathXmlApplicationContext("classpath*:spring/*-context.xml");
			MainServeur.LOG.info("Appuyez sur 'Enter' pour arrêter le serveur.");
			System.in.read();
		} catch (Exception e) {
			MainServeur.LOG.fatal("Erreur", e);
			System.exit(-1);
		} finally {
			if (appContext != null) {
				appContext.close();
			}
		}
		MainServeur.LOG.debug("-- Fin Serveur -- ");
		System.exit(0);
	}

}
