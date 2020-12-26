package com.lbq.logback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CustomLoggerUtil {
    
    private static FileHandler fileHandler;
    
    private static List<FileHandler> fileHandlers = new ArrayList<>();
    
    private static Formatter defaultFormatter = new SimpleFormatter();
    
    static {
    	InputStream resourceAsStream = CustomLoggerUtil.class.getClassLoader().getResourceAsStream("log.properties");
		try {
			
			Properties properties = new Properties();
			properties.load(resourceAsStream);
			List<HandlerConfig> handlerConfigs = getHandlerConfigs(properties);
        	for(HandlerConfig handlerConfig: handlerConfigs) {
        		String pattern = handlerConfig.pattern;
        		if("".equals(pattern)) {
        			continue;
        		}
        		int index = pattern.lastIndexOf(File.separator);
        		if(index == -1) {
        			pattern = System.getProperty("user.home") +File.separator + pattern;
        		}else if(index > 0) {
        			String parentPath = pattern.substring(0, index);
                	File file = new File(parentPath);
                	if(!file.exists()) {
                		file.mkdirs();
                	}
        		}
            	int limit = 0;// zero => no limit.
            	if(!"".equals(handlerConfig.limit)) {
            		limit = Integer.parseInt(handlerConfig.limit);
            	}
            	int count = 0;
            	if(!"".equals(handlerConfig.count)) {
            		count = Integer.parseInt(handlerConfig.count);
            	}
            	boolean append = Boolean.valueOf(handlerConfig.append);
                //文件日志内容标记为可追加  
                fileHandler = new FileHandler(pattern,limit,count,append);  
                Formatter formatter = defaultFormatter;
                if(!"".equals(handlerConfig.formater)) {
                	Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(handlerConfig.formater);
        			formatter = (Formatter) clazz.newInstance();
                }
                
                //以文本的形式输出  
                fileHandler.setFormatter(formatter);  
                Level handlerLevel = Level.ALL;
                if(!"".equals(handlerConfig.level)) {
                	handlerLevel = Level.parse(handlerConfig.level.toUpperCase());
                }
                fileHandler.setLevel(handlerLevel); 
                if(!"".equals(handlerConfig.filter)) {
                	Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(handlerConfig.filter);
                	Filter filer = (Filter) clazz.newInstance();
                	fileHandler.setFilter(filer);
                }
                
                if(!"".equals(handlerConfig.encoding)) {
                	fileHandler.setEncoding(handlerConfig.encoding);
                }
                fileHandlers.add(fileHandler);
        	}

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch(IllegalAccessException e3) {
			e3.printStackTrace();
		} catch(InstantiationException e4) {
			e4.printStackTrace();
		}finally {
			if(resourceAsStream != null) {
				try {
					resourceAsStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					resourceAsStream = null;
				}
			}
		}
    }
  
   
    public synchronized static Logger setLoggerHandler(Logger logger) {  
        return setLoggerHanlder(logger, Level.ALL);  
    }  
  
    public synchronized static Logger setLoggerHanlder(Logger logger, Level level) {  
           
        try {
//        	ConsoleHandler consoleHandler = new ConsoleHandler();
//        	consoleHandler.setLevel(Level.INFO);
//        	consoleHandler.close();consoleHandler.setFormatter(new CustomFormatter());
//        	logger.addHandler(consoleHandler);
            for(FileHandler fileHandler : fileHandlers) {
            	logger.addHandler(fileHandler);
            }            
            logger.setLevel(level);  
            Handler[] handlers = logger.getHandlers();
            for(Handler handler:handlers) {
            	System.out.println(logger.getName()+":"+handler);
            }
            logger.info("init......");
        } catch (SecurityException e) {  
            logger.severe(populateExceptionStackTrace(e));  
        } 
//        System.out.println(logger.getParent());
//        Logger rootLogger = logger.getParent();
//        System.out.println(rootLogger.getParent());
//        Handler[] handlers = rootLogger.getHandlers();
//        for(Handler handler:handlers) {
//        	if(handler instanceof ConsoleHandler) {
//        		rootLogger.removeHandler(handler);
//        	}
//        	System.out.println(logger.getName()+":"+handler);
//        }
//        logger.setParent(null);
        //logger.setFilter(new CustomFilter());
        logger.setUseParentHandlers(false);
        return logger;  
    }  
  
    private synchronized static String populateExceptionStackTrace(Exception e) {  
        StringBuilder sb = new StringBuilder();  
        sb.append(e.toString()).append("\n");  
        for (StackTraceElement elem : e.getStackTrace()) {  
            sb.append("\tat ").append(elem).append("\n");  
        }  
        return sb.toString();  
    }  
    private static List<HandlerConfig> getHandlerConfigs(Properties properties) {
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
    
    static class HandlerConfig {
    	
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
    	public String getName() {
    		return name;
    	}
    }
}
