package com.banque.entity.impl;

import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test sur la classe CompteEntity.
 */
public class TestCompteEntity {
	private static final Logger LOG = LogManager.getLogger(TestCompteEntity.class);

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
		TestCompteEntity.LOG.debug("--> Test - testEqualsOk");
		CompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(BigDecimal.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(BigDecimal.valueOf(5000D));
		unCpt1.setTaux(BigDecimal.valueOf(0.01D));
		unCpt1.setUtilisateur(new UtilisateurEntity());

		CompteEntity unCpt2 = new CompteEntity();
		unCpt2.setId(Integer.valueOf(5));
		unCpt2.setDecouvert(BigDecimal.valueOf(0D));
		unCpt2.setLibelle("Cpt 01");
		unCpt2.setSolde(BigDecimal.valueOf(5000D));
		unCpt2.setTaux(BigDecimal.valueOf(0.01D));
		unCpt2.setUtilisateur(new UtilisateurEntity());

		Assert.assertTrue("Les deux comptes sont egaux", unCpt1.equals(unCpt2));
		Assert.assertTrue("Les deux comptes sont egaux", unCpt2.equals(unCpt1));
		TestCompteEntity.LOG.debug("<-- Test - testEqualsOk");
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo() {
		TestCompteEntity.LOG.debug("--> Test - testEqualsKo");
		CompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(BigDecimal.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(BigDecimal.valueOf(5000D));
		unCpt1.setTaux(BigDecimal.valueOf(0.01D));
		unCpt1.setUtilisateur(new UtilisateurEntity());

		CompteEntity unCpt2 = new CompteEntity();
		unCpt2.setId(Integer.valueOf(15));
		unCpt2.setDecouvert(BigDecimal.valueOf(0D));
		unCpt2.setLibelle("Cpt 01");
		unCpt2.setSolde(BigDecimal.valueOf(5000D));
		unCpt2.setTaux(BigDecimal.valueOf(0.01D));
		unCpt2.setUtilisateur(new UtilisateurEntity());

		Assert.assertFalse("Les deux comptes sont differents", unCpt1.equals(unCpt2));
		Assert.assertFalse("Les deux comptes sont differents", unCpt2.equals(unCpt1));
		TestCompteEntity.LOG.debug("<-- Test - testEqualsKo");
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo1() {
		TestCompteEntity.LOG.debug("--> Test - testEqualsKo1");
		CompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(BigDecimal.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(BigDecimal.valueOf(5000D));
		unCpt1.setTaux(BigDecimal.valueOf(0.01D));
		unCpt1.setUtilisateur(new UtilisateurEntity());

		UtilisateurEntity unObj = new UtilisateurEntity(Integer.valueOf(5));

		Assert.assertFalse("Un compte n'est pas un utilisateur", unCpt1.equals(unObj));
		Assert.assertFalse("Un utilisateur n'est pas un compte", unObj.equals(unCpt1));
		TestCompteEntity.LOG.debug("<-- Test - testEqualsKo1");
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo2() {
		TestCompteEntity.LOG.debug("--> Test - testEqualsKo2");
		CompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(BigDecimal.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(BigDecimal.valueOf(5000D));
		unCpt1.setTaux(BigDecimal.valueOf(0.01D));
		unCpt1.setUtilisateur(new UtilisateurEntity());

		OperationEntity unObj = new OperationEntity(Integer.valueOf(5));

		Assert.assertFalse("Un compte n'est pas une operation", unCpt1.equals(unObj));
		Assert.assertFalse("Une operation n'est pas un compte", unObj.equals(unCpt1));
		TestCompteEntity.LOG.debug("<-- Test - testEqualsKo2");
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeOk() {
		TestCompteEntity.LOG.debug("--> Test - testHashcodeOk");
		CompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(BigDecimal.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(BigDecimal.valueOf(5000D));
		unCpt1.setTaux(BigDecimal.valueOf(0.01D));
		unCpt1.setUtilisateur(new UtilisateurEntity());

		CompteEntity unCpt2 = new CompteEntity();
		unCpt2.setId(Integer.valueOf(5));
		unCpt2.setDecouvert(BigDecimal.valueOf(0D));
		unCpt2.setLibelle("Cpt 01");
		unCpt2.setSolde(BigDecimal.valueOf(5000D));
		unCpt2.setTaux(BigDecimal.valueOf(0.01D));
		unCpt2.setUtilisateur(new UtilisateurEntity());

		Assert.assertEquals("Les deux comptes ont le meme hashcode", unCpt1.hashCode(), unCpt2.hashCode());
		TestCompteEntity.LOG.debug("<-- Test - testHashcodeOk");
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo() {
		TestCompteEntity.LOG.debug("--> Test - testHashcodeKo");
		CompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(BigDecimal.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(BigDecimal.valueOf(5000D));
		unCpt1.setTaux(BigDecimal.valueOf(0.01D));
		unCpt1.setUtilisateur(new UtilisateurEntity());

		CompteEntity unCpt2 = new CompteEntity();
		unCpt2.setId(Integer.valueOf(15));
		unCpt2.setDecouvert(BigDecimal.valueOf(0D));
		unCpt2.setLibelle("Cpt 01");
		unCpt2.setSolde(BigDecimal.valueOf(5000D));
		unCpt2.setTaux(BigDecimal.valueOf(0.01D));
		unCpt2.setUtilisateur(new UtilisateurEntity());

		Assert.assertNotEquals("Les deux comptes n'ont pas le meme hashcode", unCpt1.hashCode(), unCpt2.hashCode());
		TestCompteEntity.LOG.debug("<-- Test - testHashcodeKo");
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo1() {
		TestCompteEntity.LOG.debug("--> Test - testHashcodeKo1");
		CompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(BigDecimal.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(BigDecimal.valueOf(5000D));
		unCpt1.setTaux(BigDecimal.valueOf(0.01D));
		unCpt1.setUtilisateur(new UtilisateurEntity());

		OperationEntity unObj = new OperationEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode de compte ne doit pas etre identique a celui d'une operation",
				unCpt1.hashCode(), unObj.hashCode());
		TestCompteEntity.LOG.debug("<-- Test - testHashcodeKo1");
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo2() {
		TestCompteEntity.LOG.debug("--> Test - testHashcodeKo2");
		CompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(BigDecimal.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(BigDecimal.valueOf(5000D));
		unCpt1.setTaux(BigDecimal.valueOf(0.01D));
		unCpt1.setUtilisateur(new UtilisateurEntity());

		UtilisateurEntity unObj = new UtilisateurEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode d'utilisateur ne doit pas etre identique a celui d'un compte",
				unCpt1.hashCode(), unObj.hashCode());
		TestCompteEntity.LOG.debug("<-- Test - testHashcodeKo2");
	}

	@Test
	@Ignore
	public void testJaxBCreate() {
		TestCompteEntity.LOG.debug("--> Test - testJaxBCreate");
		CompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(BigDecimal.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(BigDecimal.valueOf(5000D));
		unCpt1.setTaux(BigDecimal.valueOf(0.01D));
		unCpt1.setUtilisateur(new UtilisateurEntity());

		Writer writer = null;
		String asJax = null;
		try {
			JAXBContext context = JAXBContext.newInstance(CompteEntity.class, UtilisateurEntity.class,
					OperationEntity.class);
			Marshaller m = context.createMarshaller();
			writer = new StringWriter();
			m.marshal(unCpt1, writer);
			asJax = writer.toString();
		} catch (Exception e) {
			TestCompteEntity.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		TestCompteEntity.LOG.debug("Obj en XML=" + asJax);
		Assert.assertNotNull("La string ne doit pas etre null", asJax);
		TestCompteEntity.LOG.debug("<-- Test - testJaxBCreate");
	}
}
