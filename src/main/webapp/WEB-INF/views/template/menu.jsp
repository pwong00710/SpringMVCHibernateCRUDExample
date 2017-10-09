<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<ul style="list-style:none;line-height:28px;">

	<li><spring:url value="/index" var="homeUrl" htmlEscape="true" />
		<a href="${homeUrl}">Home</a>
	</li>

	<li><spring:url value="/getAllCountries" var="getAllCountriesUrl" htmlEscape="true" />
		<a href="${getAllCountriesUrl}">Country List</a>
	</li>
	
	<!--
	<li><spring:url value="https://www.google.com.hk/?gws_rd=ssl" var="google" htmlEscape="true" />
		<a href="${google}">Google</a>
	</li>
	-->

	<!--
	<li hidden><spring:url value="http://localhost:8080/HelloWorld/kittensPicture.jsp" var="kittensPicture1" htmlEscape="true" />
		<a href="${kittensPicture1}">Kittens Picture (1)</a>
	</li>
	-->
	
	<!--
	<li hidden><spring:url value="/kittensPicture.jsp" var="kittensPicture2" htmlEscape="true" />
		<a href="${kittensPicture2}">Kittens Picture (2)</a>
	</li>
	-->
	
</ul>
