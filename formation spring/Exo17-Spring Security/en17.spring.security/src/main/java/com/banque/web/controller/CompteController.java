package com.banque.web.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.banque.entity.ICompteEntity;
import com.banque.entity.IUtilisateurEntity;
import com.banque.service.ICompteService;

/**
 * Controller qui liste les comptes <br/>
 */
@Controller
@RequestMapping(value = "/listeCompte.smvc")
@SessionAttributes({ "utilisateur" })
public class CompteController extends AbstractController {

	private static final Logger LOG = LogManager.getLogger(CompteController.class);

	@Autowired
	private ICompteService service;

	/**
	 * Constructeur de l'objet.
	 */
	public CompteController() {
		super();
	}

	/**
	 * S'affiche quand on appel l'URL en get. Place le bean dans le modele.
	 *
	 * @param modelMap
	 *            le model map
	 * @return la ou aller
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showCompte(ModelMap modelMap) {
		CompteController.LOG.debug("--> Passage dans showCompte");
		// c'est l'annotation qui fait le lien avec la session
		IUtilisateurEntity utilisateur = (IUtilisateurEntity) modelMap.get("utilisateur");
		if (utilisateur == null) {
			CompteController.LOG.error("Erreur : utilisateur non connecte");
			return "index";
		}
		try {
			CompteController.LOG.debug("-- showCompte pour " + utilisateur.getId());
			List<ICompteEntity> listeCompte = this.service.selectAll(utilisateur.getId().intValue());
			// Si tout va bien on sauvegarde la liste dans le model
			modelMap.addAttribute("listeCompte", listeCompte);
			CompteController.LOG.debug(
					"-- showCompte pour " + utilisateur.getId() + " trouve " + Integer.valueOf(listeCompte.size()));
		} catch (Exception e) {
			CompteController.LOG.error("Erreur:", e);
		}
		return "comptes/liste";
	}
}
