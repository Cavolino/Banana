<%@ page import="Beans.*"%>
<%@ page import="java.util.List"%>
<html>
<head>
<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-radius: 10px;
	text-align: center;
	vertical-align: middle;
}
</style>
<title>Elimina evento</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="/fragments/navbar.jsp"></jsp:include>
	<%
	Utente utente = ((Utente) session.getAttribute("utente"));
	String usernameOrganizzatore = utente.getUsername();
	Organizzatore o = new Organizzatore("", usernameOrganizzatore);
	List<Locale> listaLocali = o.getLocali();
	System.out.println(listaLocali.size());
	%>
	
	<%
	String name = request.getParameter("rimosso");

	if (name != null && !name.equals(" ")) {
		if (name.equals("true")) {
	%>
		<h2>Evento rimosso con successo!</h2>
	<%
	}
	}
	%>
	
	<br><br>
	<h2>Elimina evento</h2>
	
	<div id="eventi">

		<table style="width: 100%">
			<tr>
				<th>Locale</th>
				<th>Evento</th>
				<th>Descrizione</th>
				<th>Informazioni Aggiuntive</th>

			</tr>
			<%
			for (Locale l : listaLocali) {

				List<Evento> listaEventi = l.getEventi();
				for (Evento e : listaEventi) {
					System.out.println(e);
			%>
			<tr>
				<td><%=l.getNome()%></td>
				<td><%=e.getNome()%></td>
				<td><%=e.getDescrizione()%></td>
				<td><%=e.getInfoAggiuntive()%></td>
				<td><input type="button" value="Elimina"
					onclick="elimina('<%=e.getId()%>')"></td>
			</tr>
			<%
			}
			}
			%>

		</table>
	</div>

	<script>
const elimina = (idEvento) => {
	var url = '/Banana/rimuoviEvento';
	 const data = new URLSearchParams([
	        ["id", idEvento],        
	    ]);
	$.post({
		url: url,
		data: data.toString(),
		success: function(response){
			console.log(response);								  						
		}
	});
	
	location.reload();
}
</script>
	<style>
.full {
	width: 100%;
}

.dateTime {
	float: left;
}
</style>
</body>
</html>