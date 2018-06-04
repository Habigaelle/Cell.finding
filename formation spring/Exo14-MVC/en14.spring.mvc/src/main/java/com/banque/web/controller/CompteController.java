package com.banque.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.banque.entity.IUtilisateurEntity;
import com.banque.entity.impl.CompteEntity;
import com.banque.service.ICompteService;

/**
 * Controller qui liste les comptes <br/>
 */
@WebServlet(urlPatterns = "/listeCompte")
public class CompteController extends AbstractController {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger(CompteController.class);

	private ICompteService service;

	/**
	 * Constructeur de l'objet.
	 */
	public CompteController() {
		super();
		// TODO A retirer, en attente d'ID
		this.service = Mockito.mock(ICompteService.class);
		try {
			Mockito.when(this.service.select(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
					.thenReturn(Mockito.mock(ICompteEntity.class));
			List<ICompteEntity> r = new ArrayList<ICompteEntity>();
			r.add(new CompteEntity(Integer.valueOf(1), "Test", BigDecimal.valueOf(25), null, null));
			r.add(new CompteEntity(Integer.valueOf(2), "Test 2", BigDecimal.valueOf(250), null, null));
			Mockito.when(this.service.selectAll(ArgumentMatchers.anyInt())).thenReturn(r);
		} catch (Exception e) {
			CompteController.LOG.warn("Erreur dans le mock", e);
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String destination = "login.jsp";
		CompteController.LOG.debug("--> Passage dans CompteController.service");
		IUtilisateurEntity utilisateur = (IUtilisateurEntity) request.getSession(true).getAttribute("utilisateur");
		if (utilisateur == null) {
			request.setAttribute("erreur", "Pas connecte");
			CompteController.LOG.error("Erreur : utilisateur non connecte");
		} else {
			try {
				CompteController.LOG.debug("-- CompteController.service pour " + utilisateur.getId());
				List<ICompteEntity> listeCompte = this.service.selectAll(utilisateur.getId().intValue());
				request.setAttribute("listeCompte", listeCompte);
				CompteController.LOG.debug("-- CompteController.service pour " + utilisateur.getId() + " trouve "
						+ Integer.valueOf(listeCompte.size()));
				destination = "comptes/liste.jsp";
			} catch (Exception e) {
				CompteController.LOG.error("Erreur:", e);
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}
}
