<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- On declare le fait que l'on va utiliser la taglib JSTL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Gestion des Virements</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/css/banque.css"/>" rel="stylesheet" type="text/css">
</head>

<body class="elbody">

	<form name="frmVirements" action="<c:url value="/virement"/>" method="post">
		<table border="0" width="100%" align="center">
			<tr>
				<td align="center" valign="top"><img src="<c:url value="/images/titre.jpg"/>" border="0" height="98" alt="" /></td>
			</tr>
			<tr>
				<td>
					<hr>
				</td>
			</tr>
			<tr>
				<td>
					<c:if test="${!empty requestScope.erreur}">
						<p><c:out value="${requestScope.erreur}" /></p>
					</c:if> <c:if test="${!empty requestScope.message}">
						<p><c:out value="${requestScope.message}" /></p>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					<br /> <br />
					<table width="60%" border="1" align="center">
						<tr>
							<td align="left">&nbsp;<img src="<c:url value="/images/puce.gif"/>" width="13" height="18" alt="" />&nbsp;Comptes &eacute;metteurs</td>
							<td>
								<select name="inCmptEme" id="inCmptEme">
									<option value="">&nbsp;&nbsp;&nbsp;--&nbsp;Choix&nbsp;--&nbsp;&nbsp;&nbsp;</option>
									<c:forEach items="${listeCpt}" var="compte">
										<c:if test="${!empty pCmptEme && pCmptEme.equals(compte.id)}">
											<option value="${compte.id}" selected="selected">${compte.libelle}</option>										
										</c:if>
										<c:if test="${empty pCmptEme || !pCmptEme.equals(compte.id)}">
											<option value="${compte.id}">${compte.libelle}</option>											
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left">&nbsp;<img src="<c:url value="/images/puce.gif"/>" width="13" height="18" alt="" />&nbsp;Comptes destinataires</td>
							<td>
								<select name="inCmptDes" id="inCmptDes">
									<option value="">&nbsp;&nbsp;&nbsp;--&nbsp;Choix&nbsp;--&nbsp;&nbsp;&nbsp;</option>
									<c:forEach items="${listeCpt}" var="compte">
										<c:if test="${!empty pCmptDes && pCmptDes.equals(compte.id)}">
											<option value="${compte.id}" selected="selected">${compte.libelle}</option>											
										</c:if>
										<c:if test="${empty pCmptDes || !pCmptDes.equals(compte.id)}">
											<option value="${compte.id}">${compte.libelle}</option>											
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left">&nbsp;<img src="<c:url value="/images/puce.gif"/>" width="13" height="18" alt="" />&nbsp;Montant du virement</td>
							<td><input type="text" name="montant" value="<c:out value="${requestScope.vbean.montant}"/>" size="12" /></td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table width="200" border="0" align="center">
						<tr>
							<td>
								<a href="javascript:frmVirements.submit()">
									<img src="<c:url value="/images/bouton-validez.gif"/>" width="98" height="33" border="0" alt="" />
								</a>
							</td>
							<td>
								<a href="<c:url value="/menu"/>">
									<img src="<c:url value="/images/bouton-annuler.gif"/>" width="98" height="34" border="0" alt="" />
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
