package Beans;

import java.util.Date;

public class Evento{	
	private String id;
	private String nome;
	private Date dataInizio;
	private String oraInizio;
	private Date dataFine;
	private String oraFine;
	private String descrizione;
	private String infoAggiuntive;
	private Locale locale;
	//private Stato stato;
	
	//nel costruttore non ho messo lo stato perch� nel file json non so con che criterio metterlo, ci pensiamo poi magari
	public Evento(String id, String nome, Date dataInizio, String oraInizio, 
			Date dataFine, String oraFine, String descrizione, 
			String infoAggiuntive, Locale locale) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataInizio = dataInizio;
		this.oraInizio = oraInizio;
		this.dataFine = dataFine;
		this.oraFine = oraFine;
		this.descrizione = descrizione;
		this.infoAggiuntive = infoAggiuntive;
		this.locale = locale;
	}

	public enum Stato{
		programmato,
		inCorso,
		terminato;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(String oraInizio) {
		this.oraInizio = oraInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getOraFine() {
		return oraFine;
	}

	public void setOraFine(String oraFine) {
		this.oraFine = oraFine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getInfoAggiuntive() {
		return infoAggiuntive;
	}

	public void setInfoAggiuntive(String infoAggiuntive) {
		this.infoAggiuntive = infoAggiuntive;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Stato getStato() {
		if(new Date().before(dataInizio)) return Stato.programmato;
		if(new Date().after(dataFine)) return Stato.terminato;
		
		return Stato.inCorso;
		//to do, check orari e date;
//		if(new Date().equals(dataInizio)) {
//			if()
//		}
		
	}

	public void setStato(Stato stato) {
		//this.stato = stato;
	}	
	
	@Override
	public boolean equals(Object o) {
		Evento e = (Evento)o;
		if(!this.id.equals(e.getId())) return false;
		return true;
		
	}

}