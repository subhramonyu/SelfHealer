package org.client.Factory.config;

import java.awt.Robot;
import java.util.Arrays;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;

import io.qameta.allure.Step;

@SuppressWarnings({ "unused" })
public class FactoryUtils {
	private static Process process;


	@Step("Executing Command : {0} ")
	public static void executeCommand(String command) {
		try {
			process = Runtime.getRuntime().exec(command);
		} catch (Exception e) {
		}
	}

	@Step("Waiting for : {0} seconds")
	public static void wait(int seconds) {
		try {
			Thread.sleep(1000 * seconds);
		} catch (Exception e) {
		}
	}

	@Step("Spliting the string : {0} with separator : {1}")
	public static String[] split(String mainString, String... sepeartor) {
		String getString = Arrays.toString(sepeartor);
		return mainString.split(getString);
	}

	

	@Step("Getting the User home directory path")
	public static String getUserHomeDirectoryPath() {
		return System.getProperty("user.home");
	}

	@Step("Getting the  User Current directory path")
	public static String getUserCurrentDirectoryPath() {
		return System.getProperty("user.dir");
	}

	
	

}
