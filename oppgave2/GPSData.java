package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int G) {

		
		gpspoints = new GPSPoint[G];
		antall = 0;
		//throw new UnsupportedOperationException(TODO.construtor("GPSData"));

	}

	public GPSPoint[] getGPSPoints() {
		
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {
		
		boolean insert1 = false;
		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			insert1 = true;
		}

		return insert1;
	}
	
	

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		

		
		
		GPSPoint gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
		boolean insert=insertGPS(gpspoint);
		
		return insert;

	}

	public void printIt() {

		System.out.println("====== Konvertert GPS Data - START ======");


		//throw newUnsupportedOperationException(TODO.method());
		for (GPSPoint gpspoint : gpspoints) {
			System.out.println(gpspoint.toString());
		

	}
		System.out.println("====== Konvertert GPS Data - SLUTT ======");

}
	}
