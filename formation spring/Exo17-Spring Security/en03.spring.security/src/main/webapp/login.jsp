<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		<title>Spring security : Login</title>
	</head>
	
	<body>
		<c:if test="${!empty param.error}">
			<p>Problème d'authentification. </p>
			<c:out value="${SPRING_SECURITY_LAST_EXCEPTION}"/>
			<p>Revenir sur <a href="<c:url value="/login.jsp"/>">Login</a></p>
		</c:if>
			
		<c:if test="${empty param.error}">
			<form action="<c:url value="/j_spring_security_check"/>" method="post">
				<table style="width:100%;">
					<tr>
						<td>Nom d'utilisateur</td>
						<td><input type="text" size="20" name="j_username" /></td>
					</tr>
					<tr>
						<td>Mot de passe</td>
						<td><input type="password" size="20" name="j_password" /></td>
					</tr>
					<tr>
						<td>Se souvenir de moi</td>
						<td><input type="checkbox" name="_spring_security_remember_me" /></td>
					</tr>						
					<tr>
						<td colspan="2"><input type="submit" value="Go" /></td>
					</tr>				
				</table>
			</form>
		</c:if>
	</body>
</html>
