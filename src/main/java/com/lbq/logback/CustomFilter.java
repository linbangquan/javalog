package com.lbq.logback;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class CustomFilter implements Filter {

	@Override
	public boolean isLoggable(LogRecord record) {
		System.out.println("1:"+record.getResourceBundleName());
		System.out.println("1:"+record.getParameters());
		return true;
	}

}
