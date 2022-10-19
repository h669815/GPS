package no.hvl.dat100ptc.oppgave2;

import java.io.*;

import javax.swing.*;

public class GPSDataFileReader {

	// entry separator in CSV files
	private static String SEP_STR = ",";

	private static  String GPSDATA_FORMAT = "time,lat,lon,elevation,accuracy,bearing,speed,satellites,"
			+ "provider,hdop,vdop,pdop,geoidheight,ageofdgpsdata,dgpsid,activity,battery,annotation";

	// location of GPS data files in this Eclipse project
	private static String GPSLOGS_DIR = System.getProperty("user.dir") + "/logs/";

	public static GPSData readGPSFile(String filename) {

		BufferedReader buffre = null;
		GPSData gpsdata = null;

		String time, latitude, longitude, elevation;

		try {

			buffre = new BufferedReader(new FileReader(GPSLOGS_DIR + filename + ".csv"));

			String line = buffre.readLine();

			// first line specifies number of entries in the gps data file
			int G = Integer.parseInt(line);

			// allocate arrays for the right number of entries
			gpsdata = new GPSData(G);

			// skip the description line by simply reading it
			line = buffre.readLine();

			int i = 0;

			line = buffre.readLine();

			while (line != null && i < G) {

				String[] gpsdatapoint = line.split(SEP_STR);

				time = gpsdatapoint[0];
				latitude = gpsdatapoint[1];
				longitude = gpsdatapoint[2];
				elevation = gpsdatapoint[3];
				
				gpsdata.insert(time,latitude,longitude,elevation);

				line = buffre.readLine();
				i++;
			}

		} catch (FileNotFoundException e) {
		    JOptionPane.showMessageDialog(null,"GPS filen " + filename + "finnes ikke");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"GPS filen " + filename + "kunne ikke leses");
			e.printStackTrace();
		} finally {
			if (buffre != null) {
				try {
					buffre.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return gpsdata;
	}
}