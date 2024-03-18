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

<%@ page import="Servlet.Login"%>

<%
Utente utente = ((Utente) session.getAttribute("utente"));
String username = utente.getUsername();
//String username = "test";
//List<String> eventi = utente.getEventiInteresse();
//System.out.println(utente);
//System.out.println(username);
String pathJson = LocalPaths.pathEventiInteresse;// "D:/Gabri/b/web/eventiInteresse.json";
String pathJsonEventi = LocalPaths.pathEventi;//"D:/Gabri/b/web/eventi.json";
try (Reader reader = new FileReader(pathJson)) {
	//List<Membro> items = new List<Membro>();
	Type setType = new TypeToken<List<Membro>>() {
	}.getType();

	Gson gson = new GsonBuilder().setDateFormat("dd/MM/yy").create();

	List<Membro> items = gson.fromJson(new JsonReader(reader), setType);
	/*for (Membro i : items) {
		//System.out.println(i.getUsername());
		if ( true ){//i.getUsername().equals(username)) {
	//System.out.println(i.getEventi().length);
		}//
	}*/
	Reader reader2 = new FileReader(pathJsonEventi);
	Type setType2 = new TypeToken<List<Evento>>() {
	}.getType();
	List<Evento> eventi = gson.fromJson(new JsonReader(reader2), setType2);
%>


<!DOCTYPE html>
<html lang="en">
<head>
<style>
table, th, td {
	border: 1px solid black;
	border-radius: 10px;
	text-align: center; 
    vertical-align: middle;
}

</style>
<meta charset="UTF-8">
<title>Membro Page</title>

</head>
<body>
	<jsp:include page="/fragments/navbar.jsp"></jsp:include>
	<h2>
		Benvenuto
		<%=username%></h2>
	<br>
	<h2>Visualizza eventi interesse</h2>
	<br>

	<div id="content">
		<table style="width:100%">
			<tr>
				<th>IdEvento</th>
				<th>Nome</th>
				<th>DataInizio</th>
				<th>OraInizio</th>
				<th>DataFine</th>
				<th>OraFine</th>
				<th>Descrizione</th>
				<th>Informazioni Aggiuntive</th>
				<th>Condividi</th>
			</tr>
			<%
			for (Membro i : items) {
				if (i.getUsername().equals(username)) {
					for (String e : i.getEventi()) {
				for (Evento ev : eventi) {
					System.out.println(ev.getId());
					if (ev.getId().equals(e)) {
			%>


			<tr>
				<td><%=ev.getId()%></td>
				<td><%=ev.getNome()%></td>
				<td><%=ev.getDataInizio()%></td>
				<td><%=ev.getOraInizio()%></td>
				<td><%=ev.getDataFine()%></td>
				<td><%=ev.getOraFine()%></td>
				<td><%=ev.getDescrizione()%></td>
				<td><%=ev.getInfoAggiuntive()%></td>
				<td><p>
						<button class='but' value='<%=ev.getNome()%>'>Share</button>
					</p></td>
			</tr>
			<%
			}
			}
			}
			}
			}
			%>
		</table>
		<p class="result"></p>

		<div class="clear">
			<p>&nbsp;</p>
		</div>
	</div>

	<a title="Inizia una ricerca" href="../ricerca.jsp" target="_blank">Inizia
		ricerca!</a>
	<%
	} catch (FileNotFoundException e) {
	e.printStackTrace();
	} catch (IOException e1) {
	e1.printStackTrace();
	}
	%>


</body>
</html>

<script>
		const btns = document.querySelectorAll('.but');
		const resultPara = document.querySelector('.result');
		// Share must be triggered by "user activation"
		btns.forEach(item => {
  			item.addEventListener('click', async () => {
  				 try {
  					var shareData = {
  						  title: 'Condividi Evento '+ item.value +' su Banana App',
  						  text: 'IdEvento: '+ item.value,
  						  url: 'https://banana/events/'+ item.value
  						}
  					 
  				    await navigator.share(shareData)
  				  } catch(err) {
  				    resultPara.textContent = 'Error: ' + err
  				  }
  			})
		})
	
</script>