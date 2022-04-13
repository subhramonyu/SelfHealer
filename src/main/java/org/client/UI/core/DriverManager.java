package org.client.ui.core;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<String> driverName = new ThreadLocal<>();
	private static ThreadLocal<String> userName = new ThreadLocal<>();
	private static ThreadLocal<String> password = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(WebDriver webdriver) {
		driver.set(webdriver);
	}

	public static String getDriverName() {
		return driverName.get();
	}

	public static void setDriverName(String name) {
		driverName.set(name);
	}

	public static String getUserName() {
		return userName.get();
	}

	public static void setUserName(String userNameValue) {
		userName.set(userNameValue);
	}

	public static String getPassword() {
		return password.get();
	}

	public static void setPassword(String passwordValue) {
		password.set(passwordValue);
	}

	public static void clearContext() {
		driver.set(null);
		driverName.set(null);
		userName.set(null);
		password.set(null);
	}

}
