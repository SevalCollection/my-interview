/**
 *  No Copy Right attached -Copy Rights to go here
 */
package com.myinterview.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This the Model of the data being processed
 * 
 * @author Rajesh Bhaskar
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {

	public String item;
	public float amount;
	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}
	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	
}
