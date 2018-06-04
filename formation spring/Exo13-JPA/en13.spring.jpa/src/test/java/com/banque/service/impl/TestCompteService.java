package com.banque.service.impl;

import java.util.List;

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
import com.banque.entity.impl.CompteEntity;
import com.banque.service.ICompteService;
import com.banque.service.ex.AucunDroitException;
import com.banque.service.ex.EntityIntrouvableException;

/**
 * Test sur la classe CompteService.
 */
public class TestCompteService {
	private static final Logger LOG = LogManager.getLogger(TestCompteService.class);
	private static EntityManager entityManager;

	private ICompteService compteService;

	@BeforeClass
	public static void before() {
		System.setProperty("log4j.configurationFile", "log4j-test.properties");
		// "JPABanque" est le name qui se trouve dans META-INF/persistence.xml
		TestCompteService.entityManager = Persistence.createEntityManagerFactory("JPABanque").createEntityManager();
	}

	/**
	 * Ferme l'entity manager.
	 */
	@AfterClass
	public static void after() {
		TestCompteService.entityManager.close();
	}

	/**
	 * Initialisation de l'objet a tester.
	 *
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	@Before
	public void init() {
		this.compteService = new CompteService(TestCompteService.entityManager);
	}

	/**
	 * Test
	 */
	@Test
	public void selectOk() {
		TestCompteService.LOG.debug("--> Test - selectOk");
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		CompteEntity cpt = null;
		try {
			cpt = this.compteService.select(unUtilisateurId, unCompteId);
		} catch (Exception e) {
			TestCompteService.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le compte ne doit pas etre null", cpt);
		Assert.assertEquals("L'id du compte doit etre " + unCompteId, cpt.getId().intValue(), unCompteId);
		TestCompteService.LOG.debug("<-- Test - selectOk");
	}

	/**
	 * Test
	 *
	 * @throws Exception
	 *             AucunDroitException attendue
	 */
	@Test(expected = AucunDroitException.class)
	public void selectKo1() throws Exception {
		TestCompteService.LOG.debug("--> Test - selectOk");
		// il n'y a pas d'utilisateur 100
		final int unUtilisateurId = 100;
		// il y a un compte 12
		final int unCompteId = 12;
		this.compteService.select(unUtilisateurId, unCompteId);
		TestCompteService.LOG.debug("<-- Test - selectKo1");
	}

	/**
	 * Test
	 *
	 * @throws Exception
	 *             EntityIntrouvableException attendue
	 */
	@Test(expected = EntityIntrouvableException.class)
	public void selectKo2() throws Exception {
		TestCompteService.LOG.debug("--> Test - selectOk");
		// il y a un utilisateur 1
		final int unUtilisateurId = 1;
		// il n'y a pas de compte 12000
		final int unCompteId = 12000;
		this.compteService.select(unUtilisateurId, unCompteId);
		TestCompteService.LOG.debug("<-- Test - selectKo2");
	}

	/**
	 * Test
	 */
	@Test
	public void selectAllOk() {
		TestCompteService.LOG.debug("--> Test - selectOk");
		// il y a un utilisateur 1
		final int unUtilisateurId = 1;
		List<CompteEntity> liste = null;
		try {
			liste = this.compteService.selectAll(unUtilisateurId);
		} catch (Exception e) {
			TestCompteService.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		for (CompteEntity compteEntity : liste) {
			Assert.assertNotNull("Le compte ne doit pas etre null", compteEntity);
			Assert.assertEquals("Le compte doit avoir un user id = " + unUtilisateurId,
					compteEntity.getUtilisateur().getId().intValue(), unUtilisateurId);
		}
		TestCompteService.LOG.debug("<-- Test - selectAllOk");
	}

}
