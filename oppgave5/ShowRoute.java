package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
			
		// TODO - START
		
		double maxbre = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minbre = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / Math.abs(maxbre - minbre);

		return ystep;


		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		

				double xstep = this.xstep();
				double ystep = this.ystep();
				System.out.println("xstep: " + xstep + " ystep: " + ystep);

				double minlongitude = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
				double minlatitude  = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
				System.out.println("minlong: " + minlongitude + " minlat: " + minlatitude);

				int points = gpspoints.length;
				double[] longs  = new double[points];
				double[] lats   = new double[points];

				for (int i = 0; i < points; i++) {
					longs[i] = gpspoints[i].getLongitude() - minlongitude;
				}
				for (int i = 0; i < points; i++) {
					lats[i] = gpspoints[i].getLatitude() - minlatitude;
				}


				setColor(0, 255, 0);
				for (int i = 0; i < points; i++) {


					int x = (int) (MARGIN + (longs[i] * xstep));
					int y = (int) (ybase - (lats[i] * ystep));
					if (i == points - 1) {


						setColor(0, 0, 200);
						fillCircle(x, y, 2);
					} else {
						//  regne ut lokasjon til neste punkt
						int nextX = (int) (MARGIN + (longs[i + 1] * xstep));
						int nextY = (int) (ybase - (lats[i + 1] * ystep));
						if (i == 0) {
							// rød sirkel 
							setColor(200, 0, 0);
							fillCircle(x, y, 4);
							setColor(0, 200, 0);
							drawLine(x, y, nextX, nextY);
						} else {
							//linje til neste punkt
							fillCircle(x, y, 2);
							drawLine(x, y, nextX, nextY);
						}
					}

				}
			}
		
		// TODO - SLUTT
	

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		
		String stringTime = String.format("Total Time") +
                GPSUtils.formatTime(gpscomputer.totalTime()) +
                "\n";
String stringDistance = String.format("Total distance") +
                    GPSUtils.formatDouble(gpscomputer.totalDistance() / 1000) + " km\n";

String stringElevation = String.format("Total elevation") +
                     GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m\n";

String stringMaxSpeed = String.format("Max speed") +
                    GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t\n";

String stringAvgSpeed = String.format("Average speed") +
                    GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t\n";

String stringEnergy = String.format("Energy") +
                  GPSUtils.formatDouble(gpscomputer.totalKcal(GPSComputer.getWEIGHT())) +
                  " kcal\n";

drawString(stringTime, MARGIN, MARGIN);
drawString(stringDistance, MARGIN, MARGIN + TEXTDISTANCE);
drawString(stringElevation, MARGIN, MARGIN + TEXTDISTANCE * 2);
drawString(stringMaxSpeed, MARGIN, MARGIN + TEXTDISTANCE * 3);
drawString(stringAvgSpeed, MARGIN, MARGIN + TEXTDISTANCE * 4);
drawString(stringEnergy, MARGIN, MARGIN + TEXTDISTANCE * 5);

		
	}

}
