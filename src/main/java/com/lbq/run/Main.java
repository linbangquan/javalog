package com.lbq.run;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.lbq.logback.Foo;
import com.lbq.logback.LogBackTest;

public class Main {

	private final static Logger logger = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {
		try {
			logger.setLevel(Level.INFO);
			FileHandler fileHandler = new FileHandler("D:/javalog/info.log",true);
			fileHandler.setLevel(Level.INFO);
			
			fileHandler.setFormatter(new Formatter() {

				@Override
				public String format(LogRecord record) {
					
					
					Level level = record.getLevel();
					String loggerName = record.getLoggerName();
					String message = record.getMessage();
					long millis = record.getMillis();
					Object[] objArr = record.getParameters();
					//ResourceBundle resourceBundle = record.getResourceBundle();
					//String resourceBundleName =record.getResourceBundleName();
					long sequenceNumber = record.getSequenceNumber();
					String sourceClassName = record.getSourceClassName();
					String sourceMethodName = record.getSourceMethodName();
					int threadId = record.getThreadID();
					//Throwable throwable = record.getThrown();
					String result = String.format("%-5s|%d|%d|%s.%s|%d - %s\n", level,millis,threadId,sourceClassName,sourceMethodName,sequenceNumber,message);
					return result;
//					return "\n level=" + level + ",\n loggerName=" + loggerName + ",\n message=" + message + ",\n millis=" + millis
//							+ ",\n resourceBundleName=" + resourceBundleName + ",\n sequenceNumber=" + sequenceNumber
//							+ ",\n sourceClassName=" + sourceClassName + ",\n sourceMethodName=" + sourceMethodName
//							+ ",\n threadId=" + threadId + ",\n throwable=" + throwable;
				}
				
			});
			logger.addHandler(fileHandler);
			logger.info("main start");
			
			LogBackTest logbackTest = new LogBackTest();
			logbackTest.outputlog();
			
			Foo foo = new Foo();
	        foo.doIt();
	        logger.info("main end");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
