<%@ page import="Beans.Utente"%>
<%@ page import="Servlet.Login"%>
<%
boolean isLoggedIn = Login.isLoggedIn(request);

Utente u = ((Utente) session.getAttribute("utente"));
boolean isAdmin = u != null && u.getUsername().equals("admin");
%>

<style>
.navbar {
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
	color: black !important;
	text-decoration: none !important;
}

.navbar div {
	margin: 20px;
	flex: 1;
	flex-basis: 150px;
	flex-grow: 0;
}

.navbar div.spacer {
	flex-grow: 1;
}

.navbar div.nav-username {
	flex: 2;
	flex-basis: 200px;
	flex-grow: 0;
}

div.navbar {
	display: flex;
	height: 60px;
	background-color: #ffe200;
	text-decoration: none !important;
}

body {
	margin: 0px;
	font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS',
		sans-serif;
}

html, body {
	height: 100%;
	width: 100%;
}
</style>

<div class="navbar">
	<div>
		<a href="<%=request.getContextPath()%>/ricerca.jsp">Home</a>
	</div>
	<div class="spacer"></div>
	<div>
		<a href='<%=request.getContextPath()%>/ricerca'>Ricerca</a>
	</div>
	<%
	if (isLoggedIn) {
	%>
	<%=isAdmin ? "<div><a href='" + request.getContextPath() + "/admin.jsp'>Admin</a></div>" : ""%>
	<div>
		<a href='<%=request.getContextPath()%>/login/changePwd.jsp'>Change
			Password</a>
	</div>
	<div>
		<a href='<%=request.getContextPath()%>/logoutServlet'>Logout</a>
	</div>
	<div>
		<a href='<%=request.getContextPath()%>/pages/Membro.jsp'>Eventi
			interesse</a>
	</div>
	<div>
		<a href='<%=request.getContextPath()%>/pages/ViewInserisciEvento.jsp'>Inserisci
			evento</a>
	</div>
	<div>
		<a href='<%=request.getContextPath()%>/pages/ViewEliminaEvento.jsp'>Elimina
			evento</a>
	</div>
	<div>
		<a
			href='<%=request.getContextPath()%>/pages/IGestioneOrganizzatore.jsp'>Stato
			eventi</a>
	</div>
	<div class="nav-username">
		Hello: <b><%=((Utente) request.getSession().getAttribute("utente")).getUsername()%></b>
	</div>
	<%
	} else {
	%>
	<div>
		<a href='<%=request.getContextPath()%>/login/login.jsp'>Login</a>
	</div>
	<div>
		<a href='<%=request.getContextPath()%>/login/registrazione.jsp'>Registrazione</a>
	</div>
	<%
	}
	%>
</div>