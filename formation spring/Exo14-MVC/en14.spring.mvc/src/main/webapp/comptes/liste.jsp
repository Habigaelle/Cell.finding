<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- On declare le fait que l'on va utiliser la taglib JSTL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Liste de vos comptes.</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/css/banque.css"/>" rel="stylesheet" type="text/css">
</head>

<body class="elbody">

	<form id="frmListeCompte" name="frmListeCompte" action="<c:url value="/ServletHistorique"/>" method="post">

		<table border="0" width="100%">
			<tr>
				<td align="center" valign="top"><img src="<c:url value="/images/titre.jpg"/>" border="0" height="98" alt="" /></td>
			</tr>
			<tr>
				<td><hr></td>
			</tr>
			<tr>
				<td align="center">
					<c:if test="${!empty listeCompte}">
						<p class="eltitre2">Liste de vos comptes sur Net Banque</p>
						<table border="1" width="60%">
							<tr bgcolor="white">
								<td class="ellibelletableau">Num&eacute;ro</td>
								<td class="ellibelletableau">D&eacute;signation</td>
								<td class="ellibelletableau">Taux</td>
								<td class="ellibelletableau">D&eacute;couvert autoris&eacute;</td>
								<td class="ellibelletableau">Solde</td>
							</tr>
							<c:forEach items="${listeCompte}" var="compte" varStatus="iter">
								<tr class="ellignetableau<c:out value="${iter.count%2+1}"/>">
									<td class="ellibelletableau">
										<%-- On fabrique un url que l'on place dans la variable de page urlPourCpt --%>
										<%-- Cet URL contiendra un parametre qui contiendra l'id du compte --%>
										<c:url value="historiqueCompte" var="urlPourCpt">
											<c:param name="cptId" value="${compte.id}"></c:param>
										</c:url>
										<%-- On recupere notre variable urlPourCpt afin de fabriquer un a href --%>
										<a href="<c:out value="${urlPourCpt}"/>"><c:out value="${compte.id}" /></a></td>
									<td class="ellibelletableau"><c:out value="${compte.libelle}" /></td>
									
									<%-- Le code presente ici est tres mauvais car il fait usage du nom long de la classe sous forme de chaine de caracteres --%>
									<%-- L'affichage ici est trop 'intelligent' --%>
									<%-- Le controleur (notre servlet) devrait transformer nos objets Compte en autre chose adapte a l'affichafe = un modele --%>						
									<c:if test="${compte.getClass().name =='fr.banque.CompteASeuilRemunere' || compte.getClass().name =='fr.banque.CompteRemunere'}">
										<td class="ellibelletableau"><c:out value="${compte.taux}" /></td>
									</c:if>
									<c:if test="${compte.getClass().name !='fr.banque.CompteASeuilRemunere' && compte.getClass().name !='fr.banque.CompteRemunere'}">
										<td class="ellibelletableau">--</td>
									</c:if>
									<c:if test="${compte.getClass().name =='fr.banque.CompteASeuilRemunere' || compte.getClass().name =='fr.banque.CompteASeuil'}">
										<td class="ellibelletableau"><c:out value="${compte.seuil}" /></td>
									</c:if>
									<c:if test="${compte.getClass().name !='fr.banque.CompteASeuilRemunere' && compte.getClass().name !='fr.banque.CompteASeuil'}">
										<td class="ellibelletableau">--</td>
									</c:if>
									<td class="ellibelletableau"><c:out value="${compte.solde}" /></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<c:if test="${empty listeCompte}">
						Pas de compte pour cet utilisateur.
					</c:if>
					<p>
						<a href="<c:url value="/menu"/>">
							<img src="<c:url value="/images/menu.gif"/>" width="98" height="33" border="0" alt="" />
						</a>
					</p>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
