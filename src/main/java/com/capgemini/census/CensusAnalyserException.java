package com.capgemini.census;

public class CensusAnalyserException extends Exception {
	public enum ExceptionType {
		INVALID_FILE_PATH, INVALID_DELIMITER, INVALID_HEADER;
	}

	public ExceptionType type;

	public CensusAnalyserException(ExceptionType type, String message) {
		super(message);
		this.type = type;
	}
}
