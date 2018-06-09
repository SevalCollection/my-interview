package com.myinterview.main;

import org.apache.log4j.Logger;

import com.myinterview.exceptions.MyInterviewException;
import com.myinterview.model.Transactions;
import com.myinterview.utils.MyFileUtil;
import com.myinterview.utils.TransactionProcessingUtil;
import com.myinterview.utils.XMLUtil;

/**
 * XML Reader and Denomination formatter logic as per Screening Requirement !
 *
 */
public class MyInterview {
	static Logger logger = Logger.getLogger(MyInterview.class);

	// path to the XML to be read - available in the resources folder
	static String xmlFileName = "/transactions.xml";
	// static path to the output file
	static String writeToTxtFileInPath = "TransactionOutput.txt";

	public static void main(String[] args) {
		if (logger.isDebugEnabled()) {
			logger.debug("Begin Processing XML to Breakdown Transactions with file=" + xmlFileName);
		}
		try {
			TransactionProcessingUtil.performBreakDown(
					XMLUtil.unMarshal(MyFileUtil.retrieveInputAsFile(xmlFileName), Transactions.class),
					writeToTxtFileInPath);
		} catch (MyInterviewException myInterviewException) {
			logger.error("Error:" + myInterviewException.getMessage());
		}
	}
}
