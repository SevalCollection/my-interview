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
		return transaction.getItem() + " " + denominate(transaction.getAmount());
	}

	/**
	 * perform the breakdown routine
	 * 
	 * @param amount
	 *            to breakdown
	 * @return the structured breakdown as String
	 */

	private static String performDenominate(float amount) {
		return null;
	}
	
	private static String denominitazion(float amount) {
		return null;
	}
	
	/**
	 * perform the breakdown routine
	 * 
	 * @param amount
	 *            to breakdown
	 * @return the structured breakdown as String
	 */

	private static String denominate(float amount) {

		if (logger.isDebugEnabled()) {
			logger.debug(" breaking down Amount : " + amount);
		}

		StringBuilder breakDown = new StringBuilder(StringUtils.EMPTY);
		// extract the dollar amount
		int totalDollars = (int) (amount / 1);

		BigDecimal bdOrigAmount = new BigDecimal((amount - Math.floor(amount)) * 100);
		bdOrigAmount = bdOrigAmount.setScale(4, RoundingMode.HALF_DOWN);
		// extract the pennies amount
		int totalPennies = bdOrigAmount.intValue();

		// break down the DOLLAR part

		// the max denomination Dollar Amount is $20
		int noOf20s = totalDollars / 20;
		// reducing the dollar amount by that denomination as it has already been picked
		totalDollars = totalDollars % 20;
		// add to output if value not zero
		if (noOf20s > 0) {
			for (int i = 0; i < noOf20s; i++) {
				breakDown.append("$20");
			}
		}

		// the next denomination Dollar Amount is $10
		int noOf10s = totalDollars / 10;
		// reducing the dollar amount by that denomination as it has already been picked
		totalDollars = totalDollars % 10;
		// add to output if value not zero
		if (noOf10s > 0) {
			for (int i = 0; i < noOf10s; i++) {
				breakDown.append("$10");
			}
		}

		// the max denomination Dollar Amount is $5
		int noOf5s = totalDollars / 5;
		// reducing the dollar amount by that denomination as it has already been picked
		totalDollars = totalDollars % 5;
		// add to output if value not zero
		if (noOf5s > 0) {
			for (int i = 0; i < noOf5s; i++) {
				breakDown.append("$5");
			}
		}

		// the max denomination Dollar Amount is $2
		int noOf2s = totalDollars / 2;
		// reducing the dollar amount by that denomination as it has already been picked
		totalDollars = totalDollars % 2;
		// add to output if value not zero
		if (noOf2s > 0) {
			for (int i = 0; i < noOf2s; i++) {
				breakDown.append("$2");
			}
		}

		// the max denomination Dollar Amount is $1
		int noOf1s = totalDollars;
		// add to output if value not zero
		if (noOf1s > 0) {
			for (int i = 0; i < noOf1s; i++) {
				breakDown.append("$1");
			}
		}

		// break down the PENNY part
		// the max denomination Dollar Amount is C25
		int noOf25cs = totalPennies / 25;
		// reducing the dollar amount by that denomination as it has already been picked
		totalPennies = totalPennies % 25;
		// add to output if value not zero
		if (noOf25cs > 0) {
			for (int i = 0; i < noOf25cs; i++) {
				breakDown.append("C25");
			}
		}

		// the max denomination Dollar Amount is C10
		int noOf10cs = totalPennies / 10;
		// reducing the dollar amount by that denomination as it has already been picked
		totalPennies = totalPennies % 10;
		// add to output if value not zero
		if (noOf10cs > 0) {
			for (int i = 0; i < noOf10cs; i++) {
				breakDown.append("C10");
			}
		}

		// the max denomination Dollar Amount is C5
		int noOf5cs = totalPennies / 5;
		// reducing the dollar amount by that denomination as it has already been picked
		totalPennies = totalPennies % 5;
		// add to output if value not zero
		if (noOf5cs > 0) {
			for (int i = 0; i < noOf5cs; i++) {
				breakDown.append("C5");
			}
		}

		// the max denomination Dollar Amount is C2
		int noOf2cs = totalPennies / 2;
		// reducing the dollar amount by that denomination as it has already been picked
		totalPennies = totalPennies % 2;
		// add to output if value not zero
		if (noOf2cs > 0) {
			for (int i = 0; i < noOf2cs; i++) {
				breakDown.append("C2");
			}
		}

		// the max denomination Dollar Amount is $1
		int noOf1cs = totalPennies;
		// add to output if value not zero
		if (noOf1cs > 0) {
			for (int i = 0; i < noOf1cs; i++) {
				breakDown.append("C1");
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" broke Amount into : " + breakDown.toString());
		}

		return breakDown.toString();
	}

}
