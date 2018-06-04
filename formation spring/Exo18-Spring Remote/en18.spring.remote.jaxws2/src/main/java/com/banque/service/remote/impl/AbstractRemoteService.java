package com.banque.service.remote.impl;

import org.springframework.stereotype.Controller;

/**
 * Classe parente de tous les services distants JAX-WS.
 */
@Controller
abstract class AbstractRemoteService {

	/**
	 * Constructeur de l'objet.
	 */
	protected AbstractRemoteService() {
		super();
	}
}
