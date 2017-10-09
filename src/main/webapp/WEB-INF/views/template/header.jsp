<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="span-24">
	<!--
	<img src="${pageContext.request.contextPath}/resources/images/techzoo-header.png" 
		width="950" style="padding-top:10px;" />
	-->
	<img src="<c:url value='/resources/images/techzoo-header1.png' />" 
		width="950" style="padding-top:10px;" />
		
	<c:set var="now" value="<%=new java.util.Date()%>" />
	
	<ul style="list-style:none;line-height:28px;">
		<li>Last Update Time : <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${now}" /></li>
		<security:authorize access="isAuthenticated()">
			<li>Username : <security:authentication property="principal.username" /></li> 
		</security:authorize>
	</ul>	
	
	<!--
	<p>
		<b>Last Update Time : <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${now}" /></b>
		<br>
		<security:authorize access="isAuthenticated()">
    		<b>Username : <security:authentication property="principal.username" /></b> 
		</security:authorize>		
	</p>
	-->
	
	<img src="<c:url value='/resources/images/techzoo-header2.png' />" 
		width="950" />

</div>