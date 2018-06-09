package com.myinterview.exceptions;

public class MyInterviewException extends Exception {

	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public MyInterviewException() {
		super();
	}
	
	public MyInterviewException(String message) {
		super(message);		
	}
}
