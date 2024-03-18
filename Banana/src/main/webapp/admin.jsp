<%@ page import="Beans.Utente"%>
<%@ page import="Beans.Membro"%>
<%@ page import="Beans.Organizzatore"%>
<%@ page import="Beans.Evento"%>
<%@ page import="Beans.LocalPaths"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.reflect.Type"%>
<%@ page import="com.google.gson.reflect.TypeToken"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.FileNotFoundException"%>
<%@ page import="java.io.FileReader"%>
<%@ page import="java.io.FileWriter"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.Reader"%>
<%@ page import="com.google.gson.stream.JsonReader"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="Servlet.Login"%>



<%
Utente u = ((Utente) session.getAttribute("utente"));
boolean isAdmin = Login.isLoggedIn(request) && u != null && u.getUsername().equals("admin");

String id = "";

//String pathJson = "D:/Gabri/b/web/verificaOrganizzatore.json";
String pathJson = LocalPaths.pathVerificaOrganizzatore;

try (Reader reader = new FileReader(pathJson)) {
	Set<Organizzatore> items = new HashSet<Organizzatore>();

	Type setType = new TypeToken<HashSet<Organizzatore>>() {
	}.getType();
	items = new Gson().fromJson(new JsonReader(reader), setType);

	/*for (Organizzatore o : items) {
		System.out.println(o.getUsername());
		System.out.println(o.getDocumento());
	}*/
%>

<!DOCTYPE html>
<html lang="en">
<head>
<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-radius: 10px;
	text-align: center;
	vertical-align: middle;
}
</style>
<meta charset="UTF-8">
<title><%=pageContext.getServletContext().getInitParameter("titolo")%></title>
</head>
<body>
	<jsp:include page="/fragments/navbar.jsp"></jsp:include>
	<h2>Admin Page</h2>
	<h1>Organizzatori in attesa di approvazione</h1>
	<%
	String name = request.getParameter("verificato");

	if (name != null && !name.equals(" ")) {
		if (name.equals("true")) {
	%>
	<h2>Organizzatore verificato!</h2>
	<%
	}
	}
	%>
	<br>
	<%
	if (isAdmin) {
		if (items.size() > 0) {
	%>
	<div id="content">
		<table>
			<tr>
				<th>Username</th>
				<th>Mail</th>
				<th>Documento</th>
				<th>Verifica</th>
			</tr>
			<%
			for (Organizzatore o : items) {
			%>
			<tr>
				<td><%=o.getUsername()%></td>
				<td><%=o.getMail()%></td>
				<td><input type="image"
					src="<%=request.getContextPath()%>/img/<%=o.getDocumento()%>"
					alt="Documento non trovato" /></td>
				<td><form
						action="<%=request.getContextPath()%>/verificaOrganizzatore"
						method="post">
						<button name="verifica" value="<%=o.getUsername()%>">Verifica</button>
					</form></td>

			</tr>
			<%
			}
			%>
		</table>

		<div class="clear">
			<p>&nbsp;</p>
		</div>
	</div>
	<%
	} else {
	%>
	<h2>Non ci sono Organizzatori da verificare al momento!</h2>
	<%
	}
	} else {
	%>
	<h2>you are not admin, come back with some authorization</h2>
	<br />
	<br />
	<img src="<%=request.getContextPath()%>/res/angery.jpg"
		alt="angery.jpg" width="600" height="500">
	<%
	}
	} catch (FileNotFoundException e) {
	e.printStackTrace();
	} catch (IOException e1) {
	e1.printStackTrace();
	}
	%>
</body>
</html>
