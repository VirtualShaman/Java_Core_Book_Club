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
<h1>Welcome, ${user.userName}</h1>
<a href="/logout">Logout</a>
<br>
<a href="/newBook">Create A New Book</a>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>					
				<th>Author Name</th>
				<th>Posted By</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach  var="i" items="${allBooks}" >
				<tr>
					<td><c:out value="${i.id}"></c:out></td>
					<td><a href="/oneBook/${i.id}"><c:out value="${i.title}"></c:out></a></td>
					<td><c:out value="${i.author}"></c:out></td>
					<td>
						<c:choose>
							<c:when test="${i.user.id == user.id}">
								<a href="/editBook/${i.id}">Edit</a>
								<a href="/deleteBook/${i.id}">Delete</a>
							</c:when>
							<c:otherwise>
								<p>You are not the creator</p>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>