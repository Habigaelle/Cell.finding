<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		<title>Spring security : Menu</title>
	</head>
	
	<body>
		<table style="width:100%;">
			<tr>
				<td><a href="<c:url value="/user/pageA.jsp"/>">Page A : role user</a></td>
				<td><a href="<c:url value="/adm/pageB.jsp"/>">Page B : role admin</a></td>
				<td>&nbsp;</td>
				<td><a href="<c:url value="/j_spring_security_logout"/>">Logout</a></td>
			</tr>
		</table>
	</body>
</html>
