package com.capgemini.censustest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.census.CensusAnalyserException;
import com.capgemini.census.IndianStateAnalyser;

import junit.framework.Assert;

public class CensusTest {
	private static final String CSV_FILE = "H:\\CapgTutorial\\Census\\IndianStateCensusData .csv";
	private static String CSV_INVALID_FILE = "H:\\CapgTutorial\\Census\\IndianStateCensusDataInvalid.csv";
	private static String CSV_INVALID_DELIMITER = "H:\\CapgTutorial\\Census\\InvalidDelimiter.csv";
	private static String CSV_INVALID_HEADER = "H:\\CapgTutorial\\Census\\InvalidHeader.csv";

	// this test case checks for total no. of entries in the file
	@Test
	public void givenNumberOfEntriesInACSVFile_ShouldReturnExactlytheSameWhileReading() throws CensusAnalyserException {
		IndianStateAnalyser data = new IndianStateAnalyser();
		int entry = 0;
		try {
			entry = data.readData(CSV_FILE);

		} catch (CensusAnalyserException e) {
			System.out.println("Invalid");
		}
		Assert.assertEquals(29, entry);
	}

	// this test case checks if custom exception thrown in case of invalid file
	// location

	@Test
	public void givenWrongFileLocationsthrowsCustomeException_ForInvalidFilePath() {
		IndianStateAnalyser obj = new IndianStateAnalyser();
		try {
			obj.readData(CSV_INVALID_FILE);
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}

	// this test case checks for invalid delimiter in the csv file while reading
	@Test
	public void givenInvalidDelimiter_ShouldThrowCustomException() {
		IndianStateAnalyser obj = new IndianStateAnalyser();
		try {
			obj.readData(CSV_INVALID_DELIMITER);
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
		}
	}
	// this test case checks for invalid header in csv file while reading

	@Test
	public void givenInvalidHeader_ShouldThrowCustomException() {
		IndianStateAnalyser obj = new IndianStateAnalyser();
		try {
			obj.readData(CSV_INVALID_HEADER);
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(CensusAnalyserException.ExceptionType.INVALID_HEADER, e.type);
		}
	}
}
