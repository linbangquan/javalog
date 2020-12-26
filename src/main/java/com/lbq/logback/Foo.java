package com.lbq.logback;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Foo {

	//public static final Logger logger = Logger.getLogger("Foo");
	private Logger logger = CustomLoggerUtil.setLoggerHandler(Logger.getLogger(Foo.class.getName()));
	//private Logger logger = CustomLoggerUtil.setLoggerHandler(Foo.class.getName());
    public void doIt() {
    	//全部
		logger.log(Level.ALL, "test all");
		//配置
		logger.log(Level.CONFIG, "test config");
		//详细
		logger.log(Level.FINE, "test fine");
		//较详细
		logger.log(Level.FINER, "test finer");
		//最详细
		logger.log(Level.FINEST, "test finest");
		//信息
		logger.log(Level.INFO, "test info");
		//禁用
		logger.log(Level.OFF, "test off");
		//严重
		logger.log(Level.SEVERE, "test sevre");
		//警告
		logger.log(Level.WARNING, "test warning");
		
		logger.severe("");
		logger.warning("");
    }
    
}
