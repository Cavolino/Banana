package Utils;

/**
 * This is the implementation Haversine Distance Algorithm between two places
 * 
 * @author ananth R = earth’s radius (mean radius = 6,371km) Δlat = lat2− lat1
 *         Δlong = long2− long1 a = sin²(Δlat/2) +
 *         cos(lat1).cos(lat2).sin²(Δlong/2) c = 2.atan2(√a, √(1−a)) d = R.c
 *
 */

public class HaversineDistance {

	/**
	 * @param args arg 1- latitude 1 arg 2 — latitude 2 arg 3 — longitude 1 arg 4 —
	 *             longitude 2
	 */

	private Double lat1;
	private Double lon1;
	private Double lat2;
	private Double lon2;
	
	private final int R = 6371; // Radious of the earth
	
	public HaversineDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
		this.lat1 = lat1;
		this.lon1 = lon1;
		this.lat2 = lat2;
		this.lon2 = lon2;
	}
	
	public Double getDistance() {
		Double latDistance = toRad(lat2 - lat1);
		Double lonDistance = toRad(lon2 - lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		Double distance = R * c;

		return distance;
	}	

	private static Double toRad(Double value) {
		return value * Math.PI / 180;
	}

}