package com.exo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Exemple.
 */
public final class Main {
	private static final Logger LOG = LogManager.getLogger(Main.class);

	/**
	 * Constructeur.
	 */
	private Main() {
		super();
		Main.LOG.error("Ne pas utiliser le constructeur");
	}

	/**
	 * Charge un fichier Spring.
	 *
	 * @param args
	 *            ne sert a rien
	 */
	public static void main(String[] args) {
		Main.LOG.debug("-- Debut -- ");
		ClassPathXmlApplicationContext appContext = null;
		try {
			// Chargement du fichier
			appContext = new ClassPathXmlApplicationContext("spring/mesBeans.xml");

			// Recuperation de notre instance de client
			// Client cl1 = (Client) appContext.getBean("client01");
			Client cl2 = (Client) appContext.getBean("client02");
			// Affichage
			// Main.LOG.debug(cl1.toString());
			Main.LOG.debug(cl2.toString());
		} catch (BeansException e) {
			Main.LOG.fatal("Erreur", e);
			System.exit(-1);
		} finally {
			if (appContext != null) {
				appContext.close();
			}
			// On peut aussi faire :
			// appContext.registerShutdownHook();
			// Juste apres la creation du context, de cette manière il sera
			// detruit automatiquement à la fin du programme
		}

		Main.LOG.debug("-- Fin -- ");
		System.exit(0);
	}
}