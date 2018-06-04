package com.banque.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.impl.UtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ex.MauvaisMotdepasseException;
import com.banque.service.ex.UtilisateurInconnuException;

/**
 * Test sur la classe IAuthentificationService.
 */
public class TestUtilisateurService {
	private static final Logger LOG = LogManager.getLogger(TestUtilisateurService.class);
	private static EntityManager entityManager;

	private IAuthentificationService authentificationService;

	@BeforeClass
	public static void before() {
		System.setProperty("log4j.configurationFile", "log4j-test.properties");
		// "JPABanque" est le name qui se trouve dans META-INF/persistence.xml
		TestUtilisateurService.entityManager = Persistence.createEntityManagerFactory("JPABanque")
				.createEntityManager();
	}

	/**
	 * Ferme l'entity manager.
	 */
	@AfterClass
	public static void after() {
		TestUtilisateurService.entityManager.close();
	}

	/**
	 * Initialisation de l'objet a tester.
	 *
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	@Before
	public void init() {
		this.authentificationService = new AuthentificationService(TestUtilisateurService.entityManager);
	}

	/**
	 * Test
	 */
	@Test
	public void testAuthentifierOk() {
		final String login = "df";
		final String pwd = "df";
		UtilisateurEntity user = null;
		try {
			user = this.authentificationService.authentifier(login, pwd);
		} catch (Exception e) {
			TestUtilisateurService.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", user);
		Assert.assertEquals("Le login de l'utilisateur est " + login, user.getLogin(), login);
	}

	/**
	 * Test
	 *
	 * @throws Exception
	 *             UtilisateurInconnuException attendue
	 */
	@Test(expected = UtilisateurInconnuException.class)
	public void testAuthentifierKo1() throws Exception {
		final String login = "dfd";
		final String pwd = "df";
		this.authentificationService.authentifier(login, pwd);
	}

	/**
	 * Test
	 *
	 * @throws Exception
	 *             MauvaisMotdepasseException attendue
	 */
	@Test(expected = MauvaisMotdepasseException.class)
	public void testAuthentifierKo2() throws Exception {
		final String login = "df";
		final String pwd = "dfd";
		this.authentificationService.authentifier(login, pwd);
	}

}
