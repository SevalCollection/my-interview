/**
 * Copy Rights to go here
 */
package com.myinterview.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.myinterview.exceptions.MyExceptionUtil;
import com.myinterview.exceptions.MyInterviewException;
import com.myinterview.model.Transaction;
import com.myinterview.model.Transactions;

/**
 * Class to perform all the processing of breaking down the Transactions amount
 * to the required format
 * 
 * @author Rajesh Bhaskar
 *
 */
public class TransactionProcessingUtil {

	static Logger logger = Logger.getLogger(TransactionProcessingUtil.class);


	//Creating the Current Denominators to pass for calculation 
	static int[][] DENOM_VALUES = {{20,10,5,2,1},{25,10,5,2,1}};	
	
	//The different Symbols for a currency (the whole (Dollars) and the reminder part(Cents)
	static char[] 	DENOM_SYMBOLS = {'$','C'};
	
	/**
	 * Process the Transactions and perform the transformation xml and write to
	 * OutputFile
	 * 
	 * @param transactions
	 *            Object read from XML
	 * @param writeToTxtFileInPath
	 *            the text file to write output
	 * @throws IOException
	 */
	public static void performBreakDown(Transactions transactions, String writeToTxtFileInPath)
			throws MyInterviewException {
		if (StringUtils.isNotEmpty(writeToTxtFileInPath)
				|| (null != transactions && CollectionUtils.isNotEmpty(transactions.getTransactions()))) {
			if (logger.isDebugEnabled()) {
				logger.debug(" breakdownTransaction Object : " + transactions.toString());
			}
			List<String> lines = transactions.getTransactions().stream()
					.map(transaction -> transformTransaction(transaction)).collect(Collectors.toList());
			MyFileUtil.writeToFile(writeToTxtFileInPath, lines);
		} else {
			// create an exception as either WriteFilePath is null or retrieved transactions object was null
			throw MyExceptionUtil
					.createMyInterviewException("Unable to perform Break Down proass with the parameter writeFilePath="
							+ writeToTxtFileInPath + " and Xtracted Transactions=" + transactions);
		}
	}

	/**
	 * perform the transformation to specified format: ItemName +
	 * transformedCurrencyText Created as an new method to show case the construct
	 * as this might change and modifying this method easy for maintenance
	 * 
	 * @param transaction
	 *            object
	 * @return String formatted text
	 */

	private static String transformTransaction(Transaction transaction) {
		return transaction.getItem() + " " + denominateTransaction(transaction.getAmount());
	}

	/**
	 * perform the breakdown routine
	 * 
	 * @param amount
	 *            to breakdown
	 * @return the structured breakdown as String
	 */

	private static String denominateTransaction(float amount) {
		StringBuilder breakDown = new StringBuilder(StringUtils.EMPTY);
		
		// extract the dollar amount
		int totalDollars = (int) (amount / 1);

		// To retrieve the pennies, use BigDecimal and RoundOf Mode 
		BigDecimal bdOrigAmount = new BigDecimal((amount - Math.floor(amount)) * 100);
		bdOrigAmount = bdOrigAmount.setScale(4, RoundingMode.HALF_DOWN);
		
		// extract the pennies amount
		int totalPennies = bdOrigAmount.intValue();
		
		//Creating the TotalAmount of Dollars and Pennies to send for breaking down and formating
		int[] dollarAndCentTotalAmount = {totalDollars,totalPennies};
		
		
		for (int i = 0; i < DENOM_VALUES[0].length; i++) {
			for (int k = 0; k < DENOM_SYMBOLS.length; k++) {
				for (int j = 0; j < DENOM_VALUES[1].length; j++) {

					// dollarAndCentTotalAmount array length will be same as the symbols array.
					// Using same iteration
					breakDown.append(denominateItem(dollarAndCentTotalAmount[k], DENOM_VALUES[i][j], DENOM_SYMBOLS[k]));
				}
			}
		}
				
		return null;
	}
	

	/**
	 * Performing the formatting process for individual Item
	 * 
	 * @param amount the amount to process the breakdown
	 * @param denomValue the actual currency denomination to calculate
	 * @param demonSymbol the symbol that is to be recorded
	 * @return the formatted string for the individual denominator
	 */
	private static String denominateItem(int amount, int denomValue, char demonSymbol) {
		StringBuilder formattedStr = new StringBuilder(StringUtils.EMPTY);
		
		if (logger.isDebugEnabled()) {
			logger.debug(" amount: " + amount + " denom Value="+ denomValue +" denomSymbol="+ demonSymbol);
		}
		
		int nos = amount / denomValue;
		// reducing the amount by that denomination as it has already been picked
		amount = amount % denomValue;
		// add to output if value not zero
		for (int i = 0; nos > 0 && i < nos; i++) {
			formattedStr.append(demonSymbol+denomValue);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" appending="+ formattedStr.toString());
		}
		return formattedStr.toString();
	}

}
