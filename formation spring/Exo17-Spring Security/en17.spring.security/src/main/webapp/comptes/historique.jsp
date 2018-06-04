<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="128kb"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Historique de vos operations.</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="css/banque.css"/>" rel="stylesheet" type="text/css">
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
	<form:form action="dohistorique.smvc" method="post" modelAttribute="historiqueBean" name="historiqueBean">

		<form:hidden path="cptId" />

		<table border="0" width="100%">
			<tr>
				<td align="center" valign="top"><img src="<c:url value="images/titre.jpg"/>" border="0" height="98" alt="" /></td>
			</tr>
			<tr>
				<td><hr></td>
			</tr>
			<tr>
				<td align="center">
					<p style="">
						<spring:hasBindErrors name="historiqueBean">
							<form:errors path="*" />
						</spring:hasBindErrors>
					</p>
					<p>&nbsp;</p>
					<p style="">
						Historique de vos op&eacute;rations effectu&eacute;es sur le compte n&deg;
						<c:out value="${historiqueBean.cptId}" />
					</p>
					<p style="">Crit&egrave;res de recherche :</p>
					<table width="70%" border="1">
						<tr>
							<td width="446" bgcolor="#ffffff" class="ellibelletableau">Date</td>
							<td width="138" bgcolor="#ffffff" class="ellibelletableau">Type</td>
						</tr>
						<tr>
							<td width="460" bgcolor="#fae6a0" class="ellibelletableau">
								Du <form:input path="dateDebut" /> 
								&nbsp;&nbsp; Au 
								<form:input path="dateFin" />
							</td>
							<td width="138" bgcolor="#fae6a0" class="ellibelletableau">
								<p>
									Credit
									<form:checkbox path="credit" />
								</p>
								<p>
									Debit
									<form:checkbox path="debit" />
								</p>
							</td>
						</tr>
					</table>
					<p>
						<a href="javascript:historiqueBean.submit()"> <img src="<c:url value="images/rechercher.gif"/>" width="98" height="33" border="0" alt="" />
						</a>
					</p>
					<p>&nbsp;</p> <c:if test="${!empty listeOperations}">
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
					</c:if> <c:if test="${empty listeOperations}">
			Pas d'opération pour le compte n°<c:out value="${requestScope.historiqueBean.cptId}" />
					</c:if>

					<p>&nbsp;</p>
					<table width="150">
						<tr>
							<td><a href="<c:url value="listeCompte.smvc"/>"> <img src="<c:url value="images/liste-comptes.gif"/>" width="103" height="33" border="0" alt="" />
							</a></td>
							<td>&nbsp;</td>
							<td><a href="<c:url value="menu.smvc"/>"> <img src="<c:url value="images/menu.gif"/>" width="98" height="33" border="0" alt="" />
							</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>

</body>
</html>
