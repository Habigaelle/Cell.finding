package com.banque.web.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.banque.entity.IUtilisateurEntity;

/**
 * Controller qui va afficher le menu. <br/>
 */
@Controller
@RequestMapping(value = "/menu.smvc")
@SessionAttributes({ "utilisateur" })
public class MenuController extends AbstractController {
	private static final Logger LOG = LogManager.getLogger(MenuController.class);

	/**
	 * Constructeur.
	 */
	public MenuController() {
		super();
	}

	/**
	 * Affiche la page menu.
	 *
	 * @param modelMap
	 *            le model map
	 * @return vers la page menu
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showMenu(ModelMap modelMap) {
		MenuController.LOG.debug("--> Passage dans showMenu");
		// c'est l'annotation qui fait le lien avec la session
		IUtilisateurEntity utilisateur = (IUtilisateurEntity) modelMap.get("utilisateur");
		if (utilisateur == null) {
			MenuController.LOG.error("Erreur : utilisateur non connecte");
			return "index";
		}
		MenuController.LOG.debug("--> showMenu uid=" + utilisateur.getId());
		return "menu";
	}
}
