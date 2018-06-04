<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- On declare le fait que l'on va utiliser la taglib JSTL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Historique de vos operations.</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/css/banque.css"/>" rel="stylesheet" type="text/css">
 <link href="<c:url value="/css/jquery-ui.min.css"/>" rel="stylesheet" type="text/css"/>
 <script src="<c:url value="/librairie/jquery.min.js"/>"></script>
 <script src="<c:url value="/librairie/jquery-ui.min.js"/>"></script>
 <script src="<c:url value="/librairie/i18n/datepicker-fr.js"/>"></script>
 <script>
  $( function() {
    $("#dateDebut").datepicker({
		showOn: "button",
		buttonImage: "<c:url value="/images/date_icon.gif"/>",
		buttonImageOnly: true,
		buttonText: "Selectionner une date",
		dateFormat: "yy/mm/dd",
		regional: "fr",
		showWeek: true,
		firstDay: 1
    });
    $("#dateFin").datepicker({
		showOn: "button",
		buttonImage: "<c:url value="/images/date_icon.gif"/>",
		buttonImageOnly: true,
		buttonText: "Selectionner une date",
		dateFormat: "yy/mm/dd",
		regional: "fr",
		showWeek: true,
		firstDay: 1
    });
  } );
  </script>

</head>

<body class="elbody">

	<div id="popupcalendar" class="text">&nbsp;</div>
	<form id="frmListeOperations" name="frmListeOperations" action="<c:url value="/historiqueCompte"/>" method="post">

		<input type="hidden" name="cptId" value="<c:out value="${requestScope.hbean.cptId}"/>" />

		<table border="0" width="100%">
			<tr>
				<td align="center" valign="top"><img src="<c:url value="/images/titre.jpg"/>" border="0" height="98" alt="" /></td>
			</tr>
			<tr>
				<td><hr></td>
			</tr>
			<tr>
				<td align="center">
					<p>
						<c:if test="${!empty requestScope.erreur}">
							<p><c:out value="${requestScope.erreur}" /></p>
						</c:if>
					</p>
					<p>&nbsp;</p>
					<p>
						Historique de vos op&eacute;rations effectu&eacute;es sur le compte n&deg;<c:out value="${requestScope.hbean.cptId}" />
					</p>
					<p>Crit&egrave;res de recherche :</p>
					<table width="70%" border="1">
						<tr>
							<td width="446" bgcolor="#ffffff" class="ellibelletableau">Date</td>
							<td width="138" bgcolor="#ffffff" class="ellibelletableau">Type</td>
						</tr>
						<tr>
							<td width="460" bgcolor="#fae6a0" class="ellibelletableau">
								Du <input type="text" id="dateDebut" name="dateDebut" value="<c:out value="${requestScope.hbean.dateDebut}"/>" /> 
								&nbsp;&nbsp;
								
								Au <input type="text" name="dateFin" id="dateFin" value="<c:out value="${requestScope.hbean.dateFin}"/>" />
							</td>
							<td width="138" bgcolor="#fae6a0" class="ellibelletableau">
								<p>
									Credit <input type="checkbox" name="credit" <c:if test="${empty requestScope.hbean.credit}">checked="checked"</c:if> />
								</p>
								<p>
									D&eacute;bit <input type="checkbox" name="debit" <c:if test="${empty requestScope.hbean.debit}">checked="checked"</c:if> />
								</p>
							</td>
						</tr>
					</table>
					<p>
						<a href="javascript:frmListeOperations.submit()">
							<img src="<c:url value="/images/rechercher.gif"/>" width="98" height="33" border="0" alt="" />
						</a>
					</p>
					<p>&nbsp;</p>
					<c:if test="${!empty listeOperations}">
						<table border="1" width="70%">
							<tr bgcolor="white">
								<td class="ellibelletableau">Date</td>
								<td class="ellibelletableau">Libell&eacute;</td>
								<td class="ellibelletableau">Montant</td>
							</tr>
							<c:forEach items="${listeOperations}" var="operation" varStatus="iter">
								<tr class="ellignetableau<c:out value="${iter.count%2+1}"/>">
									<td class="ellibelletableau"><fmt:formatDate value="${operation.date}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
									<td class="ellibelletableau"><c:out value="${operation.libelle}" /></td>
									<td class="ellibelletableau"><c:out value="${operation.montant}" /></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<c:if test="${empty listeOperations}">
						Pas d'opération pour ce compte.
					</c:if>

					<p>&nbsp;</p>
					<table width="150">
						<tr>
							<td>
								<a href="<c:url value="/listeCompte"/>">
									<img src="<c:url value="/images/liste-comptes.gif"/>" width="103" height="33" border="0" alt="" />
								</a>
							</td>
							<td>&nbsp;</td>
							<td>
								<a href="<c:url value="/menu"/>">
									<img src="<c:url value="/images/menu.gif"/>" width="98" height="33" border="0" alt="" />
								</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
