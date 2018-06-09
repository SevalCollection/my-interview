package com.myinterview.exceptions;

/**
 * 
 * @author Rajesh Bhaskar
 *
 */
public class MyExceptionUtil {

	/**
	 * Creating New MyInterviewException
	 * 
	 * @param string
	 *            message to create the exception object
	 * 
	 * @return MyInterviewException
	 */
	public  static MyInterviewException createMyInterviewException(String message) {
		return new MyInterviewException(message);
	}
}
