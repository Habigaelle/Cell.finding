package com.banque.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Exemple de service
 */
@Service
public class MonService implements IMonService {

	private static final Logger LOG = LogManager.getLogger(MonService.class);

	@Override
	public void faireAdmin() {
		MonService.LOG.debug("---> Ma méthode pour admin");
	}

	@Override
	public void faireUser() {
		MonService.LOG.debug("---> Ma méthode pour user");
	}

}
