package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] a) {

		double max; 
		
		max = a[0];
		
		for (double d : a) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] a) {

		double min;
		
		min = a[0];
		
		for (double d : a) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
		

		// TODO - START

	//	throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] latitudes = new double[gpspoints.length];
		for (int i = 0; i<gpspoints.length; i++) {
			latitudes[i]= gpspoints[i].getLatitude();
		}
		return latitudes;
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double[] longs = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			longs[i] = gpspoints[i].getLongitude();
		}
		return longs;
	}
		
		// TODO - SLUTT

	


	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		 int R = 6371000;// jordens radius


		latitude1  = Math.toRadians(gpspoint1.getLatitude());
		
		latitude2  = Math.toRadians(gpspoint2.getLatitude());
		
		longitude1 = Math.toRadians(gpspoint1.getLongitude());
		
		longitude2 = Math.toRadians(gpspoint2.getLongitude());
		
		
		double deltaLong = longitude2 - longitude1;

		double deltaLat  = latitude2 - latitude1;

		double a = Math.pow(Math.sin(deltaLat / 2), 2) +
		           Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(deltaLong / 2), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		d = R * c;
		return d;


	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		speed = distance(gpspoint1, gpspoint2) / secs * 3.6;
		return speed;
		
		

	}

	public static String formatTime(int secs) {


		// TODO - START
		final String TIMESEP = " : ";
		String timestr = String.format("%02d", secs / 3600) + TIMESEP +
		                 String.format("%02d", (secs % 3600) / 60) + TIMESEP +
		                 String.format( "%02d", (secs % 3600) % 60);
		//                      "%10s"
		timestr = String.format( "%" + TEXTWIDTH +"s", timestr);
		return timestr;
	
				

		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str =String.format("%2f", d);

		// TODO - START

		str = String.format("%" + TEXTWIDTH + "s", str);
		return str;
		// TODO - SLUTT
		
	}
}
