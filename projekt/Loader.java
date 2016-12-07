package projekt;

import java.io.FileReader;
import com.opencsv.CSVReader;

public class Loader {
	
	public static void readFromCsv(QandA choice, String file) throws Exception {
		
		CSVReader reader = new CSVReader(new FileReader(file));

		String[] nextLine;

		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line
			String[] a = nextLine[0].split(";");
			choice.add(a[0], a[1], 5);
		}

		System.out.println(choice.questions);
		System.out.println(choice.odds);

		reader.close();
		
	}

}
