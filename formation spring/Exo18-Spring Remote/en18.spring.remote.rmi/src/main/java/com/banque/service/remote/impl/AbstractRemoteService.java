package com.banque.service.remote.impl;

import java.rmi.Remote;

import org.springframework.stereotype.Controller;

/**
 * Classe parente de tous les services distants RMI.
 */
@Controller
abstract class AbstractRemoteService implements Remote {

	/**
	 * Constructeur de l'objet.
	 */
	protected AbstractRemoteService() {
		super();
	}
}
