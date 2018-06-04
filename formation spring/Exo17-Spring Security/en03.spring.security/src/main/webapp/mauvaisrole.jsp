<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		<title>Spring security : Mauvais rôle</title>
	</head>
	
	<body>
		<p>Désolé, vous n'avez pas le bon rôle</p>
		<p>Revenir sur <a href="<c:url value="/menu.jsp"/>">menu</a></p>
	</body>
</html>
