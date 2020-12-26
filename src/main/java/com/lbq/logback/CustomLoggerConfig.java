package com.lbq.logback;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class CustomLoggerConfig {
	private static LogManager logManager = LogManager.getLogManager();
	static {
		System.out.println("LogManagerInit begin....");
		
		InputStream resourceAsStream = CustomLoggerConfig.class.getClassLoader().getResourceAsStream("logging.properties");
		try {
			logManager.readConfiguration(resourceAsStream);
			System.out.println("LogManagerInit end....");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static LogManager getLogManager() {
		return logManager;
	}
}
