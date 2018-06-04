<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- On declare le fait que l'on va utiliser la taglib JSTL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Page d'exemple Spring en Web Services</title>
</head>
<body>
	Exo Web Service en Spring
	<br />
	<h1>Authentification</h1>
	<ul>
		<li>
			<c:url value="/rest/authentifier/byurl/dj/dj" var="url01" />		
			<a href="${url01}">
				<c:out value="${pageContext.request.scheme}" />://<c:out value="${pageContext.request.serverName}" />:<c:out value="${pageContext.request.serverPort}" />${url01}
			</a>
		</li>
		<li>
			<c:url value="/rest/authentifier/byparam" var="url02">
				<c:param name="login" value="dj"/>
				<c:param name="password" value="dj"/>
			</c:url>		
			<a href="${url02}">
				<c:out value="${pageContext.request.scheme}" />://<c:out value="${pageContext.request.serverName}" />:<c:out value="${pageContext.request.serverPort}" />${url02}
			</a>
		</li>
		<li>
			<c:url value="/rest/authentifier/byparam" var="url03" />
			<form action="${url03}" method="post">
				Login: <input type="text" name="login" /><br /> 
				Pwd: <input type="text" name="password" /><br />
				<input type="submit" value="Go" />
			</form>
		</li>
	</ul>
	<h1>Lister Comptes</h1>
	<ul>
		<li>
			<c:url value="/rest/comptes/lister" var="url04">
				<c:param name="userId" value="1"/>
			</c:url>			
			<a href="${url04}">
				<c:out value="${pageContext.request.scheme}" />://<c:out value="${pageContext.request.serverName}" />:<c:out value="${pageContext.request.serverPort}" />${url04}
			</a>
		</li>
		<li>
			<c:url value="/rest/comptes/lister" var="url05" />
			<form action="${url05}" method="post">
				Id User: <input type="text" name="userId" /><br /> 
				<input type="submit" value="Go" />
			</form>
		</li>
	</ul>
	<h1>Lister Operations</h1>
	<ul>
		<li>
			<c:url value="/rest/operations/lister" var="url06">
				<c:param name="userId" value="1"/>
				<c:param name="compteId" value="15"/>
			</c:url>			
			<a href="${url06}">
				<c:out value="${pageContext.request.scheme}" />://<c:out value="${pageContext.request.serverName}" />:<c:out value="${pageContext.request.serverPort}" />${url06}
			</a>
		</li>
		<li>
			<c:url value="/rest/operations/lister" var="url07" />
			<form action="${url07}" method="post">
				Id User: <input type="text" name="userId" /><br /> 
				Compte Id: <input type="text" name="compteId" /><br /> 
				Date Debut (yyyy/MM/dd): <input type="text" name="dateDebut" /><br /> 
				Date Fin (yyyy/MM/dd): <input type="text" name="dateFin" /><br /> 
				Credit/Debit 
				<select name="credit">
					<option value="2">Credit et Debit</option>
					<option value="0">Debit</option>
					<option value="1">Credit</option>
				</select> <br/>
				<input type="submit" value="Go" />
			</form>
		</li>
	</ul>
</body>
</html>