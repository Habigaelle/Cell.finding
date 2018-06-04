package com.banque.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.banque.dao.ICompteDAO;
import com.banque.dao.IOperationDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.impl.OperationEntity;

/**
 * Test sur la classe OperationDAO.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(name = "applicationContext", locations = { "classpath*:spring/*-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TestOperationDAO {
	private static final Logger LOG = LogManager.getLogger(TestOperationDAO.class);

	@Autowired
	private IOperationDAO operationDao;
	@Autowired
	private ICompteDAO compteDao;

	/**
	 * Initialisation du log. <br/>
	 * Appele au demarrage de tous les tests.
	 */
	@BeforeClass
	public static void initLog() {
		System.setProperty("log4j.configurationFile", "log4j-test.properties");
	}

	/**
	 * Test
	 */
	@Test
	public void insertOk() {
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));
		try {
			uneOp1.setCompte(this.compteDao.select(12));
		} catch (ExceptionDao e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}

		OperationEntity uneOp2 = null;
		try {
			uneOp2 = this.operationDao.insert(uneOp1);
		} catch (ExceptionDao e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp2);
		Assert.assertNotNull("L'operation doit avoir un id non null", uneOp2.getId());
		Assert.assertEquals("Les deux operations doivent avoir le meme libelle", uneOp1.getLibelle(),
				uneOp2.getLibelle());
	}

	/**
	 * Test
	 */
	@Test
	public void updateOk() {
		// Avant le delete nous devons l'inserer
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1 de test");
		uneOp1.setMontant(Double.valueOf(500D));
		try {
			uneOp1.setCompte(this.compteDao.select(12));
		} catch (ExceptionDao e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}

		try {
			uneOp1 = this.operationDao.insert(uneOp1);
		} catch (ExceptionDao e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp1);
		Assert.assertNotNull("L'operation doit avoir un id non null", uneOp1.getId());

		uneOp1.setLibelle("Op1 de test bis");
		OperationEntity uneOp2 = null;
		try {
			uneOp2 = this.operationDao.update(uneOp1);
		} catch (ExceptionDao e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp2);
		Assert.assertNotNull("L'operation doit avoir un id non null", uneOp2.getId());
		Assert.assertEquals("Les deux operations doivent avoir le meme libelle", uneOp1.getLibelle(),
				uneOp2.getLibelle());
	}

	/**
	 * Test
	 */
	@Test
	public void deleteOk() {
		// Avant le delete nous devons l'inserer
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1 de test");
		uneOp1.setMontant(Double.valueOf(500D));
		try {
			uneOp1.setCompte(this.compteDao.select(12));
		} catch (ExceptionDao e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}

		try {
			uneOp1 = this.operationDao.insert(uneOp1);
		} catch (ExceptionDao e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp1);
		Assert.assertNotNull("L'operation doit avoir un id non null", uneOp1.getId());
		boolean resu = false;
		try {
			resu = this.operationDao.delete(uneOp1);
		} catch (ExceptionDao e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertTrue("L'operation a du etre supprime", resu);
	}

	/**
	 * Test
	 */
	@Test
	public void selectOk() {
		OperationEntity uneOp1 = null;
		try {
			uneOp1 = this.operationDao.select(1);
		} catch (ExceptionDao e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp1);
		Assert.assertEquals("L'operation doit avoir un id = 1", uneOp1.getId().intValue(), 1);
	}

	/**
	 * Test
	 */
	@Test
	public void selectAllOk() {
		List<OperationEntity> liste = null;
		OperationEntity uneOp1 = null;
		try {
			liste = this.operationDao.selectAll("id=1", null);
			uneOp1 = liste.get(0);
		} catch (ExceptionDao e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp1);
		Assert.assertEquals("L'operation doit avoir un id = 1", uneOp1.getId().intValue(), 1);
	}

	/**
	 * Test
	 */
	@Test
	public void selectCriteriaOk() {
		List<OperationEntity> liste = null;
		try {
			liste = this.operationDao.selectCriteria(12, null, null, null);
		} catch (ExceptionDao e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());

	}

}
