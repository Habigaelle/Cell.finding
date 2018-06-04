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
import com.banque.entity.impl.OperationEntity;
import com.banque.service.IOperationService;

/**
 * Test sur la classe IOperationService.
 */
public class TestOperationService {
	private static final Logger LOG = LogManager.getLogger(TestOperationService.class);
	private static EntityManager entityManager;

	private IOperationService operationService;

	@BeforeClass
	public static void before() {
		System.setProperty("log4j.configurationFile", "log4j-test.properties");
		// "JPABanque" est le name qui se trouve dans META-INF/persistence.xml
		TestOperationService.entityManager = Persistence.createEntityManagerFactory("JPABanque").createEntityManager();
	}

	/**
	 * Ferme l'entity manager.
	 */
	@AfterClass
	public static void after() {
		TestOperationService.entityManager.close();
	}

	/**
	 * Initialisation de l'objet a tester.
	 *
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	@Before
	public void init() {
		this.operationService = new OperationService(TestOperationService.entityManager);
	}

	/**
	 * Test
	 */
	@Test
	public void selectOk() {
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		final int uneOperationId = 2;
		OperationEntity op = null;
		try {
			op = this.operationService.select(unUtilisateurId, unCompteId, uneOperationId);
		} catch (Exception e) {
			TestOperationService.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'operation ne doit pas etre null", op);
		Assert.assertEquals("L'id de l'operation doit etre " + uneOperationId, op.getId().intValue(), uneOperationId);
		Assert.assertEquals("L'id du compte de l'operation doit etre " + unCompteId, op.getCompte().getId().intValue(),
				unCompteId);
	}

	/**
	 * Test
	 */
	@Test
	public void selectAllOk() {
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		List<OperationEntity> liste = null;
		try {
			liste = this.operationService.selectAll(unUtilisateurId, unCompteId);
		} catch (Exception e) {
			TestOperationService.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		for (OperationEntity uneEntity : liste) {
			Assert.assertNotNull("L'operation ne doit pas etre null", uneEntity);
			Assert.assertEquals("L'operation doit avoir un compte id = " + unCompteId,
					uneEntity.getCompte().getId().intValue(), unCompteId);
		}
	}

	/**
	 * Test
	 */
	@Test
	public void selectCritereOk() {
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		List<OperationEntity> liste = null;
		try {
			liste = this.operationService.selectCritere(unUtilisateurId, unCompteId, null, null, true, false);
		} catch (Exception e) {
			TestOperationService.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		for (OperationEntity uneEntity : liste) {
			Assert.assertNotNull("L'operation ne doit pas etre null", uneEntity);
			Assert.assertEquals("L'operation doit avoir un compte id = " + unCompteId,
					uneEntity.getCompte().getId().intValue(), unCompteId);
			Assert.assertNotNull("L'operation doit avoir un montant", uneEntity.getMontant());
			Assert.assertTrue("L'operation doit etre un credit", uneEntity.getMontant().doubleValue() > 0);
		}
	}

	/**
	 * Test
	 */
	@Test
	public void faireVirementOk() {
		final int unUtilisateurId = 1;
		final int unCompteSrcId = 12;
		final int unCompteDestId = 15;
		final double unMontant = 10d;
		List<OperationEntity> liste = null;
		try {
			liste = this.operationService.faireVirement(unUtilisateurId, unCompteSrcId, unCompteDestId, unMontant);
		} catch (Exception e) {
			TestOperationService.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		Assert.assertEquals("La liste doit avoir deux elements ", liste.size(), 2);
		for (OperationEntity uneEntity : liste) {
			Assert.assertNotNull("L'operation ne doit pas etre null", uneEntity);
			Assert.assertNotNull("L'operation doit avoir un compte id non null", uneEntity.getCompte().getId());
			Assert.assertTrue("L'operation doit avoir un compte id = " + unCompteSrcId + " ou = " + unCompteDestId,
					uneEntity.getCompte().getId().intValue() == unCompteSrcId
							|| uneEntity.getCompte().getId().intValue() == unCompteDestId);
			Assert.assertNotNull("L'operation doit avoir un montant", uneEntity.getMontant());
			Assert.assertTrue("L'operation doit avoir un montant = " + unMontant,
					Math.abs(uneEntity.getMontant().doubleValue()) == unMontant);
		}
	}

}
