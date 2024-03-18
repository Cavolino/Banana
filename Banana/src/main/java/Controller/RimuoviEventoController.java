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

public class RimuoviEventoController {

	public boolean rimuoviEvento(Evento evento){
		String pathEventi = LocalPaths.pathEventi;//"D:/Gabri/b/web/eventi.json";
		try {
			Reader reader = Files.newBufferedReader(Paths.get(pathEventi));
		    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yy").create();
		    List<Evento> eventi = gson.fromJson(reader, new TypeToken<List<Evento>>(){}.getType());
		    reader.close();		    
		    if(eventi.remove(evento)) {		    	
			    File file = new File(pathEventi);
			    Writer writer = new FileWriter(file);
			    gson.toJson(eventi, writer);
			    writer.close();		    	
		    	return true;
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   		
		return false;			
//		boolean result=false;
//		String pathJson = "/locali.json";
//		Gson gson = new Gson();
//		try (FileReader file = new FileReader(pathJson)) {
//			JSONParser jsonParser = new JSONParser();
//			Object obj = jsonParser.parse(file);
//			JSONArray locali  = (JSONArray) obj;
//			for(int i=0;i<locali.size();++i) {
//				JSONObject jo = (JSONObject) locali.get(i);
//				JSONObject indirizzoJson = (JSONObject) jo.get("indirizzo");
//				Indirizzo indirizzo = gson.fromJson(indirizzoJson.toString(), Indirizzo.class);
//				if(indirizzo.equals(evento.getLocale().getIndirizzo())){
//					//significa che il locale a cui vogliamo togliere l'evento � lo stesso della iterazione
//					JSONArray eventi = (JSONArray)jo.get("eventi");
//					//inizializzazione evento in formato json
//					JSONObject eventoJson = new JSONObject();
//					eventoJson.put("id", evento.getId());
//					eventoJson.put("name", evento.getNome());
//					eventoJson.put("dataInizio", evento.getDataInizio().toString());
//					eventoJson.put("oraInizio", evento.getOraInizio().toString());	
//					eventoJson.put("dataFine", evento.getOraFine().toString());
//					eventoJson.put("oraFine", evento.getDataFine().toString());
//					eventoJson.put("descrizione", evento.getDescrizione());
//					eventoJson.put("infoAggiuntive", evento.getInfoAggiuntive());
//				
//					result = eventi.remove(eventoJson);
//					file.close();
//						
//					gson = new GsonBuilder().setPrettyPrinting().create();
//					String prettyJsonString = gson.toJson(locali);
//					File myFoo = new File(pathJson);
//					FileOutputStream fooStream = new FileOutputStream(myFoo, false);
//					fooStream.write(prettyJsonString.getBytes());
//					fooStream.close();
//					
//				}
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
		
	}
	
	
}