<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- JSP Declarations -->
<%! String currentDate; %> 

<!DOCTYPE html>
<html>
<head>

     <!--
     <script type = "text/javascript" 
         src = "https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
         
     <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
     
     <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
     <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
     -->
         
      <script type = "text/javascript">
         $(document).ready(function() {
            //$(".deleteCountry").css("background-color", "yellow");

            $(document).on('click', '.deleteCountry', function (e) {
                var link = this;

                e.preventDefault();

                $('.unique').dialog({                    
                    buttons: {
                        "Ok": function () {
                            //window.open($(link).attr("href"));
                            
                            // Spring MVC
							// window.location = $(link).attr("data-href");
							
							// Ajax
                            var countryId = $(link).attr("data-href").split("/")[3];

        	                $.ajax({  
        	                    url:"<c:url value='/rest/deleteCountry/"+countryId+"' />",  
        	                    type:"delete",
        	                    contentType: "application/json",
        	                    headers: 
        	                    {
        	                        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        	                    },
        	                    dataType: "JSON",
        	                    success: function(data) { 
        							$("#loadButton").trigger("click");
        						},
        				        error: function() { alert("Error: Fail to delete"); }  
        	                });
                            
                            $(this).dialog("close");
                        },
                        "Cancel": function () {
                            $(this).dialog("close");
                        }
                    }
                });
            });

            $(document).on('click', '.updateCountry', function (e) {
                var link = this;

                e.preventDefault();
				// Spring MVC
				// window.location = $(link).attr("data-href");

                // Ajax
                var tr = $(link).parents('tr');

//                 alert($(link).prop('tagName')+'->'+$(tr).prop('tagName'));

				var country = {};

                $(tr).find('td').each(function(i) {
                         // code to be execute
                    if ($(this).attr("id") == "countryId") {
                        country.id = $(this).html();
                    } else if ($(this).attr("id") == "countryName") {
                        country.countryName = $(this).html();
                    } else if ($(this).attr("id") == "countryPopulation") {
                        country.population = $(this).html();
                    } 
                });

             	$("#country #id").val(country.id);
             	$("#country #countryName").val(country.countryName);
             	$("#country #population").val(country.population);
            });

            $("#loadButton").click(function(event){

            	var url = "<c:url value='/rest/getAllCountries' />"

                $.getJSON(url, function(jd) {
//                 	$('#countryList').html(JSON.stringify(jd));

					$('#countryList').empty();
					$('#countryList').append('<table class="tg"></table>');
					var table = $('#countryList').children();
					table.append('<tr>'+
								 '<th width="80">Id</th>'+
								 '<th width="120">Country Name</th>'+
								 '<th width="120">Population</th>'+
								 '<th width="60">Edit</th>'+
								 '<th width="60">Delete</th>'+
								 '</tr>');
					
                	$(jd).each(function(i, obj) {
                		table.append('<tr>'+
                			'<td id="countryId">'+obj.id+'</td>'+
                			'<td id="countryName">'+obj.countryName+'</td>'+
                			'<td id="countryPopulation">'+obj.population+'</td>'+
                			'<td>'+
                			'<button class="updateCountry blue-button" data-href="/SpringMVCHibernateCRUDExample/updateCountry/'+obj.id+'">Edit</button>'+
                			'</td>'+
                			'<td>'+
                			'<button class="deleteCountry blue-button" data-href="/SpringMVCHibernateCRUDExample/deleteCountry/'+obj.id+'">Delete</button>'+
                			'</td>'+
                			'</tr>');
                	});

                }).error(function() { alert("Error: Fail to refresh"); });
 					
             });

            $("#country #addButton").click(function(e){
            	e.preventDefault();

            	if (validate()) {
// 	                var countryTable = this;
	                
	                var id = $("#country #id").val();
	                var countryName = $("#country #countryName").val();
	                var population = $("#country #population").val();
	                var JSONObject= {
	                     "id":id,
	                     "countryName":countryName,
	                     "population":population,
	                     };
	
	                $.ajax({  
	                    url:"<c:url value='/rest/addCountry' />",  
	                    type:"post",
	                    contentType: "application/json",
	                    data :  JSON.stringify(JSONObject),
	                    headers: 
	                    {
	                        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
	                    },
	                    dataType: "JSON",
	                    success: function(data) { 
	//                              var jsonData = $.parseJSON(data); //if data is not json
	//                              $('#name').val(jsonData.name);  
	//                              $('#email').val(jsonData.email);
							$("#country #id").val("0");
							$("#country #countryName").val("");
							$("#country #population").val("");
							$("#loadButton").trigger("click");
						},
				        error: function() { alert("Error: Fail to add/update"); }  
	                });
            	}  
            }); 
                        
         });
      </script>

