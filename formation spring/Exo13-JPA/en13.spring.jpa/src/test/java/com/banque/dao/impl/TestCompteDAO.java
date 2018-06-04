package com.banque.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.dao.ICompteDAO;
import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.impl.CompteEntity;

/**
 * Test sur la classe CompteDAO.
 */
public class TestCompteDAO {
	private static final Logger LOG = LogManager.getLogger(TestCompteDAO.class);

	private ICompteDAO compteDao;
	private IUtilisateurDAO utilisateurDao;
	private static EntityManager entityManager;

	/**
	 * Initialisation du log. <br/>
	 * Appele au demarrage de tous les tests.
	 */
	@BeforeClass
	public static void before() {
		System.setProperty("log4j.configurationFile", "log4j-test.properties");
		// "JPABanque" est le name qui se trouve dans META-INF/persistence.xml
		TestCompteDAO.entityManager = Persistence.createEntityManagerFactory("JPABanque").createEntityManager();
	}

	/**
	 * Ferme l'entity manager.
	 */
	@AfterClass
	public static void after() {
		TestCompteDAO.entityManager.close();
	}

	/**
	 * Initialisation de l'objet a tester.
	 *
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	@Before
	public void init() {
		this.compteDao = new CompteDAO();
		this.compteDao.setEntityManager(TestCompteDAO.entityManager);
		this.utilisateurDao = new UtilisateurDAO();
		this.utilisateurDao.setEntityManager(TestCompteDAO.entityManager);
	}

	/**
	 * Test sur l'insertion
	 */
	@Test
	public void insertOk() {
		CompteEntity unCpt1 = new CompteEntity();
		unCpt1.setDecouvert(BigDecimal.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(BigDecimal.valueOf(5000D));
		unCpt1.setTaux(BigDecimal.valueOf(0.01D));

		try {
			unCpt1.setUtilisateur(this.utilisateurDao.select(1));
		} catch (ExceptionDao e) {
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}

		// Sans transaction, JPA ne fera rien
		EntityTransaction tx = null;
		CompteEntity unCpt2 = null;
		try {
			tx = this.compteDao.getEntityTransaction();
			tx.begin();
			unCpt2 = this.compteDao.insert(unCpt1);
			tx.commit();
		} catch (ExceptionDao e) {
			if (tx != null) {
				tx.rollback();
			}
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt2);
		Assert.assertNotNull("Le compte doit avoir un id non null", unCpt2.getId());
		Assert.assertEquals("Les deux comptes doivent avoir le meme libelle", unCpt1.getLibelle(), unCpt2.getLibelle());
	}

	/**
	 * Test sur la mise a jour
	 */
	@Test
	public void updateOk() {
		// Avant de l'updater nous devons l'inserer
		CompteEntity unCpt1 = new CompteEntity();
		unCpt1.setDecouvert(BigDecimal.valueOf(0D));
		unCpt1.setLibelle("Cpt Test");
		unCpt1.setSolde(BigDecimal.valueOf(5000D));
		unCpt1.setTaux(BigDecimal.valueOf(0.01D));
		try {
			unCpt1.setUtilisateur(this.utilisateurDao.select(1));
		} catch (ExceptionDao e) {
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}

		// Sans transaction, JPA ne fera rien
		EntityTransaction tx = null;
		try {
			tx = this.compteDao.getEntityTransaction();
			tx.begin();
			unCpt1 = this.compteDao.insert(unCpt1);
			tx.commit();
		} catch (ExceptionDao e) {
			if (tx != null) {
				tx.rollback();
			}
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt1);
		Assert.assertNotNull("Le compte doit avoir un id non null", unCpt1.getId());

		unCpt1.setLibelle("Cpt change test");
		CompteEntity unCpt2 = null;
		try {
			tx = this.compteDao.getEntityTransaction();
			tx.begin();
			unCpt2 = this.compteDao.update(unCpt1);
			tx.commit();
		} catch (ExceptionDao e) {
			if (tx != null) {
				tx.rollback();
			}
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt2);
		Assert.assertNotNull("Le compte doit avoir un id non null", unCpt2.getId());
		Assert.assertEquals("Les deux comptes doivent avoir le meme libelle", unCpt1.getLibelle(), unCpt2.getLibelle());
	}

	/**
	 * Test la suppression
	 */
	@Test
	public void deleteOk() {
		// Avant le delete nous devons l'inserer
		CompteEntity unCpt1 = new CompteEntity();
		unCpt1.setDecouvert(BigDecimal.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(BigDecimal.valueOf(5000D));
		unCpt1.setTaux(BigDecimal.valueOf(0.01D));
		try {
			unCpt1.setUtilisateur(this.utilisateurDao.select(1));
		} catch (ExceptionDao e) {
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}

		EntityTransaction tx = null;
		try {
			tx = this.compteDao.getEntityTransaction();
			tx.begin();
			unCpt1 = this.compteDao.insert(unCpt1);
			tx.commit();
		} catch (ExceptionDao e) {
			if (tx != null) {
				tx.rollback();
			}
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt1);
		Assert.assertNotNull("Le compte doit avoir un id non null", unCpt1.getId());
		boolean resu = false;
		try {
			tx = this.compteDao.getEntityTransaction();
			tx.begin();
			resu = this.compteDao.delete(unCpt1);
			tx.commit();
		} catch (ExceptionDao e) {
			if (tx != null) {
				tx.rollback();
			}
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertTrue("Le compte a du etre supprime", resu);
	}

	/**
	 * Selectionne un compte
	 */
	@Test
	public void selectOk() {
		CompteEntity unCpt1 = null;
		try {
			unCpt1 = this.compteDao.select(12);
		} catch (ExceptionDao e) {
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt1);
		Assert.assertEquals("Le compte doit avoir un id = 12", unCpt1.getId().intValue(), 12);
	}

	/**
	 * Selectionne les comptes
	 */
	@Test
	public void selectAllOk() {
		List<CompteEntity> liste = null;
		CompteEntity unCpt1 = null;
		try {
			liste = this.compteDao.selectAll("id=12", null);
			unCpt1 = liste.get(0);
		} catch (ExceptionDao e) {
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());

		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt1);
		Assert.assertEquals("Le compte doit avoir un id = 12", unCpt1.getId().intValue(), 12);
	}

}
