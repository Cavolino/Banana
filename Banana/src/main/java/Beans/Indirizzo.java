package Beans;

public class Indirizzo{
	private String via;
	private String civico;
	private String citta;
	private String nazione;
	private String latitude;
	private String longitude;

	public Indirizzo(String via,String civico,String citta,String nazione, String lat, String lng){
		this.via=via;
		this.civico=civico;
		this.citta=citta;
		this.nazione=nazione;
		this.latitude = lat;
		this.longitude = lng;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	public String getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(String lat) {
		this.latitude = lat;
	}
	
	public String getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(String lng) {
		this.longitude = lng;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Indirizzo other = (Indirizzo) obj;
		if (citta == null) {
			if (other.citta != null)
				return false;
		} else if (!citta.equals(other.citta))
			return false;
		if (civico == null) {
			if (other.civico != null)
				return false;
		} else if (!civico.equals(other.civico))
			return false;
		if (nazione == null) {
			if (other.nazione != null)
				return false;
		} else if (!nazione.equals(other.nazione))
			return false;
		if (via == null) {
			if (other.via != null)
				return false;
		} else if (!via.equals(other.via))
			return false;
		return true;
	}

	
//	public Locale getLocale(){
//		String pathJson = "/locali.json";
//		Gson gson = new Gson();
//		Locale locale=null;
//		JSONParser jsonParser = new JSONParser();
//		try (FileReader reader = new FileReader(pathJson)){
//			Object obj = jsonParser.parse(reader);
//            		JSONArray locali  = (JSONArray) obj;
//           		for(int i=0;i<locali.size();++i) {
//           			JSONObject jo = (JSONObject) locali.get(i);
//           			JSONObject indirizzoJson = (JSONObject) jo.get("indirizzo");
//           			Indirizzo indirizzo = gson.fromJson(indirizzoJson.toString(), Indirizzo.class);
//           			if(indirizzo.equals(this)){
//           				String nome = (String) jo.get("nome");
//           				locale = new Locale(nome,this);
//           				break;
//				}
//			}
//		} 
//		catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return locale;
//	}

	
}