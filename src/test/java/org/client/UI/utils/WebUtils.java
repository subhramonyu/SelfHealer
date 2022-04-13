package org.client.UI.utils;

import org.client.UI.core.DriverManager;
import org.client.Factory.config.TestData;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

public class WebUtils 
{
	
	public void refreshPage() {
        DriverManager.getDriver().navigate().refresh();
        WaitUtils.wait(TestData.FOUR_SECONDS_DELAY);
    }

    public void maximize() {
        DriverManager.getDriver().manage().window().maximize();
    }
	@Step("Get the screen width")
	public static int getScreenWidth() {
		return DriverManager.getDriver().manage().window().getSize().getWidth();
	}

	@Step("Get the Screen height")
	public static int getScreenHeight() {
		return DriverManager.getDriver().manage().window().getSize().getHeight();
	}

	@Step("Get the Element location : {0}")
	public static Point getElementLocation(WebElement element) {
		return element.getLocation();
	}

	@Step("Get the Element Dimension : {0}")
	public static Dimension getElementDimension(WebElement element) {
		return element.getSize();
	}
	
	@Step("switching to Frame : {0}")
	public static void switchToFrame(WebElement element) {
		DriverManager.getDriver().switchTo().frame(element);
	}

	@Step("switching to default Content")
	public static void switchToDefaultContent() {
		DriverManager.getDriver().switchTo().defaultContent();
	}

	@Step("get the current Frame")
	public static Object getCurrentFrame() {
		return ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return self.name");
	}

	@Step("execute JavaScript")
	public static Object jsExeCute(String script) {
		return ((JavascriptExecutor) DriverManager.getDriver()).executeScript(script);
	}
	public static void clearLocalStorage() {
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("window.localStorage.clear();");
    }

    public static void clearBrowserCookie() {
        DriverManager.getDriver().manage().deleteAllCookies();
    }

}
