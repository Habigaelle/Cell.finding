package com.banque.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Factory hibernate.
 */
public final class HibernateSessionFactory {

	private static final Logger LOG = LogManager.getLogger(HibernateSessionFactory.class);

	private static SessionFactory sessionFactory;

	/**
	 * Constructeur de l'objet.
	 */
	private HibernateSessionFactory() {
		super();
	}

	/**
	 * Recupere la session factory.
	 *
	 * @return la session factory.
	 */
	public static synchronized SessionFactory getInstance() {
		if (HibernateSessionFactory.sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				configuration.configure("hibernate/hibernate.cfg.xml");
				ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).buildServiceRegistry();
				HibernateSessionFactory.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (HibernateException e) {
				HibernateSessionFactory.LOG.fatal("Impossible d'initialiser Hibernate", e);
			}
		}
		return HibernateSessionFactory.sessionFactory;
	}

}
