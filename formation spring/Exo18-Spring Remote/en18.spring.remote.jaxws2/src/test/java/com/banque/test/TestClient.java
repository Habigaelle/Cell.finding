package com.banque.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Exemple de main client pour nos services distants SOAP. <br/>
 *
 * On se place ici comme un test JUnit pour pouvoir faire un usage simple de la
 * dependance vers org.glassfish.main.extras. <br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(name = "applicationContext", locations = { "classpath*:spring/spring-client.xml",
		"classpath*:spring/test-context.xml" })
public final class TestClient {
	/** Main log. */
	private static final Logger LOG = LogManager.getLogger(TestClient.class);

	/**
	 * Initialisation du log. <br/>
	 * Appele au demarrage de tous les tests.
	 */
	@BeforeClass
	public static void initLog() {
		System.setProperty("log4j.configurationFile", "log4j-test.properties");
		TestClient.LOG.warn("Ne pas oublier de demarrer le Serveur J2EE avant.");
	}

	/**
	 * Exemple de fonctionnement.
	 */
	@Test
	public void testClient() {
		TestClient.LOG.info("-- Debut Client -- ");
		TestClient.LOG.info("-- Fin Client-- ");
	}

}
