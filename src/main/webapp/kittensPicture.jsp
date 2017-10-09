<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kittens Picture</title>
</head>
<body>
	<p>token:${_csrf.token},headerName:${_csrf.headerName},parameterName:${_csrf.parameterName}</p>

	<form id="country" name="myForm" action="/SpringMVCHibernateCRUDExample/addCountry" method="post">
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type="hidden" name="id" value="2"/>
		<input type="hidden" name="countryName" value="China"/>
		<input type="hidden" name="population" value="20000"/>
		<input type="submit" value= "Show Kittens Picture"/>
	</form>
</body>
</html>
