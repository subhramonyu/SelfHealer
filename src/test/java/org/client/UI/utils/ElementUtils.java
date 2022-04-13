package org.client.UI.utils;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.client.UI.core.DriverManager;
import org.client.Factory.config.FactoryUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.qameta.allure.Step;

public class ElementUtils {

	

	public WebElement scrollIntoView(WebElement elementToScrollTo) {
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(false);",
				elementToScrollTo);
		WaitUtils.wait(1);
		return elementToScrollTo;
	}
	
	@Step("Scrolling to the Element : {0}")
	public boolean scrollToElement(WebElement element) {
		try {
			((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
			if (element.isDisplayed())
				return true;
			else
				return false;
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}


	

	

	@Step("Scrolling by Steps : {0}")
	public void scroll(int scrollSteps, int scrollCount) {
		for (int i = 0; i < scrollCount; i++) {
			((JavascriptExecutor) DriverManager.getDriver()).executeScript("window.scrollBy(0," + scrollSteps + ")",
					"");
		}
	}

	
	public static void scrollPageDown() {
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("window.scrollBy(0,1000)", "");
	}

	public static void scrollPageUp() {
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("window.scrollBy(0,-800)", "");
	}

	@Step("Pop up scrolling to element : {0}")
	public void webPopupScroll(WebElement parentElement, int scrollSteps, int scrollCount) {
		if (scrollSteps <= 0)
			((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollTop = arguments[1];",
					parentElement, Math.abs(scrollSteps));
		else if (scrollSteps > 0)
			((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollBottom = arguments[1];",
					parentElement, Math.abs(scrollSteps));
		FactoryUtils.wait(2);
	}

	public static boolean isElementSelected(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		return (Boolean) js.executeScript("var data = arguments[0].selected; return data;", element);
	}

	public static boolean isElementDisabled(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		return (Boolean) js.executeScript("var data = arguments[0].disabled; return data;", element);
	}

	
	@Step("Selecting from the drop down by index :{1}")
	public void selectFromDropdown(WebElement element, int indexToBeSelected) {
		Select aSelect = new Select(element);
		aSelect.selectByIndex(indexToBeSelected);
	}

	public static void clickEnterKey() {
		try {
			Robot robot = new Robot();
			WaitUtils.wait(1);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			WaitUtils.wait(1);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void clickTabKey() {
		try {
			Robot robot = new Robot();
			WaitUtils.wait(1);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			WaitUtils.wait(1);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void clearInput(WebElement element) {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.click(element).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT)
				.sendKeys(Keys.DELETE).build().perform();
	}

}
