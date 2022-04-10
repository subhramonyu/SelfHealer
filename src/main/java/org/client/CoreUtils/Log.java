/*package org.client.CoreUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
	
	static {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));

	}

		// Initialize Log4j logs
	private static Logger Log;
			public static Logger setLogger(String ClassName) {
				PropertyConfigurator.configure(CommonUtils.getUserCurrentDirectoryPath()+"/src/main/resources/PropertiesFiles/Log.properties");
				return  Log = Logger.getLogger(ClassName.getClass().getName());
				
			}
		
		 public static void info(String message) {

				Log.info(message);

				}

		 public static void warn(String message) {

		    Log.warn(message);

			}

		 public static void error(String message) {

		    Log.error(message);

			}

		 public static void fatal(String message) {

		    Log.fatal(message);

			}

		 public static void debug(String message) {

		    Log.debug(message);

			}

		}*/