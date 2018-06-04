package com.banque.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.banque.entity.impl.UtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ex.MauvaisMotdepasseException;
import com.banque.service.ex.UtilisateurInconnuException;

/**
 * Test sur la classe IAuthentificationService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(name = "applicationContext", locations = { "classpath*:spring/*-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TestUtilisateurService {
	private static final Logger LOG = LogManager.getLogger(TestUtilisateurService.class);

	@Autowired
	private IAuthentificationService authentificationService;

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
