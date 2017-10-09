<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Page</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

</style>

<!--
<link rel="stylesheet" href="<c:url value='/resources/css/SpringMVCHibernateCRUDExample.css'/>" type="text/css" media=" all"></link>
-->

</head>
<body onload='$("#loginForm #username").focus();'>

	<!--
	<h1>Spring Security Custom Login Form (XML)</h1>
	-->

	<div>
		<!--
		<h2>Login with Username and Password</h2>
		-->
	
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		
		<form id="loginForm" action="<c:url value='j_spring_security_check' />" method="post">
			<table>
				<tbody>
					<tr>
						<th colspan="2">Login</th>
					</tr>
					<tr>
						<td><label for="username">User Name:</label></td>
						<td><input id="username" name="username" type="text" value="" size="30" maxlength="30"></td>
					</tr>
					<tr>
						<td><label for="password">Password:</label></td>
						<td><input id="password" name="password" type="password" value="" size="30" maxlength="30"></td>
					</tr>
					<tr>
						<td colspan="2"><input id="addButton" type="submit" value="Submit" class="blue-button"></td>
					</tr>
				</tbody>
			</table>

		  	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
	
</body>
</html>
