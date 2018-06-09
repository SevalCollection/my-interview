/**
 * 
 */
package com.myinterview.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.myinterview.exceptions.MyExceptionUtil;
import com.myinterview.exceptions.MyInterviewException;

/**
 * @author Rajesh Bhaskar
 *
 */
public class XMLUtil<T> {

	static Logger logger = Logger.getLogger(XMLUtil.class);;

	public static <T> T unMarshal(File file, Class<T> domainClass) throws MyInterviewException {
		if (null != file) {
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(domainClass);
				Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
				return domainClass.cast(unMarshaller.unmarshal(file));
			} catch (JAXBException jaxbExec) {
				throw MyExceptionUtil.createMyInterviewException("Error while Unmarshalling!-" + jaxbExec.getMessage());
			}
		} else {
			throw MyExceptionUtil.createMyInterviewException("No Read file produced!");
		}
	}
}
