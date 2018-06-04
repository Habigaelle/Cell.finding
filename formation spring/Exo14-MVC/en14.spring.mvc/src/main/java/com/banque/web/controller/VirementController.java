package com.banque.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.banque.entity.ICompteEntity;
import com.banque.entity.IOperationEntity;
import com.banque.entity.IUtilisateurEntity;
import com.banque.entity.impl.CompteEntity;
import com.banque.entity.impl.OperationEntity;
import com.banque.service.ICompteService;
import com.banque.service.IOperationService;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.FonctionnelleException;
import com.banque.web.bean.VirementBean;

/**
 * Controller pour le virement. <br/>
 */
@WebServlet(urlPatterns = "/virement")
public class VirementController extends AbstractController {
	private static final Logger LOG = LogManager.getLogger(VirementController.class);
	private static final long serialVersionUID = 1L;

	private ICompteService serviceCompte;
	private IOperationService serviceOperation;

	/**
	 * Constructeur de l'objet.
	 */
	public VirementController() {
		super();
		// TODO A retirer, en attente d'ID
		this.serviceOperation = Mockito.mock(IOperationService.class);
		try {
			List<IOperationEntity> r = new ArrayList<IOperationEntity>();
			r.add(new OperationEntity(Integer.valueOf(1)));
			r.get(0).setDate(new Timestamp(System.currentTimeMillis()));
			r.get(0).setLibelle("Operation test 1");
			r.get(0).setMontant(Double.valueOf(200D));
			r.add(new OperationEntity(Integer.valueOf(2)));
			r.get(1).setDate(new Timestamp(System.currentTimeMillis()));
			r.get(1).setLibelle("Operation test 2");
			r.get(1).setMontant(Double.valueOf(222D));
			Mockito.when(this.serviceOperation.faireVirement(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(),
					ArgumentMatchers.anyInt(), ArgumentMatchers.anyDouble())).thenReturn(r);
			Mockito.when(this.serviceOperation.selectAll(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
					.thenReturn(r);
			Mockito.when(this.serviceOperation.selectCritere(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(),
					(java.util.Date) ArgumentMatchers.any(), (java.util.Date) ArgumentMatchers.any(),
					ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean())).thenReturn(r);
			Mockito.when(this.serviceOperation.select(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(),
					ArgumentMatchers.anyInt())).thenReturn(r.get(0));
		} catch (Exception e) {
			VirementController.LOG.warn("Erreur dans le mock", e);
		}
		// TODO A retirer, en attente d'ID
		this.serviceCompte = Mockito.mock(ICompteService.class);
		try {
			Mockito.when(this.serviceCompte.select(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
					.thenReturn(Mockito.mock(ICompteEntity.class));
			List<ICompteEntity> r = new ArrayList<ICompteEntity>();
			r.add(new CompteEntity(Integer.valueOf(1), "Test", BigDecimal.valueOf(25), null, null));
			r.add(new CompteEntity(Integer.valueOf(2), "Test 2", BigDecimal.valueOf(250), null, null));
			Mockito.when(this.serviceCompte.selectAll(ArgumentMatchers.anyInt())).thenReturn(r);
		} catch (Exception e) {
			VirementController.LOG.warn("Erreur dans le mock serviceCompte", e);
		}
	}

	/**
	 * Place la liste des comptes dans le model.
	 *
	 * @param request
	 *            ou placer la liste des comptes
	 * @param pUserId
	 *            l'id du user
	 * @throws ErreurTechniqueException
	 * @throws FonctionnelleException
	 */
	private void getAndSetListeComptes(HttpServletRequest request, Integer pUserId)
			throws FonctionnelleException, ErreurTechniqueException {
		VirementController.LOG.debug("-- Recuperation comptes pour uid=" + pUserId);
		List<ICompteEntity> listeCompte = this.serviceCompte.selectAll(pUserId.intValue());
		VirementController.LOG.debug(
				"-- Recuperation comptes pour uid=" + pUserId + " resultat=" + Integer.valueOf(listeCompte.size()));
		// On replace la liste des comptes
		request.setAttribute("listeCompte", listeCompte);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String destination = "login.jsp";
		VirementController.LOG.debug("--> Passage dans VirementController.doGet");
		IUtilisateurEntity utilisateur = (IUtilisateurEntity) request.getSession(true).getAttribute("utilisateur");
		if (utilisateur == null) {
			request.setAttribute("erreur", "Pas connecte");
			VirementController.LOG.error("Erreur : utilisateur non connecte");
		} else {
			VirementBean virementBean = new VirementBean(request);
			request.setAttribute("vbean", virementBean);
			try {
				this.getAndSetListeComptes(request, utilisateur.getId());
				destination = "comptes/virement.jsp";
			} catch (Exception e) {
				VirementController.LOG.error("Erreur:", e);
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String destination = "login.jsp";
		VirementController.LOG.debug("--> Passage dans VirementController.doPost");
		IUtilisateurEntity utilisateur = (IUtilisateurEntity) request.getSession(true).getAttribute("utilisateur");
		if (utilisateur == null) {
			request.setAttribute("erreur", "Pas connecte");
			VirementController.LOG.error("Erreur : utilisateur non connecte");
		} else {
			VirementBean virementBean = new VirementBean(request);
			request.setAttribute("vbean", virementBean);
			try {
				this.getAndSetListeComptes(request, utilisateur.getId());

				VirementController.LOG
						.debug("-- doVirement uid=" + utilisateur.getId() + " cpSrc=" + virementBean.getCptSrcId()
								+ " cpDest=" + virementBean.getCptDstId() + " montant=" + virementBean.getMontant());
				this.serviceOperation.faireVirement(utilisateur.getId().intValue(),
						Integer.parseInt(virementBean.getCptSrcId()), Integer.parseInt(virementBean.getCptDstId()),
						Double.parseDouble(virementBean.getMontant()));
				request.setAttribute("message", "Virement OK");
				destination = "comptes/virement.jsp";
			} catch (Exception e) {
				request.setAttribute("erreur", e.getMessage());
				VirementController.LOG.error("Erreur:", e);
			}

		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}
}
