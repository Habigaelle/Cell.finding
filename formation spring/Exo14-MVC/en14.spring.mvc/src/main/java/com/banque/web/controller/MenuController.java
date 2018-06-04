package com.banque.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.banque.entity.IUtilisateurEntity;

/**
 * Controller qui va afficher le menu. <br/>
 */
@WebServlet(urlPatterns = "/menu")
public class MenuController extends AbstractController {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger(MenuController.class);

	/**
	 * Constructeur.
	 */
	public MenuController() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MenuController.LOG.debug("--> MenuController.service");
		IUtilisateurEntity utilisateur = (IUtilisateurEntity) request.getSession(true).getAttribute("utilisateur");
		if (utilisateur == null) {
			request.setAttribute("erreur", "Pas connecte");
			MenuController.LOG.error("Erreur : utilisateur non connecte");
		}
		MenuController.LOG.debug("-- MenuController.service uid=" + utilisateur.getId());
		RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
		dispatcher.forward(request, response);
	}
}
