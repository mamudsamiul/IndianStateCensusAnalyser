package com.capgemini.indianstatecode;

public class StateCodeException extends Exception {
	public enum ExceptionType {
		INVALID_FILE_PATH, INVALID_DELIMITER, INVALID_HEADER;
	}

	public ExceptionType type;

	public StateCodeException(ExceptionType type, String message) {
		super(message);
		this.type = type;
	}
}
