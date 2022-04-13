package org.client.ui.utils;


import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.client.ui.core.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	 private static final int TIMEOUT_IN_SEC_TO_DISAPPEAR = 10;
	 
	 public static void wait(int seconds) {
	        try {
	            Thread.sleep(1000 * seconds);
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to wait for " + seconds + " seconds", e);
	        }
	    }

	    public static WebElement waitUntilVisible(final WebElement element, int waitTimeInSeconds) {
	        return new WebDriverWait(DriverManager.getDriver(), waitTimeInSeconds)
	                .until(ExpectedConditions.visibilityOf(element));
	    }

	    public static WebElement waitUntilPresenceOfElementLocated(final String xPath, int waitTimeInSeconds) {
	        return new WebDriverWait(DriverManager.getDriver(), waitTimeInSeconds)
	                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
	    }

	    public static WebElement waitUntilClickable(final WebElement element, int waitTimeInSeconds) {
	        return new WebDriverWait(DriverManager.getDriver(), waitTimeInSeconds)
	                .until(ExpectedConditions.elementToBeClickable(element));
	    }

	    public static void waitUntilElementEnabled(final WebElement element, int waitTimeInSeconds) {
	        new WebDriverWait(DriverManager.getDriver(), waitTimeInSeconds)
	                .until((ExpectedCondition<Boolean>) driver -> element.getAttribute("disabled") == null);
	    }

	    public static void waitUntilDisappears(final WebElement element, int waitTimeInSeconds) {
	        new WebDriverWait(DriverManager.getDriver(), waitTimeInSeconds)
	                .until(ExpectedConditions.invisibilityOf(element));
	    }

	    public static WebElement waitUntilElementTextIsPresent(final WebElement element) {
	        new WebDriverWait(DriverManager.getDriver(), TIMEOUT_IN_SEC_TO_DISAPPEAR)
	                .pollingEvery(Duration.ofSeconds(1))
	                .until(textToBePresent(element));
	        return element;
	    }

	    private static ExpectedCondition<Boolean> textToBePresent(
	            final WebElement element) {
	        return driver -> {
	            try {
	                return !element.getText().equals("");
	            } catch (WebDriverException e) {
	                return true;
	            }
	        };

	    }

	    public static void waitForText(final WebElement element, String expectedText, int waitTimeInSeconds) {
	        new WebDriverWait(DriverManager.getDriver(), waitTimeInSeconds)
	                .until(ExpectedConditions.textToBePresentInElement(element, expectedText));
	    }

	    public static boolean isElementAbsentNow(By locator) {
	        WebDriver driver = DriverManager.getDriver();
	        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	        boolean result;
	        try {
	            result = new WebDriverWait(driver, 0)
	                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
	        } finally {
	            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        }
	        return result;
	    }

	    public static void waitUntilElementsVisible(List<WebElement> list, long waitTimeInSeconds) {
	        new WebDriverWait(DriverManager.getDriver(), waitTimeInSeconds).ignoring(StaleElementReferenceException.class)
	                .until(ExpectedConditions.visibilityOfAllElements(list));
	    }

}
