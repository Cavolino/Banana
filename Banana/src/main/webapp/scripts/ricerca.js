const nomeLocale = document.getElementById("nomeLocale");
const dataInizio = document.getElementById("dataInizio");
const oraInizio = document.getElementById("oraInizio");
const nomeCitta = document.getElementById("nomeCitta");
const nearYou = document.getElementById("nearYou");

let lat;
let lng;
nearYou.addEventListener("click", (e) => {
	navigator.permissions.query({ name: 'geolocation' });
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(loadPosition);
	} else {
		alert("Errore");
	}

	function loadPosition(position) {
		nomeCitta.disabled = nearYou.checked;
		lat = position.coords.latitude;
		lng = position.coords.longitude;
		map.setView([lat, lng], 13);
		L.marker([lat, lng]).addTo(map);
		console.log(lat, lng);
	}
});

const filters = document.getElementById("filters");
filters.addEventListener("click", (e) => {
	var fc = document.getElementById("filters-content");
	fc.style.display = (fc.style.display == "none") ? "block" : "none";
});

const ricerca = () => {
	var filtro;
	if (nomeCitta.value != "") {
		var queryString = `q=${nomeCitta.value}`;
		var pApi = "https://photon.komoot.io/api/?" + queryString;

		fetch(pApi).then((res) => {
			res.json()
				.then((data) => {
					lat = data.features[0].geometry.coordinates[1];
					lng = data.features[0].geometry.coordinates[0];
					filtro = {
						nomeLocale: nomeLocale.value,
						dataInizio: dataInizio.value,
						oraInizio: oraInizio.value,
						nomeCitta: nomeCitta.value,
						latitude: lat,
						longitude: lng
					}
					continueRicerca(filtro);
				}) //json parsing
				.catch((e) => {
					//citta non trovata		                
				});
		});
	} else {
		if (!nearYou.checked) {
			lat = 0;
			lng = 0;
		}
		filtro = {
			nomeLocale: nomeLocale.value,
			dataInizio: dataInizio.value,
			oraInizio: oraInizio.value,
			nomeCitta: nomeCitta.value,
			latitude: lat,
			longitude: lng
		}
		continueRicerca(filtro);
	}
}

const continueRicerca = (filtro) => {
	const url = '/Banana/ricerca';
	const data = new URLSearchParams([
		["filtro", JSON.stringify(filtro)],
	]);

	$.post({
		url: url,
		data: data.toString(),
		success: function(response) {
			console.log(response);
			document.getElementById("list").innerHTML = "";

			var latitude = 0;
			var longitude = 0;
			for (const i in response) {
				//qua abbiamo 0,1,2,3...							
				createEvent(response[i]);
				for (const key in response[i].locale.indirizzo) {
					//console.log(key);
					if (key == "latitude")
						latitude = response[i].locale.indirizzo[key];
					else if (key == "longitude") {
						longitude = response[i].locale.indirizzo[key];
						if (i == 0) //first element
							map.setView([latitude, longitude], 13);
						L.marker([latitude, longitude]).addTo(map).on('click', function(e) {
							document.querySelectorAll(".event").forEach((item) => {
								item.style.backgroundColor = "white";
							})
							document.getElementById(response[i].id).style.backgroundColor = "#fdff77";
						});
						console.log(latitude, longitude);
					}
				}
			}
		}
	});
}

const createEvent = (evento) => {
	if (!evento) return;
	var div = document.createElement("div");
	div.setAttribute("class", "event");
	div.setAttribute("id", evento.id);

	var locale = document.createElement("div");
	locale.setAttribute("class", "locale");
	locale.innerHTML = evento.locale.nome;

	var title = document.createElement("div");
	title.setAttribute("class", "title");
	title.innerHTML = evento.nome;

	var indirizzo = document.createElement("div");
	indirizzo.setAttribute("class", "indirizzo");
	indirizzo.innerHTML = evento.locale.indirizzo.via + " " + evento.locale.indirizzo.civico + ", " + evento.locale.indirizzo.citta;

	var date = document.createElement("div");
	date.setAttribute("class", "date");
	date.innerHTML = evento.dataInizio;

	var time = document.createElement("div");
	time.setAttribute("class", "time");
	time.innerHTML = "Dalle " + evento.oraInizio + " alle " + evento.oraFine;

	var description = document.createElement("div");
	description.setAttribute("class", "description");
	description.innerHTML = evento.descrizione;

	var infoAggiuntive = document.createElement("div");
	infoAggiuntive.setAttribute("class", "infoAggiuntive");
	infoAggiuntive.innerHTML = evento.infoAggiuntive;

	var utils = document.createElement("div");
	utils.setAttribute("class", "utils");

	var condividi = document.createElement("input");
	condividi.setAttribute("type", "button");
	condividi.setAttribute("class", "condividi");
	condividi.setAttribute("value", "Condividi");
	condividi.addEventListener("click", () => condividiEvento(evento.nome));

	var mostraInteresse = document.createElement("input");
	mostraInteresse.setAttribute("type", "button");
	mostraInteresse.setAttribute("class", "mostraInteresse");
	mostraInteresse.setAttribute("value", "Aggiungi ai preferiti");
	mostraInteresse.addEventListener("click", (e) => eventAction(e, "mostraInteresse"));

	utils.appendChild(condividi);
	utils.appendChild(mostraInteresse);

	
	div.appendChild(title);
	div.appendChild(locale);
	div.appendChild(indirizzo);
	div.appendChild(date);
	div.appendChild(time);
	div.appendChild(description);
	div.appendChild(infoAggiuntive);
	div.appendChild(utils);

	document.getElementById("list").appendChild(div);
}

const eventAction = (e, type) => {
	var IdEvento = e.target.parentElement.parentElement.id;
	console.log(IdEvento);

	const url = '/Banana/' + type;
	const data = new URLSearchParams([
		["IdEvento", IdEvento],
	]);

	$.post({
		url: url,
		data: data.toString(),
		success: function(response) {
			console.log(response);
		}
	});
}