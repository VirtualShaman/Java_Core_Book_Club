<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<!-- for Bootstrap CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<!-- YOUR own local CSS -->
	<link rel="stylesheet" href="/css/main.css"/>
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
</head>
<body>
<h1>${book.title}</h1>

<h3>${book.user.userName} read ${book.title} by ${book.author}</h3>
<h4>Here are ${book.user.userName}'s thoughts:</h4>
<p>${book.description}</p>
<c:choose>
	<c:when test="${book.user.id == user.id}">
		<a href="/editBook/${book.id}">Edit</a>
		<a href="/deleteBook/${book.id}">Delete</a>
	</c:when>
	<c:otherwise>
		<p>You are not the creator</p>
	</c:otherwise>
</c:choose>
</body>
</html>