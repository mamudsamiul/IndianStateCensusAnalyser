package com.capgemini.census;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.capgemini.census.CensusAnalyserException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IndianStateAnalyser {
	private static final String CSV_FILE = "H:\\CapgTutorial\\Census\\IndianStateCensusData .csv";

	public int readData(String dataset) throws CensusAnalyserException {
		int noOfEntries = 0;
		int counter = 0;
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataset));
			Reader readFile = Files.newBufferedReader(Paths.get(dataset));
			CsvToBean<IndianStateCensus> user = new CsvToBeanBuilder<IndianStateCensus>(readFile)
					.withType(IndianStateCensus.class).withIgnoreLeadingWhiteSpace(true).build();

			while ((line = br.readLine()) != null) {
				if (!line.contains(","))
					throw new CensusAnalyserException(ExceptionType.INVALID_DELIMITER,
							"Invalid Delimiter present in the File!! ");
				if (counter == 0) {
					String[] headerArray = line.split(",");
					if (!(headerArray[0].equals("State") && headerArray[1].equals("Population")
							&& headerArray[2].equals("AreaInSqKm") && headerArray[3].equals("DensityPerSqKm")))
						throw new CensusAnalyserException(ExceptionType.INVALID_HEADER,
								"Invalid headers present in the File!!");
					counter++;
				}
			}
			br.close();
			Iterator<IndianStateCensus> userIterator = user.iterator();
			while (userIterator.hasNext()) {
				IndianStateCensus csvuser = userIterator.next();
				System.out.println(csvuser);
				System.out.println("");
				noOfEntries++;
			}
		} catch (IOException e) {
			throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INVALID_FILE_PATH,
					"Invalid File Location given!!");

		}
		return noOfEntries;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Indian State census analyser program!");
		System.out.println("==================================================");
		IndianStateAnalyser data = new IndianStateAnalyser();
		try {
			data.readData(CSV_FILE);

		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
}
