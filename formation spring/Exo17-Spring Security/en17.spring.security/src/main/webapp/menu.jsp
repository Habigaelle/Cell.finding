<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="128kb"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>NetBanque Menu</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="css/banque.css"/>" rel="stylesheet">
</head>

<body class="elbody">
	<table border="0" width="100%">
		<tr>
			<td align="center" valign="top"><img src="<c:url value="images/image-bienvenue.jpg"/>" border="0" height="98" alt="" /></td>
		</tr>
		<tr>
			<td>
				<hr>
			</td>
		</tr>
		<tr>
			<td align="center">
				<table>
					<tr>
						<td>
							<form:errors path="*" />						
							<%-- Autre technique mais qui ne gere pas les messages avec trous 
							<c:forEach items="${errors.allErrors}" var="erreur">
								<li><spring:message code="${erreur.code}" /></li>
							</c:forEach>
							--%>
							
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="1">
								<tr class="ellignetableau1">
									<td width="446" class="elcelluletableau3"><img src="<c:url value="images/puce.gif"/>" width="13" height="18" alt="" />&nbsp; <a href="<c:url value="listeCompte.smvc"/>"> Liste de
											vos comptes</a></td>
								</tr>
								<tr class="ellignetableau2">
									<td width="446" class="elcelluletableau3"><img src="<c:url value="images/puce.gif"/>" width="13" height="18" alt="" />&nbsp; <a href="<c:url value="virement.smvc"/>"> Virement </a></td>
								</tr>
								
								<tr class="ellignetableau1">
									<td width="446" class="elcelluletableau3"><img src="<c:url value="images/puce.gif"/>" width="13" height="18" alt="" /> &nbsp; <a href="<c:url value="dologout.smvc"/>">Deconnexion</a></td>
								</tr>

							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
