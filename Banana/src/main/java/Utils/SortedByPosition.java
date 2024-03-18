package Utils;

import java.util.Comparator;

import Beans.Evento;
import Beans.Filtro;

public class SortedByPosition implements Comparator<Evento> {
	private Filtro filtro;	
	
	public SortedByPosition(Filtro filtro) {
		this.filtro = filtro;
	}
	
	@Override
	public int compare(Evento evento, Evento o) {
		// TODO Auto-generated method stub		
		Double lat = filtro.getLatitude();
		Double lon = filtro.getLongitude();
		
		Double lat1 = Double.parseDouble(evento.getLocale().getIndirizzo().getLatitude());
		Double lon1 = Double.parseDouble(evento.getLocale().getIndirizzo().getLongitude());
		
		Double lat2 = Double.parseDouble(o.getLocale().getIndirizzo().getLatitude());
		Double lon2 = Double.parseDouble(o.getLocale().getIndirizzo().getLongitude());
		
		HaversineDistance hd1 = new HaversineDistance(lat, lon, lat1, lon1);
		HaversineDistance hd2 = new HaversineDistance(lat, lon, lat2, lon2);
			
		Double distance1 = hd1.getDistance(); //in km 
		Double distance2 = hd2.getDistance(); //in km 
				
		if(distance1 >= (distance2 - 5) && distance1 <= (distance2 + 5)) return 0; //faccio in modo che gli eventi in un raggio di 5km sono equivalenti di distanza 
		if(distance1 > distance2) return 1;
		return -1;		
	}

}
