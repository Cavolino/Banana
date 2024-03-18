package Utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.Date;

import Beans.Evento;
import Beans.Filtro;

public class SortedByDate implements Comparator<Evento> {
	private Filtro filtro;
	
	public SortedByDate(Filtro filtro) {
		this.filtro = filtro;
	}

	@Override
	public int compare(Evento o1, Evento o2) {
		// TODO Auto-generated method stub
		
		try {
			Date filterDate = Date.from(LocalDate.parse(filtro.getDataInizio()).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
			int comp1 = o1.getDataInizio().compareTo(filterDate); 
			int comp2 = o2.getDataInizio().compareTo(filterDate);
			if( comp1 == comp2 ) return 0;
			if( comp1 > comp2 ) return -1;		
		}catch(DateTimeParseException e ) {
			System.out.println("ERRORE PARSING DATE");
		}
		return 1;
	}
}
