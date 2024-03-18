package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Beans.Filtro;
import Beans.LocalPaths;
import Beans.Evento;

import Utils.SortedByDate;
import Utils.SortedByNomeLocale;
import Utils.SortedByPosition;
import Utils.SortedByTime;

public class Ricerca extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gson g;
	
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		this.g = new Gson();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("filtro") == "")
			response.sendRedirect("ricerca.jsp");
		
		System.out.println(request.getParameter("filtro"));				
		Filtro filtro = g.fromJson(request.getParameter("filtro"), Filtro.class);
		System.out.println(filtro.toString());
		
		List<Evento> eventi = this.readAllEventi(); // a lot of events
		
		if(eventi == null) //error?
			return;		

		// come priorita` del filtro abbiamo la citta`, poi il nome del locale, il giorno e infine l'orario
		//ORARIO
		if(!filtro.getOraInizio().isEmpty() && filtro.getOraInizio() != "") {
			eventi.sort(new SortedByTime(filtro));
			System.out.println("SORTED BY TIME -> " + filtro.getOraInizio());
			for(Evento e : eventi) {			
				System.out.println("-> " + e.getOraInizio());
			}
		}
		
		//DATA
		if(!filtro.getDataInizio().isEmpty() && filtro.getDataInizio() != "") {
			eventi.sort(new SortedByDate(filtro));
			System.out.println("SORTED BY DATE -> " + filtro.getDataInizio());
			for(Evento e : eventi) {			
				System.out.println("-> " + e.getDataInizio());
			}
		}
		
		//NOME
		if(!filtro.getNomeLocale().isEmpty() && filtro.getNomeLocale() != "") {
			eventi.sort(new SortedByNomeLocale(filtro));
			System.out.println("SORTED BY NOME LOCALE -> " + filtro.getNomeLocale());
			for(Evento e : eventi) {			
				System.out.println("-> " + e.getLocale().getNome());
			}
		}
		
		//CITTA
		if(!filtro.getNomeCitta().isEmpty() && filtro.getNomeCitta() != "" || filtro.getLatitude() != 0 || filtro.getLongitude() != 0) {			
			eventi.sort(new SortedByPosition(filtro));
			System.out.println("SORTED BY POSITION -> " + filtro.getNomeCitta());
			for(Evento e : eventi) {			
				System.out.println("-> " + e.getLocale().getIndirizzo().getCitta());
			}
		}
		
		Evento res[] = new Evento[1000]; //da cambiare la grandezza

		int c = 0;
		for(Evento e : eventi) {
			res[c] = e;
			c++;
		}
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String o = g.toJson(res);

		System.out.println(o);
		out.print(o);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("ricerca.jsp");
	}

	private Evento readEvento() {
		return null;	
	}
	
	private List<Evento> readAllEventi(){
		try {
			String pathEventi = LocalPaths.pathEventi;//"D:/Gabri/b/web/eventi.json"
		    // create a reader
		    Reader reader = Files.newBufferedReader(Paths.get(pathEventi));
		    // convert JSON array to list of users
		    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yy").create();
		    List<Evento> eventi = gson.fromJson(reader, new TypeToken<List<Evento>>(){}.getType());

//		    //test reading locale funziona
//		    reader = Files.newBufferedReader(Paths.get("C:/Users/Davide/Desktop/Ingengeria/3 anno/2-Ingegneria del Software/b/web/locali.json"));
//		    // convert JSON array to list of users
//		    gson = new GsonBuilder().setDateFormat("dd/MM/yy").create();
//		    List<Locale> locali = gson.fromJson(reader, new TypeToken<List<Locale>>(){}.getType());		    
//		    for(Locale l : locali) {
//		    	System.out.println(l.getNome());
//		    	System.out.println(l.getOrganizzatore());
//		    	System.out.println(l.getIndirizzo().toString());
//		    }
		    
		    
		    // close reader
		    reader.close();
		    return eventi;
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return null;
	}
}
