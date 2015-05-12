package com.daminfo.common.util;

import java.io.File;

public final class EnvironmentalConstants {

	private static String contextPath = null;
	private static String projectFolderPath = null;

	public static String getContextPath() {
		return contextPath;
	}

	public static void setContextPath(String contextpath) {
		contextPath = contextpath;
		projectFolderPath = contextPath + UIConstants.PROJECTS_FOLDER + File.separator;
	}

	public static String getProjectFolderPath() {
		return projectFolderPath;
	}
}
