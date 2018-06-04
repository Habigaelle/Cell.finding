<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="128kb"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Liste de vos comptes.</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="css/banque.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
		function doSubmit(aCptIdValue) {
			document.forms['frmListeCompte'].elements['cptId'].value=aCptIdValue;
			document.forms['frmListeCompte'].submit();
		}
	</script>
</head>

<body class="elbody">

	<form id="frmListeCompte" name="frmListeCompte" action="<c:url value="dohistorique.smvc"/>" method="post">
		<input type="hidden" name="cptId" value="" />
		<table border="0" width="100%">
			<tr>
				<td align="center" valign="top"><img src="<c:url value="images/titre.jpg"/>" border="0" height="98" alt="" /></td>
			</tr>
			<tr>
				<td><hr></td>
			</tr>
			<tr>
				<td align="center"><c:if test="${!empty listeCompte}">
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
									<td class="ellibelletableau"><a href="javascript:doSubmit(<c:out value="${compte.id}"/>)"><c:out value="${compte.id}" /></a></td>

									<td class="ellibelletableau"><c:out value="${compte.libelle}" /></td>

									<td class="ellibelletableau"><c:if test="${! empty compte.taux}">
											<c:out value="${compte.taux}" />
										</c:if> <c:if test="${empty compte.taux}">--</c:if></td>

									<td class="ellibelletableau"><c:if test="${! empty compte.decouvert}">
											<c:out value="${compte.decouvert}" />
										</c:if> <c:if test="${empty compte.decouvert}">--</c:if></td>

									<td class="ellibelletableau"><c:out value="${compte.solde}" /></td>
								</tr>
							</c:forEach>
						</table>
					</c:if> <c:if test="${empty listeCompte}">
			Pas de compte pour cet utilisateur.
		</c:if>

					<p>
						<a href="<c:url value="menu.smvc"/>"> <img src="<c:url value="images/menu.gif"/>" width="98" height="33" border="0" alt="" />
						</a>
					</p></td>
			</tr>
		</table>
	</form>

</body>
</html>
