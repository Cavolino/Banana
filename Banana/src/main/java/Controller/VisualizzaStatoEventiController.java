//package Controller;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.time.LocalTime;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import com.google.gson.Gson;
//
//import Beans.Evento;
//import Beans.Indirizzo;
//import Beans.Locale;
//import Beans.Stato;
//import Beans.Organizzatore;
//
//public class VisualizzaStatoEventiController {
//	private Evento createEvento(JSONArray eventi,int j,Locale locale) {
//		Evento e=null;
//		try {
//			String id =(String) ((JSONObject)eventi.get(j)).get("id");
//			String nome = (String)((JSONObject)eventi.get(j)).get("name");
//			Date  dataInizio = (Date)((JSONObject)eventi.get(j)).get("dataInizio");
//			LocalTime oraInizio = (LocalTime) ((JSONObject)eventi.get(j)).get("oraInizio");
//			Date dataFine = (Date) ((JSONObject)eventi.get(j)).get("dataFine");
//			LocalTime oraFine = (LocalTime) ((JSONObject)eventi.get(j)).get("oraFine");
//			String descrizione = (String) ((JSONObject)eventi.get(j)).get("descrizione");
//			String infoAggiuntive = (String) ((JSONObject)eventi.get(j)).get("infoAggiuntive");
//			//e=(Evento)eventi.getJSONObject(j)
//			//forse si può fare pure così e me ne sono accorto troppo tardi
//			e = new Evento(id,nome,dataInizio,oraInizio.toString(),dataFine,oraFine.toString(),descrizione,infoAggiuntive,locale);
//		}catch(Exception ec) {
//			ec.printStackTrace();
//		}
//		return e;
//	}
//	
//	public Map<Evento,Stato> visualizzaStatoEventi(Organizzatore organizzatore){
//		
//		Map<Evento,Stato> mappaStato = new HashMap<Evento,Stato>();
////		String pathJson = "/Users/leo/Desktop/locali.json";
////		Gson gson = new Gson();
////		try (FileReader file = new FileReader(pathJson)) {
////			JSONParser jsonParser = new JSONParser();
////			Object obj = jsonParser.parse(file);
////			JSONArray locali  = (JSONArray) obj;
////			for(int i=0;i<locali.size();++i) {
////				JSONObject jo = (JSONObject) locali.get(i);
////				JSONObject indirizzoJson = (JSONObject) jo.get("indirizzo");
////				Indirizzo indirizzo = gson.fromJson(indirizzoJson.toString(), Indirizzo.class);
////				if(indirizzo.getLocale().getOrganizzatore().equals(organizzatore.getUsername())){
////					JSONArray eventi = (JSONArray)jo.get("eventi");
////					for(int j=0;j<eventi.size();++j){
////						Evento e = createEvento(eventi,j,indirizzo.getLocale());
////						if(LocalTime.parse(e.getOraFine()).isBefore(LocalTime.now())){
////							mappaStato.put(e, Stato.TERMINATO);
////						}
////						else if(LocalTime.parse(e.getOraFine()).isAfter(LocalTime.now()) && LocalTime.parse(e.getOraInizio()).isBefore(LocalTime.now())) {
////							mappaStato.put(e, Stato.IN_CORSO);
////						}else {
////							mappaStato.put(e, Stato.PROGRAMMATO);
////						}
////					}
////				}
////			}
////			
////		} catch (IOException e) {
////			e.printStackTrace();
////		} catch (ParseException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		return mappaStato;
//	}
//		
//}