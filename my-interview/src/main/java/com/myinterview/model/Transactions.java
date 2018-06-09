/**
 * Copy Rights to go here
 */
package com.myinterview.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections4.CollectionUtils;

/**
 * XML Root Element to store the XML Data
 * 
 * @author Rajesh Bhaskar
 *
 */
@XmlRootElement (name="transactions")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transactions {
	
	List<Transaction> transactionLst;

	/**
	 * @return the transactions
	 */
	public List<Transaction> getTransactions() {
		return transactionLst;
	}

	/**
	 * @param transactions the transactions to set
	 */
	@XmlElement(name="transaction")
	public void setTransactions(List<Transaction> transactionLst) {
		this.transactionLst = transactionLst;
	}


	
	@Override
	public String toString() {
		List<String> lines = new ArrayList<String>();
		if(CollectionUtils.isNotEmpty(transactionLst)) {
			lines = transactionLst
					.stream()
					.map(transaction -> transaction.getItem() + " " + transaction.getAmount())
					.collect(Collectors.toList());	
		}
		return lines.toString();
	}
	
}
