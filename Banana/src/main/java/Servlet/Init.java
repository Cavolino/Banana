package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.LocalPaths;
import Beans.Utente;


public class Init extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Utente> utentiRegistrati;
	private boolean initialization = false;
	
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		
		if(this.initialization) return;
		
		this.initialization = true;
		
		System.out.println("Inizializzazione del server");
		System.out.println(LocalPaths.basePath);
		
		utentiRegistrati = new HashMap<>();

		// Utenti
		Utente u = new Utente("Cavolino", "Ciao");
		utentiRegistrati.put(u.getUsername(), u);
		u = new Utente("Pippo", "Paperino");
		utentiRegistrati.put(u.getUsername(), u);
		u = new Utente("Pluto", "Paperino");
		utentiRegistrati.put(u.getUsername(), u);
		u = new Utente("Paperino", "Paperino");
		utentiRegistrati.put(u.getUsername(), u);
		u = new Utente("admin", "admin");
		utentiRegistrati.put(u.getUsername(), u);
		u = new Utente("test", "test");
		utentiRegistrati.put(u.getUsername(), u);
		
		this.getServletContext().setAttribute("utentiRegistrati", utentiRegistrati);

		// Gruppi
//		Map<String, GruppoUtenti> gruppi = new HashMap<String, GruppoUtenti>();
//		GruppoUtenti gu = new GruppoUtenti();
//		gu.setId("admin");
//		gruppi.put(gu.getId(), gu);
//		gu = new GruppoUtenti();
//		gu.setId("g01");
//		gruppi.put(gu.getId(), gu);
//		gu = new GruppoUtenti();
//		gu.setId("g02");
//		gruppi.put(gu.getId(), gu);
//		this.getServletContext().setAttribute("gruppi", gruppi);
//
//		// Catalogo
//		Catalogo catalogo = new Catalogo();
//		Item i = new Item();
//		i.setItemId("001");
//		i.setDescrizione("Biglietto");
//		i.setPrezzo(4);
//		i.setQuantita(1000);
//		catalogo.add(i);
//		this.getServletContext().setAttribute("catalogo", catalogo);
		this.getServletContext().setAttribute("init", this);

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/ricerca.jsp");
	}

}
