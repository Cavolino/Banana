package Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Beans.Evento;
import Beans.LocalPaths;


public class InserisciEventoController {
	public void inserisciEvento(Evento evento){
		//get organizzatore
		
		//check locale sia di organizzatore 
		
		//check eventi sovrapposti
		
		//tutto ok si passa al caricamento
		
		try {
			String pathEventi = LocalPaths.pathEventi;//"D:/Gabri/b/web/eventi.json";
		    Reader reader = Files.newBufferedReader(Paths.get(pathEventi));

		    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yy").create();
		    List<Evento> eventi = gson.fromJson(reader, new TypeToken<List<Evento>>(){}.getType());
			eventi.add(evento);
			reader.close();
			
		    File file = new File(pathEventi);
		    Writer writer = new FileWriter(file);
		    gson.toJson(eventi, writer);
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	   
	}
}