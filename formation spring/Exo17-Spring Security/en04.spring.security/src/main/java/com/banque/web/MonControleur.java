package com.banque.web;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.banque.service.IMonService;

/**
 * Exemple de controlleur.
 */
@Controller
public class MonControleur {
	private static final Logger LOG = LogManager.getLogger(MonControleur.class);

	@Autowired
	private IMonService service;

	/**
	 * Methode pour les utilisateurs.
	 *
	 * @return un message
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user", produces = MediaType.TEXT_PLAIN_VALUE)
	protected @ResponseBody String doUser() {
		MonControleur.LOG.debug("Appel au controleur, juste avant le service métier");
		this.service.faireUser();
		return "Ok user";
	}

	/**
	 * Methode pour les administrateurs.
	 *
	 * @return un message
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/admin", produces = MediaType.TEXT_PLAIN_VALUE)
	protected @ResponseBody String doAdmin() {
		MonControleur.LOG.debug("Appel au controleur, juste avant le service métier");
		this.service.faireAdmin();
		return "Ok admin";
	}

}
