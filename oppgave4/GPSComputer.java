package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START

		for (int i = 0; i < gpspoints.length; i++) {
			if (i != gpspoints.length - 1) {
				distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
			}
		}
			return distance;
		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START

		double V = gpspoints[0].getElevation();

		for (GPSPoint gpspoint : gpspoints) {
			double E = gpspoint.getElevation();
			if (V < E) {
				elevation += E - V;
			}
			V = E;
		}
		return elevation;

		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		// Tid i sluttpunkt - tid i startpunkt = tid brukt mellom de 2 punktene
		return gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
		
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		
		double[] speedTab = new double[gpspoints.length - 1];
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double d = GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
			int    t = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			// gjennomsnittsfart = distanse / tid
			// fart fra m/s til km/h = fart * 3.6
			speedTab[i] = d / t * 3.6;
		}
		return speedTab;

		// TODO - SLUTT

	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO - START
		
		return GPSUtils.findMax(this.speeds());		
		// TODO - SLUTT
		
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO - START
		
		double D = this.totalDistance();
		int    T = this.totalTime();
		return average = D / T * 3.6;
		
		// TODO - SLUTT
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double[] met      = {4.0, 6.0, 8.0, 10.0, 12.0, 16.0};
		double   speedmph = speed * MS;
		double   hr       = (double) secs / 3600;
		if (speedmph < 10) {
			kcal = met[0] * hr * weight;
		} else if (speedmph < 12) {
			kcal = met[1] * hr * weight;
		} else if (speedmph < 14) {
			kcal = met[2] * hr * weight;
		} else if (speedmph < 16) {
			kcal = met[3] * hr * weight;
		} else if (speedmph < 20) {
			kcal = met[4] * hr * weight;
		} else {
			kcal = met[5] * hr * weight;
		}
		return kcal;

		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
		
		return kcal(weight, this.totalTime(), this.averageSpeed());
		// TODO - SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START

		System.out.print("Total Time");
		System.out.println(GPSUtils.formatTime(this.totalTime()));
		
		System.out.print("Total distance");
		System.out.println(GPSUtils.formatDouble(this.totalDistance() / 1000) + " km");
		
		System.out.print("Total elevation");
		System.out.println(GPSUtils.formatDouble(this.totalElevation()) + " m");
		
		System.out.print("Max speed");
		System.out.println(GPSUtils.formatDouble(this.maxSpeed()) + " km/t");
		
		System.out.print("Average speed");
		System.out.println(GPSUtils.formatDouble(this.averageSpeed()) + " km/t");
		
		System.out.print("Energy");
		System.out.println(GPSUtils.formatDouble(this.totalKcal(WEIGHT)) + " kcal");
		
		System.out.println("==============================================");

		
		// TODO - SLUTT
		
	}

	public static double getWEIGHT() {
		// TODO Auto-generated method stub
		return WEIGHT ;
	}

}
