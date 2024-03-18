<%@ page import="Servlet.Login"%>

<head>
<title><%=pageContext.getServletContext().getInitParameter("titolo")%></title>
<!--  <link rel="stylesheet" href="D:/Gabri/b/web/style/login.css">-->
<style>
body {
	margin: 0px;
	font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS',
		sans-serif;
}

html, body {
	height: 100%;
	width: 100%;
}

.title {
	font-size: 35px;
	font-weight: bold;
	
}

#content {
	width: 25%;
	margin-left: auto;
	margin-right: auto;
}

#content input {
	width: 100%;
	margin-top: 10px;
	border-radius: 100px;
	padding: 2px 20px 2px 20px;
	font-size: 20px;
	border: 1px solid black;
	height: 40px;
	font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS',
		sans-serif;
}

#content input[type=submit] {
	background-color: #fdff77;
	color: black;
}

#content input[type=submit]:hover {
	background-color: black;
	color: white;
}

#content #radio-choose {
	height: 50px;
	font-size: 20px;
}

#content #radio-choose input {
	width: auto;
	height: auto;
}
</style>

</head>
<body>
	<jsp:include page="/fragments/navbar.jsp"></jsp:include>

	<%
	if (!Login.isLoggedIn(request)) {
		String error = (String) request.getAttribute("error");
		if (error != null) {
	%>
	<div id="errore">
		<h2><%=error%></h2>
	</div>
	<%
	}
	%>
	
	<div id="content">
		<form action="<%=request.getContextPath()%>/loginServlet" method="post">
	        <span class="title">Login</span>
	        <input type="text" placeholder="Username" name="username">
	        <input type="password" placeholder="Password" name="password">
	        <input type="submit" value="Accedi">
	     </form>
    </div>
	
	<%
	} else {
	%>
	<div>
		<h2>Sei già registrato!</h2>
	</div>
	<%
	}
	%>
</body>