package org.apm.carteiraprofissional.utils;

import java.net.URL;

public class PathUtils {

	public static String getWebInfPath(){
		String filePath = "";
		String WEBINF="WEB-INF";
	    URL url = PathUtils.class.getResource("PathUtils.class");

	    String className = url.getFile();

	    filePath = className.substring(0,className.indexOf(WEBINF) + WEBINF.length());

	    return filePath;
	}
}
