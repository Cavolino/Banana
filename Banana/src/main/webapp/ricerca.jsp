<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title><%=pageContext.getServletContext().getInitParameter("titolo")%></title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="scripts/condividi.js"></script>
<script src="scripts/ricerca.js" defer></script>
<script src="scripts/insert_map.js" defer></script>
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.8.0/dist/leaflet.css"
	integrity="sha512-hoalWLoI8r4UszCkZ5kL8vayOGVae1oxXe/2A4AO6J9+580uKHDO3JdHb7NzwwzK5xr/Fs0W40kiNHxM9vyTtQ=="
	crossorigin="" />

<!-- Make sure you put this AFTER Leaflet's CSS -->
<script src="https://unpkg.com/leaflet@1.8.0/dist/leaflet.js"
	integrity="sha512-BB3hKbKWOc9Ez/TAwyWxNXeoV9c1v6FIeYiBieIWkpLjauysF18NzgR1MBNBXf8/KABdlkX68nAhlwcDFLGPCQ=="
	crossorigin=""></script>


<link rel="stylesheet" type="text/css" href="style/ricerca.css" />
</head>
<body>
	<jsp:include page="/fragments/navbar.jsp"></jsp:include>

	<div id="content">
		<span class="title">Ricerca Evento</span> <input type="text"
			id="nomeCitta" name="nomeCitta" placeholder="Inserisci la tua città">
		<div class="filters" id="filters">
			Più filtri <span id="arrow-bottom">&or;</span>
		</div>
		<div id="filters-content" style="display: none">
			<input type="text" id="nomeLocale" name="nomeLocale"
				placeholder="Nome del locale"> <input type="date"
				id="dataInizio" name="dataInizio" placeholder=""> <input
				type="time" id="oraInizio" name="oraInizio" placeholder="">
			<input type="checkbox" id="nearYou" name="nearYou"> Vicino a
			te
		</div>
		<input type="button" value="Cerca" onclick="ricerca()">
	</div>
	<div id="events">
		<div id="map"></div>
		<div id="list"></div>
	</div>
</body>
</html>