package org.client.UI.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverManager {
	private static ThreadLocal<EventFiringWebDriver> driver = new ThreadLocal<EventFiringWebDriver>();

	// private static ThreadLocal<Log> log = new ThreadLocal<Log>();
	

	private static DriverManager instance = null;

	private DriverManager() {

	}

	public static DriverManager getInstance() {
		if (instance == null) {
			instance = new DriverManager();
		}
		return instance;
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(EventFiringWebDriver webdriver) {
		driver.set(webdriver);
	}

	// public static ThreadLocal<Log> getLog() { return log; }

	// public static void setLog(Log logObject) { log.set(logObject); }

}
