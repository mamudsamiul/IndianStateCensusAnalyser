package com.capgemini.statecodetest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.capgemini.indianstatecode.IndianStateCodeAnalyser;
import com.capgemini.indianstatecode.StateCodeException;

import junit.framework.Assert;

public class StateCodeTest {
	private static final String CSV_FILE = "H:\\CapgTutorial\\Census\\IndiaStateCode.csv";
	private static String CSV_INVALID_FILE = "H:\\CapgTutorial\\Census\\IndianStateCensusDataInvalid.csv";
	private static String CSV_INVALID_DELIMITER = "H:\\CapgTutorial\\Census\\IndianStateCodeInvalidDelimiter.csv";
	private static String CSV_INVALID_HEADER = "H:\\CapgTutorial\\Census\\IndianStateCodeInvalidHeader.csv";

	// this test case checks for total no. of entries in the file
	@Test
	public void givenNumberOfEntriesInACSVFile_ShouldReturnExactlytheSameWhileReading() throws StateCodeException {
		IndianStateCodeAnalyser data = new IndianStateCodeAnalyser();
		int entry = 0;
		try {
			entry = data.readData(CSV_FILE);

		} catch (StateCodeException e) {
			System.out.println("Invalid");
		}
		Assert.assertEquals(37, entry);
	}

	// this test case checks if custom exception thrown in case of invalid file
	// location

	@Test
	public void givenWrongFileLocationsthrowsCustomeException_ForInvalidFilePath() {
		IndianStateCodeAnalyser obj = new IndianStateCodeAnalyser();
		try {
			obj.readData(CSV_INVALID_FILE);
		} catch (StateCodeException e) {
			System.out.println(e.getMessage());
			assertEquals(StateCodeException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}

	// this test case checks for invalid delimiter in the csv file while reading
	@Test
	public void givenInvalidDelimiter_ShouldThrowCustomException() {
		IndianStateCodeAnalyser obj = new IndianStateCodeAnalyser();
		try {
			obj.readData(CSV_INVALID_DELIMITER);
		} catch (StateCodeException e) {
			System.out.println(e.getMessage());
			assertEquals(StateCodeException.ExceptionType.INVALID_DELIMITER, e.type);
		}
	}
	// this test case checks for invalid header in csv file while reading

	@Test
	public void givenInvalidHeader_ShouldThrowCustomException() {
		IndianStateCodeAnalyser obj = new IndianStateCodeAnalyser();
		try {
			obj.readData(CSV_INVALID_HEADER);
		} catch (StateCodeException e) {
			System.out.println(e.getMessage());
			assertEquals(StateCodeException.ExceptionType.INVALID_HEADER, e.type);
		}
	}
}
