package com.banque.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.banque.entity.IUtilisateurEntity;
import com.banque.entity.impl.UtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.web.bean.LoginBean;

/**
 * Controller de login. <br/>
 */
@WebServlet(urlPatterns = "/login")
public class LoginController extends AbstractController {
	private static final Logger LOG = LogManager.getLogger(LoginController.class);
	private static final long serialVersionUID = 1L;

	private IAuthentificationService service;

	/**
	 * Constructeur de l'objet.
	 */
	public LoginController() {
		super();
		// TODO A retirer, en attente d'ID
		this.service = Mockito.mock(IAuthentificationService.class);
		try {
			IUtilisateurEntity u = new UtilisateurEntity(Integer.valueOf(1));
			Mockito.when(this.service.authentifier(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
					.thenReturn(u);
			Mockito.when(this.service.authentifier(null, null)).thenReturn(u);
		} catch (Exception e) {
			LoginController.LOG.warn("Erreur dans le mock", e);
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginController.LOG.debug("--> Passage dans LoginController.doGet");
		LoginBean loginBean = new LoginBean(request);
		request.setAttribute("lbean", loginBean);
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String destination = "login.jsp";
		LoginController.LOG.debug("--> Passage dans LoginController.doPost");

		LoginBean loginBean = new LoginBean(request);
		request.setAttribute("lbean", loginBean);
		try {
			LoginController.LOG.debug("-- LoginController.doPost login=" + loginBean.getLogin());
			IUtilisateurEntity utilisateur = this.service.authentifier(loginBean.getLogin(), loginBean.getPassword());
			LoginController.LOG
					.debug("-- LoginController.doPost login=" + loginBean.getLogin() + " resultat=" + utilisateur);
			request.getSession(true).setAttribute("utilisateur", utilisateur);
			destination = "menu.jsp";
		} catch (Exception e) {
			request.setAttribute("erreur", e.getMessage());
			LoginController.LOG.error("Erreur", e);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}

}
