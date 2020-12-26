package com.lbq.run;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lbq.logback.Foo;
import com.lbq.logback.LogBackTest;

public class Main0629 {
	
	private static Logger logger = Logger.getLogger(Main0629.class.getName());
	
	public static void main(String[] args) {
		logger.log(Level.INFO, "main start ...");
		Properties properties = new Properties();
		//InputStream resourceAsStream = Main0629.class.getClassLoader().getResourceAsStream("logging.properties");
		InputStream resourceAsStream = Main0629.class.getClassLoader().getResourceAsStream("log.properties");
		try {
			properties.load(resourceAsStream);
			Set<Entry<Object, Object>> entrySet = properties.entrySet();
			for(Entry<Object, Object> entry : entrySet) {
				System.out.println(entry.getKey()+"="+entry.getValue());
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		LogBackTest logbackTest = new LogBackTest();
		logbackTest.outputlog();
		//System.out.println(Level.parse("INFO"));
		//System.out.println(Level.parse("warning"));
		Foo foo = new Foo();
        foo.doIt();
		
		logger.log(Level.INFO, "main end ...");

	}

}
