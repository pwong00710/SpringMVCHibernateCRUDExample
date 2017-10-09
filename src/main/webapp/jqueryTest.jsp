<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>The jQuery Example</title>

	<script type = "text/javascript" src = "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
         
	<script type = "text/javascript" src = "${pageContext.request.contextPath}/resources/jquery/custom.js"></script>
	
	<script type = "text/javascript" language = "javascript">
         $(document).ready(function() {
            $("p").css("background-color", "yellow");
         });
	</script>
</head>

<body>
	<h1>Hello, World!</h1>

	<img src="${pageContext.request.contextPath}/resources/images/test.png"
		alt="Test Image" />
		
    <div id = "mydiv">
         Click on this to see a dialogue box.
    </div>

	<p class = "myclass">This is a paragraph.</p>
	<p id = "myid">This is second paragraph.</p>
	<p>This is third paragraph.</p>
	    
</body>

</html>