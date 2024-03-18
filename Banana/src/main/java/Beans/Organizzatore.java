package Beans;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Organizzatore extends Membro{
	private String documento;
	private boolean verificato;
	
	public Organizzatore(String mail,String username){
		super(mail,username);		
		this.verificato=false;
	}
	
	public Organizzatore(String documento,String mail,String username){
		super(mail,username);
		this.documento=documento;
		this.verificato=false;
	}

	public void setDocumento(String documento){
		this.documento=documento;
	}

	public String getDocumento(){
		return this.documento;
	}

	public boolean isVerificato(){
		return this.verificato;
	}	

	public void setVerificato(boolean verificato){
		this.verificato=verificato;
	}

	//Qua ho usato Gson, dai prossimi user� JSONParser e simili perch� sono pi� semplici da usare
	public List<Locale> getLocali(){
		//Si prende la lista dei locali da un file json, usando come chiave l'username del Locale
		try {
			String pathLocali = LocalPaths.pathLocali; //"D:/Gabri/b/web/locali.json"
		    // create a reader
		    Reader reader = Files.newBufferedReader(Paths.get(pathLocali));
		    // convert JSON array to list of users
		    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yy").create();
		    List<Locale> locali = gson.fromJson(reader, new TypeToken<List<Locale>>(){}.getType());
		    List<Locale> loc = new ArrayList<Locale>();
		    // close reader
		    reader.close();
		    
		    for(Locale l : locali) {
		    	System.out.println(l.getOrganizzatore());
		    	if (l.getOrganizzatore().equals(this.getUsername())) {
		    		loc.add(l);
		    		System.out.println(l.toString());
		    	}
		    	
		    }
		    
		    System.out.println("Ciao:" + this.getUsername());
		    return loc;
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return null;
		
		
		
//		
//		List<Locale> listaLocali = new ArrayList<Locale>();
//		String pathJson = "C:/Users/Davide/Desktop/Ingengeria/3 anno/2-Ingegneria del Software/b/web/locali.json";
//		try (Reader reader = Files.newBufferedReader(Paths.get(pathJson))) {
//			Type listType = new TypeToken<List<Locale>>(){}.getType();
//			listaLocali = new Gson().fromJson(reader, listType);
//			for(Locale l : listaLocali){
//				if(!l.getOrganizzatore().equals(this.getUsername())){
//					listaLocali.remove(l);
//				}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return listaLocali;
	}	

}