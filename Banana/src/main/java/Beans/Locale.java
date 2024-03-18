package Beans;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Locale{
	private String nome;
	private String organizzatore;
	private Indirizzo indirizzo;

	public Locale(String nome, String organizzatore, Indirizzo indirizzo){
		this.nome=nome;
		this.organizzatore = organizzatore;
		this.indirizzo=indirizzo;
	}

	
	public void setNome(String nome){
		this.nome=nome;
	}

	public String getNome(){
		return this.nome;
	}
	
	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getOrganizzatore(){
		return this.organizzatore;
	}

	public List<Evento> getEventi(){
		List<Evento> listaEventi = new ArrayList<Evento>();
		
		String pathEventi = LocalPaths.pathEventi; //"D:/Gabri/b/web/eventi.json";
		try {
			Reader reader = Files.newBufferedReader(Paths.get(pathEventi));
		    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yy").create();
		    List<Evento> eventi = gson.fromJson(reader, new TypeToken<List<Evento>>(){}.getType());
		    reader.close();
		    
		    for(Evento e : eventi) {		    	
		    	if(e.getLocale().equals(this))
		    		listaEventi.add(e);
		   }		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   		
		
		return listaEventi;
	}
	
	@Override
	public boolean equals(Object o) {
		Locale l = (Locale) o;
//		System.out.println("1" + l.toString());
//		System.out.println("2" + this.toString());
//		if(!this.indirizzo.equals(l.getIndirizzo())) return false;
		if(!this.nome.equals(l.getNome())) return false;
		if(!this.organizzatore.equals(l.getOrganizzatore())) return false;
//		System.out.println("RETURN TURE");
		return true;
	}
	
	@Override
	public String toString() {
		return this.nome + ", " + this.organizzatore + ", " + this.indirizzo.toString();
	}	
}