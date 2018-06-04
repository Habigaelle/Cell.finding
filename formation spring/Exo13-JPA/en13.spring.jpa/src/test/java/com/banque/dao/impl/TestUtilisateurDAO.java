package com.banque.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.impl.UtilisateurEntity;

/**
 * Test sur la classe UtilisateurDAO.
 */
public class TestUtilisateurDAO {
	private static final Logger LOG = LogManager.getLogger(TestCompteDAO.class);
	private static EntityManager entityManager;

	private IUtilisateurDAO utilisateurDao;

	/**
	 * Initialisation du log. <br/>
	 * Appele au demarrage de tous les tests.
	 */
	@BeforeClass
	public static void before() {
		System.setProperty("log4j.configurationFile", "log4j-test.properties");
		// "JPABanque" est le name qui se trouve dans META-INF/persistence.xml
		TestUtilisateurDAO.entityManager = Persistence.createEntityManagerFactory("JPABanque").createEntityManager();
	}

	/**
	 * Ferme l'entity manager.
	 */
	@AfterClass
	public static void after() {
		TestUtilisateurDAO.entityManager.close();
	}

	/**
	 * Initialisation de l'objet a tester.
	 *
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	@Before
	public void init() throws ExceptionDao {
		this.utilisateurDao = new UtilisateurDAO();
		this.utilisateurDao.setEntityManager(TestUtilisateurDAO.entityManager);
	}

	/**
	 * Test
	 */
	@Test
	public void insertOk() {
		UtilisateurEntity unUt1 = new UtilisateurEntity();
		unUt1.setLogin("login");
		unUt1.setNom("Smith");
		unUt1.setPrenom("Jhon");
		unUt1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unUt1.setPassword("bonjour");
		unUt1.setSex(Boolean.TRUE);
		unUt1.setAdresse("Quelque part dans le test");
		unUt1.setCodePostal(Integer.valueOf(78000));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date d = null;
		try {
			d = sdf.parse("1988/01/01");
		} catch (Exception e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		unUt1.setDateDeNaissance(new java.sql.Date(d.getTime()));
		unUt1.setTelephone("0148759678");

		UtilisateurEntity unUt2 = null;
		EntityTransaction tx = null;
		try {
			tx = this.utilisateurDao.getEntityTransaction();
			tx.begin();
			unUt2 = this.utilisateurDao.insert(unUt1);
			tx.commit();
		} catch (ExceptionDao e) {
			if (tx != null) {
				tx.rollback();
			}
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt2);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt2.getId());
		Assert.assertEquals("Les deux utilisateurs doivent avoir le meme libelle", unUt1.getNom(), unUt2.getNom());
	}

	/**
	 * Test
	 */
	@Test
	public void updateOk() {
		// Avant le delete nous devons l'inserer
		UtilisateurEntity unUt1 = new UtilisateurEntity();
		unUt1.setLogin("login");
		unUt1.setNom("Nom test");
		unUt1.setPrenom("Prenom test");
		unUt1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unUt1.setPassword("bonjour");
		unUt1.setSex(Boolean.TRUE);

		EntityTransaction tx = null;
		try {
			tx = this.utilisateurDao.getEntityTransaction();
			tx.begin();
			unUt1 = this.utilisateurDao.insert(unUt1);
			tx.commit();
		} catch (ExceptionDao e) {
			if (tx != null) {
				tx.rollback();
			}
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());

		unUt1.setNom("Test is back");
		UtilisateurEntity unUt2 = null;
		try {
			tx = this.utilisateurDao.getEntityTransaction();
			tx.begin();
			unUt2 = this.utilisateurDao.update(unUt1);
			tx.commit();
		} catch (ExceptionDao e) {
			if (tx != null) {
				tx.rollback();
			}
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt2);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt2.getId());
		Assert.assertEquals("Les deux utilisateurs doivent avoir le meme id", unUt1.getId(), unUt2.getId());
		Assert.assertEquals("Les deux utilisateurs doivent avoir le meme libelle", unUt1.getNom(), unUt2.getNom());
	}

	/**
	 * Test
	 */
	@Test
	public void deleteOk() {
		// Avant le delete nous devons l'inserer
		UtilisateurEntity unUt1 = new UtilisateurEntity();
		unUt1.setLogin("login");
		unUt1.setNom("Nom test");
		unUt1.setPrenom("Prenom test");
		unUt1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unUt1.setPassword("bonjour");
		unUt1.setSex(Boolean.FALSE);

		EntityTransaction tx = null;
		try {
			tx = this.utilisateurDao.getEntityTransaction();
			tx.begin();
			unUt1 = this.utilisateurDao.insert(unUt1);
			tx.commit();
		} catch (ExceptionDao e) {
			if (tx != null) {
				tx.rollback();
			}
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());

		boolean resu = false;
		try {
			tx = this.utilisateurDao.getEntityTransaction();
			tx.begin();
			resu = this.utilisateurDao.delete(unUt1);
			tx.commit();
		} catch (ExceptionDao e) {
			if (tx != null) {
				tx.rollback();
			}
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertTrue("L'utilisateur a du etre supprime", resu);
	}

	/**
	 * Test
	 */
	@Test
	public void selectOk() {
		UtilisateurEntity unUt1 = null;
		try {
			// le login df existe
			unUt1 = this.utilisateurDao.selectLogin("df");
		} catch (ExceptionDao e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());
		Assert.assertEquals("L'utilisateur doit avoir un login = df", unUt1.getLogin(), "df");
	}

	/**
	 * Test
	 */
	@Test
	public void selectAllOk() {
		List<UtilisateurEntity> liste = null;
		UtilisateurEntity unUt1 = null;
		try {
			liste = this.utilisateurDao.selectAll("nom='Fargis'", null);
			unUt1 = liste.get(0);
		} catch (ExceptionDao e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());

		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());
		Assert.assertEquals("L'utilisateur doit avoir un login = Fargis", unUt1.getNom(), "Fargis");
	}
}
