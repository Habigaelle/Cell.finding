package com.banque.web.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.banque.entity.ICompteEntity;
import com.banque.entity.IUtilisateurEntity;
import com.banque.service.ICompteService;
import com.banque.service.IOperationService;
import com.banque.web.bean.VirementBean;
import com.banque.web.bean.validator.VirementBeanValidator;

/**
 * Controller pour le virement. <br/>
 */
@Controller
@SessionAttributes({ "utilisateur" })
public class VirementController extends AbstractController {
	private static final Logger LOG = LogManager.getLogger(VirementController.class);

	@Autowired
	private ICompteService serviceCompte;
	@Autowired
	private IOperationService serviceOperation;

	/**
	 * Constructeur de l'objet.
	 */
	public VirementController() {
		super();
	}

	/**
	 * Place la liste des comptes dans le model.
	 *
	 * @param modelMap
	 *            ou placer la liste des comptes
	 * @param pUserId
	 *            l'id du user
	 */
	private void getAndSetListeComptes(ModelMap modelMap, Integer pUserId) {
		try {
			VirementController.LOG.debug("-- Recuperation comptes pour uid=" + pUserId);
			List<ICompteEntity> listeCompte = this.serviceCompte.selectAll(pUserId.intValue());
			VirementController.LOG.debug(
					"-- Recuperation comptes pour uid=" + pUserId + " resultat=" + Integer.valueOf(listeCompte.size()));
			// On replace la liste des comptes
			modelMap.addAttribute("listeCompte", listeCompte);
		} catch (Exception e) {
			VirementController.LOG.error("Erreur:", e);
		}
	}

	/**
	 * Affiche la page des virements.
	 *
	 * @param modelMap
	 *            le model map
	 * @return vers la page virement ou vers la page index si probleme
	 */
	@RequestMapping(value = "/virement.smvc", method = RequestMethod.GET)
	public String showVirement(ModelMap modelMap) {
		VirementController.LOG.debug("--> Passage dans showVirement");
		VirementBean virementBean = new VirementBean();
		modelMap.addAttribute("virementBean", virementBean);
		// c'est l'annotation qui fait le lien avec la session
		IUtilisateurEntity utilisateur = (IUtilisateurEntity) modelMap.get("utilisateur");
		if (utilisateur == null) {
			VirementController.LOG.error("Erreur : utilisateur non connecte");
			return "index";
		}
		this.getAndSetListeComptes(modelMap, utilisateur.getId());
		return "comptes/virement";
	}

	/**
	 * Soumet le formulaire.
	 *
	 * @param virementBean
	 *            le bean qui represente le formulaire
	 * @param modelMap
	 *            le model map
	 * @param bindingResult
	 *            result binding
	 * @return la ou aller
	 */
	@RequestMapping(value = "/dovirement.smvc", method = RequestMethod.POST)
	public String doVirement(@ModelAttribute("virementBean") VirementBean virementBean, ModelMap modelMap,
			BindingResult bindingResult) {
		VirementController.LOG.debug("--> Passage dans doVirement");
		// c'est l'annotation qui fait le lien avec la session
		IUtilisateurEntity utilisateur = (IUtilisateurEntity) modelMap.get("utilisateur");
		if (utilisateur == null) {
			VirementController.LOG.error("Erreur : utilisateur non connecte");
			return "index";
		}

		this.getAndSetListeComptes(modelMap, utilisateur.getId());

		VirementBeanValidator validator = new VirementBeanValidator();
		validator.validate(virementBean, bindingResult);

		if (!bindingResult.hasErrors()) {
			try {
				VirementController.LOG
						.debug("-- doVirement uid=" + utilisateur.getId() + " cpSrc=" + virementBean.getCptSrcId()
								+ " cpDest=" + virementBean.getCptDstId() + " montant=" + virementBean.getMontant());
				this.serviceOperation.faireVirement(utilisateur.getId().intValue(),
						Integer.parseInt(virementBean.getCptSrcId()), Integer.parseInt(virementBean.getCptDstId()),
						Double.parseDouble(virementBean.getMontant()));
				modelMap.addAttribute("message", "virement.ok");
			} catch (Exception e) {
				String msg = e.getMessage() != null ? e.getMessage() : e.getClass().getName();
				bindingResult.reject("error.technical", new Object[] { msg }, msg);
				VirementController.LOG.error("Erreur:", e);
			}
		}
		return "comptes/virement";
	}
}
