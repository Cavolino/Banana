package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import Beans.Evento;
import Beans.Filtro;

public class SortedByTime implements Comparator<Evento> {
	public Filtro filtro;
	
	public SortedByTime(Filtro filtro) {
		this.filtro = filtro;
	}

	@Override
	public int compare(Evento o1, Evento o2) {
		// TODO Auto-generated method stub
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		try {
			Date filterTime = parser.parse(filtro.getOraInizio());
			Date time1 = parser.parse(o1.getOraInizio());
			Date time2 = parser.parse(o2.getOraInizio());
			
			int comp1 = time1.compareTo(filterTime); 
			int comp2 = time2.compareTo(filterTime);
			if( comp1 == comp2 ) return 0;
			if( comp1 > comp2 ) return -1;
			return 1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR PARSING TIME");
		}		
		
		return 0;
	}
}
