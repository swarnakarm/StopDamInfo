package com.daminfo.common.util;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class AppLogger {

	private static Logger logger = null;

	public static void initLoggerProperties(String propFolder) {
		String log4jFile = new File(propFolder + "log4j.properties").getAbsolutePath();
		System.out.println("log4jFile: " + log4jFile);
		PropertyConfigurator.configureAndWatch(log4jFile);
		logger = Logger.getLogger(AppLogger.class);
	}

	public static Logger getLogger() {
		return logger;
	}
	
}
