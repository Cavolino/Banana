<%@ page import="Beans.*"%>
<%@ page import="java.util.List"%>
<html>
<head>
<title>Inserisci evento</title>
</head>
<body>
	<jsp:include page="/fragments/navbar.jsp"></jsp:include>
	<%
	Utente utente = ((Utente) session.getAttribute("utente"));
	String usernameOrganizzatore = utente.getUsername();
	Organizzatore o = new Organizzatore("", usernameOrganizzatore);
	List<Locale> listaLocali = o.getLocali();
	%>
	<%
	String name = request.getParameter("inserito");

	if (name != null && !name.equals(" ")) {
		if (name.equals("true")) {
	%>
		<h2>Evento inserito con successo!</h2>
	<%
	}
	}
	%>
	<br><br>
	<h2>Inserisci le informazioni relative all'evento che desideri organizzare</h2>

	<select id="locali">
		<option value="" selected disabled hidden>Seleziona un locale</option>
		<%
		for (Locale l : listaLocali) {
		%>
		<option value="<%=l.getNome()%>" data-nome="<%=l.getNome()%>"
			data-via="<%=l.getIndirizzo().getVia()%>"
			data-civico="<%=l.getIndirizzo().getCivico()%>"
			data-citta="<%=l.getIndirizzo().getCitta()%>"
			data-nazione="<%=l.getIndirizzo().getNazione()%>"
			data-latitude="<%=l.getIndirizzo().getLatitude()%>"
			data-longitude="<%=l.getIndirizzo().getLongitude()%>">
			<%=l.getNome()%>,
			<%=l.getIndirizzo().getVia()%>
			<%=l.getIndirizzo().getCivico()%>
			<%=l.getIndirizzo().getCitta()%>
			<%=l.getIndirizzo().getNazione()%>
		</option>
		<%
		}
		%>
	</select>

	<form method="POST" action="../inserisciEvento">
		<input type="text" hidden="true" id="nomeLocale" name="nomeLocale">
		<input type="text" hidden="true" id="via" name="via"> <input
			type="text" hidden="true" id="civico" name="civico"> <input
			type="text" hidden="true" id="citta" name="citta"> <input
			type="text" hidden="true" id="nazione" name="nazione"> <input
			type="text" hidden="true" id="latitude" name="latitude"> <input
			type="text" hidden="true" id="longitude" name="longitude"> <input
			type="text" class="full" name="nome" placeholder="Nome evento" /> <input
			type="text" class="full" name="descrizione" placeholder="Descrizione" />
		<input type="text" class="full" name="infoAggiuntive"
			placeholder="Inserisci delle infoAggiuntive" />

		<div class="dateTime">
			<label for="dataInizio">Data di inizio</label> <input type="date"
				name="dataInizio" /> <label for="dataFine">Data di fine</label> <input
				type="date" name="dataFine" /> <label for="oraInizio">Ora
				di inizio</label> <input type="time" name="oraInizio" min="00:00" /> <label
				for="oraFine">Ora di fine</label> <input type="time" name="oraFine"
				max="23:59" />
		</div>
		<br> <input type="submit" value="Inserisci Evento">
	</form>
	<script>
document.getElementById("locali").addEventListener("change", (e) => {
	console.log(e);
		
	const locale = event.target.options[event.target.selectedIndex];
	console.log(locale);
	document.getElementById("nomeLocale").value = locale.dataset.nome;
	document.getElementById("via").value = locale.dataset.via;
	document.getElementById("civico").value = locale.dataset.civico;
	document.getElementById("citta").value = locale.dataset.citta;
	document.getElementById("nazione").value = locale.dataset.nazione;
	document.getElementById("latitude").value = locale.dataset.latitude;
	document.getElementById("longitude").value = locale.dataset.longitude;
	
	console.log(locale.dataset.nome);
	console.log(locale.dataset.via);
	console.log(locale.dataset.civico);
	console.log(locale.dataset.citta);
	console.log(locale.dataset.nazione);
});
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