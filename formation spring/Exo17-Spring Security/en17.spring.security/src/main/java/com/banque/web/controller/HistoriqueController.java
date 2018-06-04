package com.banque.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.banque.entity.IOperationEntity;
import com.banque.entity.IUtilisateurEntity;
import com.banque.service.IOperationService;
import com.banque.web.bean.HistoriqueBean;
import com.banque.web.bean.validator.HistoriqueBeanValidator;

/**
 * Controller pour l'historique <br/>
 */
@Controller
@SessionAttributes({ "utilisateur" })
public class HistoriqueController extends AbstractController {
	private static final Logger LOG = LogManager.getLogger(HistoriqueController.class);

	@Autowired
	private IOperationService service;

	/**
	 * Constructeur de l'objet.
	 */
	public HistoriqueController() {
		super();
	}

	/**
	 * Soumet le formulaire.
	 *
	 * @param historiqueBean
	 *            le bean qui represente le formulaire
	 * @param modelMap
	 *            le model map
	 * @param bindingResult
	 *            result binding
	 * @return la ou aller
	 */
	@RequestMapping(value = "/dohistorique.smvc", method = RequestMethod.POST)
	public String doHistorique(@ModelAttribute("historiqueBean") HistoriqueBean historiqueBean, ModelMap modelMap,
			BindingResult bindingResult) {
		HistoriqueController.LOG.debug("--> Passage dans dohistorique");
		// c'est l'annotation qui fait le lien avec la session
		IUtilisateurEntity utilisateur = (IUtilisateurEntity) modelMap.get("utilisateur");
		if (utilisateur == null) {
			HistoriqueController.LOG.error("Erreur : utilisateur non connecte");
			return "index";
		}

		HistoriqueBeanValidator validator = new HistoriqueBeanValidator();
		validator.validate(historiqueBean, bindingResult);
		if (!bindingResult.hasErrors()) {
			try {
				HistoriqueController.LOG.debug("-- dohistorique pour uid=" + utilisateur.getId() + " et cpid="
						+ historiqueBean.getCptId() + " avec dateDebut=" + historiqueBean.getDateDebut() + " dateFin="
						+ historiqueBean.getDateFin() + " credit=" + historiqueBean.getCredit() + " debit="
						+ historiqueBean.getDebit());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				sdf.setLenient(false);
				Date dateDebut = null;
				if (historiqueBean.getDateDebut() != null) {
					dateDebut = sdf.parse(historiqueBean.getDateDebut());
				}
				Date dateFin = null;
				if (historiqueBean.getDateFin() != null) {
					dateFin = sdf.parse(historiqueBean.getDateFin());
				}
				if (dateDebut != null && dateFin == null) {
					dateFin = new Date(System.currentTimeMillis());
				}
				if (dateDebut == null && dateFin != null) {
					dateDebut = new Date(System.currentTimeMillis());
				}
				boolean credit = historiqueBean.getCredit() != null && historiqueBean.getCredit().booleanValue();
				boolean debit = historiqueBean.getDebit() != null && historiqueBean.getDebit().booleanValue();
				List<IOperationEntity> listeOperations = this.service.selectCritere(utilisateur.getId().intValue(),
						historiqueBean.getCptId().intValue(), dateDebut, dateFin, credit, debit);
				HistoriqueController.LOG.debug("-- dohistorique pour uid=" + utilisateur.getId() + " et cpid="
						+ historiqueBean.getCptId() + " resultat=" + Integer.valueOf(listeOperations.size()));
				modelMap.addAttribute("listeOperations", listeOperations);
			} catch (Exception e) {
				String msg = e.getMessage() != null ? e.getMessage() : e.getClass().getName();
				bindingResult.reject("error.technical", new Object[] { msg }, msg);
				HistoriqueController.LOG.error("Erreur:", e);
			}
		}
		return "comptes/historique";
	}
}
