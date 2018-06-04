package fr;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Demarrage du client JMX.
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
	 * Charge un fichier Spring.
	 *
	 * @param args
	 *            ne sert a rien
	 */
	public static void main(String[] args) {
		MainClient.LOG.debug("-- Debut Client -- ");
		ClassPathXmlApplicationContext appContext = null;
		try {
			// On ne charge que le fichier Spring client
			appContext = new ClassPathXmlApplicationContext("classpath*:spring/spring-client.xml");
			// Recupérer votre bean JMX
			// Réalisez une ou plusieurs opérations
		} catch (Exception e) {
			MainClient.LOG.fatal("Erreur", e);
			System.exit(-1);
		} finally {
			if (appContext != null) {
				appContext.close();
			}
		}
		MainClient.LOG.debug("-- Fin Client -- ");
		System.exit(0);
	}

}
