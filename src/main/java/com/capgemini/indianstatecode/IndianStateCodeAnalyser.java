package com.capgemini.indianstatecode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.capgemini.indianstatecode.StateCodeException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IndianStateCodeAnalyser {
	private static final String CSV_FILE = "H:\\CapgTutorial\\Census\\IndiaStateCode.csv";

	public int readData(String dataset) throws StateCodeException {
		int noOfEntries = 0;
		int counter = 0;
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataset));
			Reader readFile = Files.newBufferedReader(Paths.get(dataset));
			CsvToBean<IndianStateCode> user = new CsvToBeanBuilder<IndianStateCode>(readFile)
					.withType(IndianStateCode.class).withIgnoreLeadingWhiteSpace(true).build();

			while ((line = br.readLine()) != null) {
				if (!line.contains(","))
					throw new StateCodeException(ExceptionType.INVALID_DELIMITER,
							"Invalid Delimiter present in the File!! ");
				if (counter == 0) {
					String[] headerArray = line.split(",");
					if (!(headerArray[1].equals("StateName") && headerArray[2].equals("TIN")
							&& headerArray[3].equals("StateCode")))
						throw new StateCodeException(ExceptionType.INVALID_HEADER,
								"Invalid headers present in the File!!");
					counter++;
				}
			}
			br.close();
			Iterator<IndianStateCode> userIterator = user.iterator();
			while (userIterator.hasNext()) {
				IndianStateCode csvuser = userIterator.next();
				System.out.println(csvuser);
				System.out.println("");
				noOfEntries++;
			}
		} catch (IOException e) {
			throw new StateCodeException(StateCodeException.ExceptionType.INVALID_FILE_PATH,
					"Invalid File Location given!!");

		}
		return noOfEntries;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Indian State Code analyser program!");
		System.out.println("==================================================");
		IndianStateCodeAnalyser data = new IndianStateCodeAnalyser();
		try {
			data.readData(CSV_FILE);

		} catch (StateCodeException e) {
			e.printStackTrace();
		}
	}

}
