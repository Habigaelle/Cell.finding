package com.banque.entity.impl;

import java.sql.Timestamp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test sur la classe OperationEntity.
 */
public class TestOperationEntity {
	private static final Logger LOG = LogManager.getLogger(TestOperationEntity.class);

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
	public void testEqualsOk() {
		TestOperationEntity.LOG.debug("--> Test - testEqualsOk");
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompte(new CompteEntity(Integer.valueOf(1)));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		OperationEntity uneOp2 = new OperationEntity();
		uneOp2.setId(Integer.valueOf(5));
		uneOp2.setCompte(new CompteEntity(Integer.valueOf(1)));
		uneOp2.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp2.setLibelle("Op1");
		uneOp2.setMontant(Double.valueOf(500D));

		Assert.assertTrue("Les deux operations sont egales", uneOp1.equals(uneOp2));
		Assert.assertTrue("Les deux operations sont egales", uneOp2.equals(uneOp1));
		TestOperationEntity.LOG.debug("<-- Test - testEqualsOk");
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo() {
		TestOperationEntity.LOG.debug("--> Test - testEqualsKo");
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompte(new CompteEntity(Integer.valueOf(1)));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		OperationEntity uneOp2 = new OperationEntity();
		uneOp2.setId(Integer.valueOf(15));
		uneOp2.setCompte(new CompteEntity(Integer.valueOf(1)));
		uneOp2.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp2.setLibelle("Op1");
		uneOp2.setMontant(Double.valueOf(500D));

		Assert.assertFalse("Les deux operations sont differents", uneOp1.equals(uneOp2));
		Assert.assertFalse("Les deux operations sont differents", uneOp2.equals(uneOp1));
		TestOperationEntity.LOG.debug("<-- Test - testEqualsKo");
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo1() {
		TestOperationEntity.LOG.debug("--> Test - testEqualsKo1");
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompte(new CompteEntity(Integer.valueOf(1)));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		UtilisateurEntity unObj = new UtilisateurEntity(Integer.valueOf(5));

		Assert.assertFalse("Une operation n'est pas un utilisateur", uneOp1.equals(unObj));
		Assert.assertFalse("Un utilisateur n'est pas une operation", unObj.equals(uneOp1));
		TestOperationEntity.LOG.debug("<-- Test - testEqualsKo1");
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo2() {
		TestOperationEntity.LOG.debug("--> Test - testEqualsKo2");
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompte(new CompteEntity(Integer.valueOf(1)));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		CompteEntity unObj = new CompteEntity(Integer.valueOf(5));

		Assert.assertFalse("Une operation n'est pas un compte", uneOp1.equals(unObj));
		Assert.assertFalse("Un compte n'est pas une operation", unObj.equals(uneOp1));
		TestOperationEntity.LOG.debug("<-- Test - testEqualsKo2");
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeOk() {
		TestOperationEntity.LOG.debug("--> Test - testHashcodeOk");
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompte(new CompteEntity(Integer.valueOf(1)));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		OperationEntity uneOp2 = new OperationEntity();
		uneOp2.setId(Integer.valueOf(5));
		uneOp2.setCompte(new CompteEntity(Integer.valueOf(1)));
		uneOp2.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp2.setLibelle("Op1");
		uneOp2.setMontant(Double.valueOf(500D));

		Assert.assertEquals("Les deux operations ont le meme hashcode", uneOp1.hashCode(), uneOp2.hashCode());
		TestOperationEntity.LOG.debug("<-- Test - testHashcodeOk");
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo() {
		TestOperationEntity.LOG.debug("--> Test - testHashcodeKo");
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompte(new CompteEntity(Integer.valueOf(1)));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		OperationEntity uneOp2 = new OperationEntity();
		uneOp2.setId(Integer.valueOf(15));
		uneOp2.setCompte(new CompteEntity(Integer.valueOf(1)));
		uneOp2.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp2.setLibelle("Op1");
		uneOp2.setMontant(Double.valueOf(500D));

		Assert.assertNotEquals("Les deux operations n'ont pas le meme hashcode", uneOp1.hashCode(), uneOp2.hashCode());
		TestOperationEntity.LOG.debug("<-- Test - testHashcodeKo");
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo1() {
		TestOperationEntity.LOG.debug("--> Test - testHashcodeKo1");
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompte(new CompteEntity(Integer.valueOf(1)));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		CompteEntity unObj = new CompteEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode de operation ne doit pas etre identique a celui d'un compte",
				uneOp1.hashCode(), unObj.hashCode());
		TestOperationEntity.LOG.debug("<-- Test - testHashcodeKo1");
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo2() {
		TestOperationEntity.LOG.debug("--> Test - testHashcodeKo2");
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompte(new CompteEntity(Integer.valueOf(1)));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		UtilisateurEntity unObj = new UtilisateurEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode d'utilisateur ne doit pas etre identique a celui d'une operation",
				uneOp1.hashCode(), unObj.hashCode());
		TestOperationEntity.LOG.debug("<-- Test - testHashcodeKo2");
	}
}
