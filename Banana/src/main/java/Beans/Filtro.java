package Beans;

public class Filtro {
	private String nomeLocale;
	private String dataInizio;
	private String oraInizio;	
	private String nomeCitta;
	private double latitude;
	private double longitude;
		
	public Filtro(){}
	
	public String getNomeLocale() {
		return nomeLocale;
	}
	public void setNomeLocale(String nomeLocale) {
		this.nomeLocale = nomeLocale;
	}
	public String getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	public String getOraInizio() {
		return oraInizio;
	}
	public void setOraInizio(String oraInizio) {
		this.oraInizio = oraInizio;
	}
	public String getNomeCitta() {
		return nomeCitta;
	}
	public void setNomeCitta(String nomeCitta) {
		this.nomeCitta = nomeCitta;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return this.nomeLocale + ", " + this.dataInizio + ", " + this.oraInizio + ", " + this.latitude + ", " + this.longitude + ", " + this.nomeCitta;
	}
}