<!--
<style>
.blue-button {
	background: #25A6E1;
	filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#25A6E1',
		endColorstr='#188BC0', GradientType=0);
	padding: 3px 5px;
	color: #fff;
	font-family: 'Helvetica Neue', sans-serif;
	font-size: 12px;
	border-radius: 2px;
	-moz-border-radius: 2px;
	-webkit-border-radius: 4px;
	border: 1px solid #1A87B9
}

table {
	font-family: "Helvetica Neue", Helvetica, sans-serif;
	width: 50%;
}

th {
	background: SteelBlue;
	color: white;
}

td, th {
	border: 1px solid gray;
	width: 25%;
	text-align: left;
	padding: 5px 10px;
}

.unique {display: none;}

</style>
-->

<script type="text/javascript">
   <!--

	// Form validation code will come here.

	function isPositiveInteger(n) {
		var floatN = parseFloat(n);
		return !isNaN(floatN) && isFinite(n) && floatN > 0 && floatN % 1 == 0;
	}

	function validate() {
		if (document.myForm.countryName.value == "") {
			alert("Please provide country name!");
			document.myForm.countryName.focus();
			return false;
		}

		if (!isPositiveInteger(document.myForm.population.value)) {
			alert("Please provide population!");
			document.myForm.population.focus();
			return false;
		}

		return (true);
	}
//-->
</script>

<!--
<meta name="csrf-token" content="${_csrf.token}"/>
<meta name="csrf-headerName" content="${_csrf.headerName}"/>
-->

<!--
<link rel="stylesheet" href="<c:url value='/resources/css/SpringMVCHibernateCRUDExample.css'/>" type="text/css" media=" all"></link>
-->

</head>
<body onload="$('#loadButton').trigger('click');">
	<!--The Scriptlet -->
	<%
	currentDate = (new java.util.Date()).toString();
	%>

	<!-- JSP Expression -->
	<!--
	<h3>Welcome ${username}! (<%= currentDate %>)</h3>
	-->
	
	<!--
	<h3>Welcome!</h3>
	-->

	<!-- Spring MVC -->
	<!--
	<form:form method="post" modelAttribute="country"
		action="/SpringMVCHibernateCRUDExample/addCountry" name="myForm" onsubmit="return(validate());">
		<table>
			<tr>
				<th colspan="2">Add Country</th>
			</tr>
			<tr>
				<form:hidden path="id" />
				<td><form:label path="countryName">Country Name:</form:label></td>
				<td><form:input path="countryName" size="30" maxlength="30"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="population">Population:</form:label></td>
				<td><form:input path="population" size="30" maxlength="30"></form:input></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value= "Submit" class="blue-button" /></td>
			</tr>
		</table>
	</form:form>
	-->
	
	<!-- Ajax -->
	<form id="country" name="myForm" action="" method="post">
		<table>
			<tbody><tr>
				<th colspan="2">Add Country</th>
			</tr>
			<tr>
				<input id="id" name="id" type="hidden" value="0">
				<td><label for="countryName">Country Name:</label></td>
				<td><input id="countryName" name="countryName" type="text" value="" size="30" maxlength="30"></td>
			</tr>
			<tr>
				<td><label for="population">Population:</label></td>
				<td><input id="population" name="population" type="text" value="" size="30" maxlength="30"></td>
			</tr>
			<tr>
				<td colspan="2"><input id="addButton" type="submit" value="Submit" class="blue-button"></td>
			</tr>
		</tbody></table>
		<div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</div>
	</form>
	
	</br>
	<h3>Country List</h3>
	
	<div class="unique">Are you sure you want to continue?</div>
	
    <!--
    <p>Click on the button to load result.json file</p>
    -->
		
    <div id = "countryList">
    	<!-- Spring MVC -->
    	<!--
		<c:if test="${!empty listOfCountries}">
			<table class="tg">
				<tr>
					<th width="80">Id</th>
					<th width="120">Country Name</th>
					<th width="120">Population</th>
					<th width="60">Edit</th>
					<th width="60">Delete</th>
				</tr>
				<c:forEach items="${listOfCountries}" var="country">
					<tr>
						<td id="countryId">${country.id}</td>
						<td id="countryName">${country.countryName}</td>
						<td id="countryPopulation">${country.population}</td>
						<td>
							<button class="updateCountry blue-button" data-href="<c:url value='/updateCountry/${country.id}' />">Edit</button>
						</td>
						<td> 
							<button class="deleteCountry blue-button" data-href="<c:url value='/deleteCountry/${country.id}' />">Delete</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		-->
    </div>
	
	<p></p>
	<input class="blue-button" type = "button" id = "loadButton" value = "Refersh" />
	
	<!-- Not working when csrf is enabled -->
	<!--
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	
	<p></p>
	<a href="${logoutUrl}">Log Out</a>
	-->
	
	<p></p>
	<c:url var="logoutUrl" value="/logout"/>
	<form action="${logoutUrl}" method="post">
    	<input type="submit" value="Logout" class="blue-button"/>
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
		
	<p></p>
</body>
</html>

