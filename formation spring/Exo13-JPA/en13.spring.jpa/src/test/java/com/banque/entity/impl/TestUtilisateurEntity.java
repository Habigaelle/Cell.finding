package com.banque.entity.impl;

import java.sql.Timestamp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test sur la classe UtilisateurEntity.
 */
public class TestUtilisateurEntity {
	private static final Logger LOG = LogManager.getLogger(TestUtilisateurEntity.class);

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
		TestUtilisateurEntity.LOG.debug("--> Test - testEqualsOk");
		UtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(Boolean.TRUE);

		UtilisateurEntity unClient2 = new UtilisateurEntity();
		unClient2.setId(Integer.valueOf(5));
		unClient2.setNom("Smith");
		unClient2.setPrenom("Jhon");
		unClient2.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient2.setPassword("bonjour");
		unClient2.setSex(Boolean.TRUE);

		Assert.assertTrue("Les deux utilisateurs sont egaux", unClient1.equals(unClient2));
		Assert.assertTrue("Les deux utilisateurs sont egaux", unClient2.equals(unClient1));
		TestUtilisateurEntity.LOG.debug("<-- Test - testEqualsOk");
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo() {
		TestUtilisateurEntity.LOG.debug("--> Test - testEqualsKo");
		UtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(Boolean.TRUE);

		UtilisateurEntity unClient2 = new UtilisateurEntity();
		unClient2.setId(Integer.valueOf(15));
		unClient2.setNom("Smith");
		unClient2.setPrenom("Jhon");
		unClient2.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient2.setPassword("bonjour");
		unClient2.setSex(Boolean.TRUE);

		Assert.assertFalse("Les deux utilisateurs sont differents", unClient1.equals(unClient2));
		Assert.assertFalse("Les deux utilisateurs sont differents", unClient2.equals(unClient1));
		TestUtilisateurEntity.LOG.debug("<-- Test - testEqualsKo");
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo1() {
		TestUtilisateurEntity.LOG.debug("--> Test - testEqualsKo1");
		UtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(Boolean.TRUE);

		OperationEntity unObj = new OperationEntity(Integer.valueOf(5));

		Assert.assertFalse("Un utilisateur n'est pas une operation", unClient1.equals(unObj));
		Assert.assertFalse("Une operation n'est pas un utilisateur", unObj.equals(unClient1));
		TestUtilisateurEntity.LOG.debug("<-- Test - testEqualsKo1");
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo2() {
		TestUtilisateurEntity.LOG.debug("--> Test - testEqualsKo2");
		UtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(Boolean.TRUE);

		CompteEntity unObj = new CompteEntity(Integer.valueOf(5));

		Assert.assertFalse("Un utilisateur n'est pas un compte", unClient1.equals(unObj));
		Assert.assertFalse("Un compte n'est pas un utilisateur", unObj.equals(unClient1));
		TestUtilisateurEntity.LOG.debug("<-- Test - testEqualsKo2");
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeOk() {
		TestUtilisateurEntity.LOG.debug("--> Test - testHashcodeOk");
		UtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(Boolean.TRUE);

		UtilisateurEntity unClient2 = new UtilisateurEntity();
		unClient2.setId(Integer.valueOf(5));
		unClient2.setNom("Smith");
		unClient2.setPrenom("Jhon");
		unClient2.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient2.setPassword("bonjour");
		unClient2.setSex(Boolean.TRUE);

		Assert.assertEquals("Les deux utilisateurs ont le meme hashcode", unClient1.hashCode(), unClient2.hashCode());
		TestUtilisateurEntity.LOG.debug("<-- Test - testHashcodeOk");
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo() {
		TestUtilisateurEntity.LOG.debug("--> Test - testHashcodeKo");
		UtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(Boolean.TRUE);

		UtilisateurEntity unClient2 = new UtilisateurEntity();
		unClient2.setId(Integer.valueOf(15));
		unClient2.setNom("Smith");
		unClient2.setPrenom("Jhon");
		unClient2.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient2.setPassword("bonjour");
		unClient2.setSex(Boolean.TRUE);

		Assert.assertNotEquals("Les deux utilisateurs n'ont pas le meme hashcode", unClient1.hashCode(),
				unClient2.hashCode());
		TestUtilisateurEntity.LOG.debug("<-- Test - testHashcodeKo");
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo1() {
		TestUtilisateurEntity.LOG.debug("--> Test - testHashcodeKo1");
		UtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(Boolean.TRUE);

		OperationEntity unObj = new OperationEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode d'utilisateur ne doit pas etre identique a celui d'une operation",
				unClient1.hashCode(), unObj.hashCode());
		TestUtilisateurEntity.LOG.debug("<-- Test - testHashcodeKo1");
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo2() {
		TestUtilisateurEntity.LOG.debug("--> Test - testHashcodeKo2");
		UtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(Boolean.TRUE);

		CompteEntity unObj = new CompteEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode d'utilisateur ne doit pas etre identique a celui d'un compte",
				unClient1.hashCode(), unObj.hashCode());
		TestUtilisateurEntity.LOG.debug("<-- Test - testHashcodeKo2");
	}
}
