package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Beans.Evento;
import Beans.Indirizzo;
import Beans.LocalPaths;
import Beans.Locale;
import Controller.RimuoviEventoController;

public class RimuoviEvento extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RimuoviEventoController rec = new RimuoviEventoController();
		
		String id = request.getParameter("id");
		
		String pathEventi = LocalPaths.pathEventi;//"D:/Gabri/b/web/eventi.json";
	    Reader reader = Files.newBufferedReader(Paths.get(pathEventi));

	    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yy").create();
	    List<Evento> eventi = gson.fromJson(reader, new TypeToken<List<Evento>>(){}.getType());
	    reader.close();
	    
	    for(Evento e : eventi) {
	    	System.out.println("PRE RIMOZIONE");
	    	System.out.println(e.getId() + ", " + id);
	    	if(e.getId().equals(id)) {
	    		rec.rimuoviEvento(e);
	    	}
	    }
	    
	    response.sendRedirect(request.getContextPath() + "/pages/ViewEliminaEvento.jsp?rimosso=true");
	}
}
