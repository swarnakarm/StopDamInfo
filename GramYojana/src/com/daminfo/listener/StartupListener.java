package com.daminfo.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.daminfo.common.util.AppLogger;
import com.daminfo.common.util.EnvironmentalConstants;

public class StartupListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		final ServletContext context = sce.getServletContext();
		EnvironmentalConstants.setContextPath(context.getRealPath("/"));
		System.out.println("Initializing Logger");
		AppLogger.initLoggerProperties(EnvironmentalConstants.getContextPath() + "WEB-INF/");
		System.out.println("Logger Initialized");
	}

}
