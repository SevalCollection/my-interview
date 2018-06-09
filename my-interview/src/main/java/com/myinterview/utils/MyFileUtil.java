/**
 * Copy Rights to go here
 */
package com.myinterview.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.myinterview.exceptions.MyExceptionUtil;
import com.myinterview.exceptions.MyInterviewException;

/**
 * Contains File Read utility methods
 * 
 * @author Rajesh Bhaskar
 *
 */
public class MyFileUtil {

	static Logger logger = Logger.getLogger(MyFileUtil.class);

	public static File retrieveInputAsFile(String path) {
		if (logger.isDebugEnabled()) {
			logger.debug("File Path = " + path);
		}
		if (StringUtils.isNotEmpty(path)) {
			return new File(MyFileUtil.class.getResource(path).getFile());
		}
		return null;
	}

	public static void writeToFile(String writeToTxtFileInPath, List<String> lines) throws MyInterviewException {
		if (logger.isDebugEnabled()) {
			logger.debug("Write File Path = " + writeToTxtFileInPath);
		}
		if (StringUtils.isNotEmpty(writeToTxtFileInPath)) {
			Path fileInPath = Paths.get(writeToTxtFileInPath);

			// Write the contents to the file
			try {
				Files.write(fileInPath, lines, Charset.forName("UTF-8"));
				if (logger.isDebugEnabled()) {
					logger.debug("Write to  file was successful!");
				}
			} catch (IOException ioExec) {
				throw MyExceptionUtil.createMyInterviewException("Write to File was Unsuccessful!" + ioExec);
			}
		} else {
			throw MyExceptionUtil.createMyInterviewException("WriteToFile Path is Empty!");
		}
	}
}
