package com.lbq.run;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.lbq.logback.Foo;
import com.lbq.logback.LogBackTest;
import com.lbq.logback.CustomLoggerConfig;

public class Main2 {

	private final static Logger logger = Logger.getLogger(Main2.class.getName());
	//static LogManager logManager = CustomLoggerConfig.getLogManager();
	
	
	public static void main(String[] args) {
		
		
		try {
			//logger.setLevel(Level.INFO);
			//logManager.addLogger(logger);
			logger.info("main start");
			CustomLoggerConfig.getLogManager();
			LogBackTest logbackTest = new LogBackTest();
			logbackTest.outputlog();
			
			Foo foo = new Foo();
	        foo.doIt();
	        logger.info("main end");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
