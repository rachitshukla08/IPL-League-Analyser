package com.capgemini.iplleagueanalyser;

public class IPLAnaylserException extends Exception {
	public enum ExceptionType{
		INVALID_FILE_PATH,INVALID_CLASS_TYPE, INVALID_DELIMITER, INVALID_HEADER,NO_DATA, WRONG_PLAYER_TYPE;
	}
	public ExceptionType type;
	
	public IPLAnaylserException(String message,ExceptionType type){
		super(message);
		this.type = type;
	}
}
