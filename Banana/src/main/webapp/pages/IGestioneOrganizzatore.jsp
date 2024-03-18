<%@ page import="Beans.Membro"%>
<%@ page import="Beans.Utente"%>
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

<%@ page import="java.nio.file.*"%>


<%@ page import="Servlet.Login"%>
<html>
<style type="text/css">
.button {
	font: bold 11px Arial;
	text-decoration: none;
	background-color: #EEEEEE;
	color: #333333;
	padding: 2px 6px 2px 6px;
	border-top: 1px solid #CCCCCC;
	border-right: 1px solid #333333;
	border-bottom: 1px solid #333333;
	border-left: 1px solid #CCCCCC;
}

table, th, td {
	border: 1px solid black;
	border-radius: 10px;
	text-align: center;
	vertical-align: middle;
}
</style>
<head>
<title>Homepage Organizzatore</title>
</head>
<body>
	<jsp:include page="/fragments/navbar.jsp"></jsp:include>
	<%
	//Organizzatore organizzatore = (Organizzatore) session.getAttribute("organizzatore");
	//RequestDispatcher rd = request.getRequestDispatcher("/servletGestioneOrganizzatore"); //potrebbe non funzionare, bisogna mettere l'url della servlet
	//rd.include(request,response);

	Utente utente = ((Utente) session.getAttribute("utente"));
	String username = utente.getUsername();
	%>
	<h1>
		Benvenuto
		<%=username%></h1>
	<br>
	<h2>Da questa pagina puoi accedere a tutte le funzionalità di
		organizzatore</h2>
		
	<a href="ViewInserisciEvento.jsp" class="button">Inserisci Evento</a>
	<a href="ViewEliminaEvento.jsp" class="button">Elimina Evento</a>
	<br />

	<%
	//rd = request.getRequestDispatcher("/servletStatoEventi");
	//rd.include(request,response);
	String pathEventi = LocalPaths.pathEventi;//"D:/Gabri/b/web/eventi.json"
	//get all eventi
	Reader reader = Files.newBufferedReader(Paths.get(pathEventi));
	// convert JSON array to list of users
	Gson gson = new GsonBuilder().setDateFormat("dd/MM/yy").create();
	List<Evento> eventi = gson.fromJson(reader, new TypeToken<List<Evento>>() {
	}.getType());
	%>


	<br>
	<br>
	<h2>Da qua puoi vedere lo stato dei tuoi eventi</h2>
	<div class="eventi">
		<div class="evento">
			<table style="width: 100%">
				<tr>
					<th>Nome</th>
					<th>Descrizione</th>
					<th>Informazioni Aggiuntive</th>
					<th>Stato</th>
				</tr>
				<%
				for (Evento ev : eventi) {
					if (ev.getLocale().getOrganizzatore().equals(username)) {
				%>
				<tr>

					<td><%=ev.getNome()%></td>
					<td><%=ev.getDescrizione()%></td>
					<td><%=ev.getInfoAggiuntive()%></td>
					<td><%=ev.getStato()%></td>
				</tr>
				<%
				}
				}
				%>
			</table>

		</div>
	</div>
</body>
</script>
</html>