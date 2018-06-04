package com.banque.web.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.banque.entity.IOperationEntity;
import com.banque.entity.IUtilisateurEntity;
import com.banque.entity.impl.OperationEntity;
import com.banque.service.IOperationService;
import com.banque.web.bean.HistoriqueBean;

/**
 * Controller pour l'historique <br/>
 */
@WebServlet(urlPatterns = "/historiqueCompte")
public class HistoriqueController extends AbstractController {
	private static final Logger LOG = LogManager.getLogger(HistoriqueController.class);
	private static final long serialVersionUID = 1L;

	private IOperationService service;

	/**
	 * Constructeur de l'objet.
	 */
	public HistoriqueController() {
		super();
		// TODO A retirer, en attente d'ID
		this.service = Mockito.mock(IOperationService.class);
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
			Mockito.when(this.service.faireVirement(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(),
					ArgumentMatchers.anyInt(), ArgumentMatchers.anyDouble())).thenReturn(r);
			Mockito.when(this.service.selectAll(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(r);
			Mockito.when(this.service.selectCritere(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(),
					(java.util.Date) ArgumentMatchers.any(), (java.util.Date) ArgumentMatchers.any(),
					ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean())).thenReturn(r);
			Mockito.when(this.service.select(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(),
					ArgumentMatchers.anyInt())).thenReturn(r.get(0));
		} catch (Exception e) {
			HistoriqueController.LOG.warn("Erreur dans le mock", e);
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String destination = "login.jsp";
		HistoriqueController.LOG.debug("--> Passage dans HistoriqueController.service");

		IUtilisateurEntity utilisateur = (IUtilisateurEntity) request.getSession(true).getAttribute("utilisateur");
		if (utilisateur == null) {
			request.setAttribute("erreur", "Pas connecte");
			HistoriqueController.LOG.error("Erreur : utilisateur non connecte");
		} else {
			// TODO A faire validation des informations
			HistoriqueBean historiqueBean = new HistoriqueBean(request);
			request.setAttribute("hbean", historiqueBean);
			try {
				HistoriqueController.LOG.debug("-- HistoriqueController.service pour uid=" + utilisateur.getId()
						+ " et cpid=" + historiqueBean.getCptId() + " avec dateDebut=" + historiqueBean.getDateDebut()
						+ " dateFin=" + historiqueBean.getDateFin() + " credit=" + historiqueBean.getCredit()
						+ " debit=" + historiqueBean.getDebit());
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
				HistoriqueController.LOG
						.debug("-- HistoriqueController.service pour uid=" + utilisateur.getId() + " et cpid="
								+ historiqueBean.getCptId() + " resultat=" + Integer.valueOf(listeOperations.size()));
				request.setAttribute("listeOperations", listeOperations);
			} catch (Exception e) {
				request.setAttribute("erreur", e.getMessage());
				HistoriqueController.LOG.error("Erreur:", e);
			}
			destination = "comptes/historique.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}
}
