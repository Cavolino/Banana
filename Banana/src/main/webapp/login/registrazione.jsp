<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="Servlet.Login"%>
<%@ page import="Servlet.Init"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%=pageContext.getServletContext().getInitParameter("titolo")%></title>
<!-- <link rel="stylesheet" href="D:/Gabri/b/web/style/registrazione.css"> -->
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
	<br>
	
		<%
			if(getServletContext().getAttribute("init") == null){
				Init init = new Init();
				init.init(getServletConfig());
			}		
		%>
		
	
		<div id="content">
			<form action="<%=request.getContextPath()%>/registrazioneServlet" method="post">
		
		        <span class="title">Registrazione</span>
		        <input type="text" placeholder="Username" name="username">
		        <input type="text" placeholder="Email" name="email">
		        <input type="password" placeholder="Password" name="password">
		        <input type="password" placeholder="Ripeti password" name="ripetiPassword">
		        <div id="radio-choose">
		            <input type="radio" name="radio-gay" value="Membro">Membro
		            <input type="radio" name="radio-gay" value="Organizzatore">Organizzatore
		        </div>
		        <input type="submit" value="Registrati">
	        </form>
    	</div>
	
	<%
	} else {
	%>
	<div>
		<h2>Sei gia` registrato!</h2>
	</div>
	<%
	}
	%>
</body>
</html>