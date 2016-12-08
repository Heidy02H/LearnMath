package projekt;

import java.io.FileReader;
import com.opencsv.CSVReader;

/**
 * This class gives the program to read a CSV file and load it it to a QandA
 * object
 * 
 * Uses OpenCSV library from http://opencsv.sourceforge.net/
 *
 * @author Heidi Koppel
 *
 */
public class Loader {
	/**
	 * Used the example code from http://opencsv.sourceforge.net/
	 * 
	 * @param choice
	 *            The designated QandA object
	 * @param file
	 *            The name of the file where we want to get information from
	 * @throws Exception
	 *             File not chosen exception
	 * 
	 */
	public static void readFromCsv(QandA choice, String file) throws Exception {

		CSVReader reader = new CSVReader(new FileReader(file));

		String[] nextLine;

		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line
			String[] a = nextLine[0].split(";");
			choice.add(a[0], a[1], 5);
		}
		reader.close();
	}
}
