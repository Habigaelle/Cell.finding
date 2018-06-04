package com.banque.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Controller de logout. <br/>
 */
@Controller
@SessionAttributes({ HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY })
public class LogoutController extends AbstractController {
	private static final Logger LOG = LogManager.getLogger(LogoutController.class);

	/**
	 * Constructeur de l'objet.
	 */
	public LogoutController() {
		super();
	}

	/**
	 * Enclenche le logout
	 *
	 * @param modelMap
	 *            le model map
	 * @param status
	 *            status de la session
	 * @param request
	 *            la request
	 * @return la ou aller
	 */
	@RequestMapping(value = "/dologout.smvc", method = RequestMethod.GET)
	public String doLogout(ModelMap modelMap, HttpServletRequest request, SessionStatus status) {
		LogoutController.LOG.debug("--> Passage dans doLogout");
		request.getSession(true).removeAttribute("utilisateur");
		try {
			// Permet de netoyer la session
			request.logout();
		} catch (ServletException e) {
			LogoutController.LOG.error("Error in logout", e);
		}
		status.setComplete();
		return "index";
	}

}
