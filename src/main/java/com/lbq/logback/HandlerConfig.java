package com.lbq.logback;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class HandlerConfig {
	
	private String name;
	private String pattern;
	private String count;
	private String limit;
	private String append;
	private String formater;
	private String level;
	private String filter;
	private String encoding;
	
	public HandlerConfig(String name) {
		this.name = name;
	}
	
	public static List<HandlerConfig> getHandlersConfig(Properties properties) {
		List<HandlerConfig> handlersConfig = new ArrayList<>(); 
		Set<Object> keySet = properties.keySet();
		for(Object obj : keySet) {
			String key = obj.toString();
			if(key.startsWith("java.util.logging.FileHandler")) {
				String value = properties.getProperty(key);
				HandlerConfig handler = new HandlerConfig(value);
				handler.pattern = properties.getProperty(value+".pattern","");
				handler.count = properties.getProperty(value+".count","");
				handler.limit = properties.getProperty(value+".limit","");
				handler.append = properties.getProperty(value+".append","");
				handler.formater = properties.getProperty(value+".formater","");
				handler.level = properties.getProperty(value+".level","");
				handler.filter = properties.getProperty(value+".filter","");
				handler.encoding = properties.getProperty(value+".encoding","");
				handlersConfig.add(handler);
			}
		}
		return handlersConfig;
	}
	
}
