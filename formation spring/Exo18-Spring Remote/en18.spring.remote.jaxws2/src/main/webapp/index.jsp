<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- On declare le fait que l'on va utiliser la taglib JSTL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>JAX WS avec JAX-WS-spring</title>
</head>
<body>
	JAX WS avec JAX-WS-spring, lancez <a href="https://www.soapui.org/" target="_blank">SOAP UI</a> : <br/>
	<%-- <a href="http://localhost:8080/exo18.spring.remote.jaxws2/AuthentificationRemoteService?wsdl">voir</a>. --%>
	<c:url value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/AuthentificationRemoteService?wsdl" var="url01"/>
	<a href="<c:out value="${url01}"/>">${url01}</a><br/>
	<c:url value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/OperationRemoteService?wsdl" var="url02"/>
	<a href="<c:out value="${url02}"/>">${url02}</a><br/>
	<c:url value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/CompteRemoteService?wsdl" var="url03"/>
	<a href="<c:out value="${url03}"/>">${url03}</a><br/>

</body>
</html>