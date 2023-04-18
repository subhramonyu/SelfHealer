package org.client.factory.core;

import static java.lang.Boolean.parseBoolean;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.client.factory.core.Config.MIN_BROWSER_WIDTH;

public class BrowserManager {
	public static final int DRIVER_IMPLICIT_WAIT = 5;
	private static final Logger LOG = LoggerFactory.getLogger(BrowserManager.class);

	private BrowserManager() {
	}

	public static WebDriver getBrowser(String browserName, boolean checkBrowserDimension) {
		WebDriver driver = resolveDriver(browserName);
		driver.manage().timeouts().implicitlyWait(DRIVER_IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		LOG.info("Browser started with dimension : " + driver.manage().window().getSize()
				+ ". Check browser dimension: " + Boolean.toString(checkBrowserDimension).toUpperCase());

		driver = resetDimension(checkBrowserDimension, driver);
		AllureManager.setAllureEnvironment(driver);
		return driver;
	}

	private static WebDriver resetDimension(boolean checkBrowserDimension, WebDriver driver) {
		Dimension dimension = driver.manage().window().getSize();
		if (checkBrowserDimension) {
			if (dimension.getWidth() < MIN_BROWSER_WIDTH) {
				driver.manage().window().setSize(new Dimension(MIN_BROWSER_WIDTH, dimension.getHeight()));
				dimension = driver.manage().window().getSize();
				LOG.info("Browser dimension was reset to " + dimension);
			} else {
				LOG.info("Browser dimension is OK");
			}
		}
		return driver;
	}

	private static WebDriver resolveDriver(String browserName) {
		String browserType = Config.CHROME;
		WebDriver driver;

		if (Config.FIREFOX.equalsIgnoreCase(browserName)) {
			browserType = Config.FIREFOX;
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			setChromeHeadlessModeArguments(DriverManager.getIsHeadless(), options);
			driver = new ChromeDriver(options);
		}
		LOG.info("Started " + browserType + " browser");
		return driver;
	}

	private static void setChromeHeadlessModeArguments(boolean isHeadless, ChromeOptions options) {
		if (isHeadless) {
			options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-extensions",
					"--enable-automation", "--window-size=1920,1020");
			LOG.info("Running Tests in Headless mode ...");
		}
	}

}
