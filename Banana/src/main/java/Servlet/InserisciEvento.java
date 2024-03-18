package Servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Evento;
import Beans.Indirizzo;
import Beans.Locale;
import Controller.InserisciEventoController;

public class InserisciEvento extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InserisciEventoController iec = new InserisciEventoController();
		DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		//prendere l'organizzatore
		
		String nome = request.getParameter("nome");
		try {
			Date dataInizio = sourceFormat.parse(request.getParameter("dataInizio"));
			LocalTime oraInizio = LocalTime.parse(request.getParameter("oraInizio"));
			Date dataFine = sourceFormat.parse(request.getParameter("dataFine"));
			LocalTime oraFine = LocalTime.parse(request.getParameter("oraFine"));
			String descrizione = request.getParameter("descrizione");
			String infoAggiuntive = request.getParameter("infoAggiuntive");
			
			String nomeLocale = request.getParameter("nomeLocale");
			
			String via = request.getParameter("via");
			String civico = request.getParameter("civico");
			String citta = request.getParameter("citta");
			String nazione = request.getParameter("nazione");
			
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			
			Indirizzo indirizzo = new Indirizzo(via, civico, citta, nazione, latitude, longitude);
			Locale locale = new Locale(nomeLocale, "test", indirizzo);
			
			//Randomizzo l'id (pi� o meno)
			Random r = new Random();
			int n=r.nextInt();
			char c_uno = (char)(r.nextInt(26) + 'a');
			char c_due = (char)(r.nextInt(26) + 'a');
			String id = String.valueOf(c_uno)+String.valueOf(c_due)+String.valueOf(n);
			
			Evento evento = new Evento(id, nome, dataInizio, oraInizio.toString(), dataFine, oraFine.toString(), 
					descrizione, infoAggiuntive, locale);
			//boolean result = 
					iec.inserisciEvento(evento);
	
			response.sendRedirect(request.getContextPath() + "/pages/ViewInserisciEvento.jsp?inserito=true");

			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}