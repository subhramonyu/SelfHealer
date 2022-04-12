package org.client.Factory.utils;

import java.util.Comparator;

import org.apache.log4j.Logger;
import org.client.UI.core.DriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.collect.ImmutableMap;

import io.qameta.allure.Attachment;

public class AllureManager {

    private AllureManager() {
    }

    private static final Logger Log = Logger.getLogger(AllureManager.class);

    public static void setAllureEnvironment(WebDriver driver, Dimension dimension) {
        Log.info("Setting up Allure environment information");
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();

        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .orderEntriesByValue(Comparator.naturalOrder())
                        .put("OS Name", System.getProperty("os.name"))
                        .put("OS Version", System.getProperty("os.version"))
                        .put("Browser Name", cap.getBrowserName())
                        .put("Browser Version", cap.getVersion())
                        .put("Browser Dimension", dimension.getWidth() + " * " + dimension.getHeight())
                        .build(), System.getProperty("allure.results.directory") + "/");
    }

    private static void allureEnvironmentWriter(ImmutableMap<String, String> build, String string) {
		
	}

	@Attachment(value = "Screenshot of {0} test", type = "image/png")
    public static byte[] takeScreenShot(String methodName) {
        Log.info("Taking screenshot for test: " + methodName);
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
