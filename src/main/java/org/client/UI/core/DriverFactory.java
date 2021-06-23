package org.client.UI.core;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.client.CommonUtils.CommonUtils;
import org.client.CommonUtils.Constants;
import org.client.CommonUtils.FileUtil;
import org.client.Performance.utils.EventListnerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Optional;

public class DriverFactory {

	private static DriverFactory instance = null;
	public static WebDriver webdriver;
	public static EventFiringWebDriver driver;
	public static EventListnerUtils eventlistner;

	private static DesiredCapabilities capabilities;
	private static ChromeOptions chromeOptions;

	private DriverFactory() {
	}

	public static DriverFactory getInstance() {
		if (instance == null) {
			instance = new DriverFactory();
		}
		return instance;
	}

	public final void driverInit(String browserName, @Optional("false") boolean performanceFlag) throws Exception {
		// DriverManager.setLog(log);

		try {

			switch (browserName) {
			case "ChromeHeadless":
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedrivercanary");
				chromeOptions.setBinary("/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary");
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("window-size=2560x1600");
				driver = new EventFiringWebDriver(webdriver);
				DriverManager.setDriver(driver);
				break;
			case "Chrome":
				System.setProperty("webdriver.chrome.driver", Constants.DRIVER_PATH + "/chromedriver.exe");
				LoggingPreferences loggingprefs = new LoggingPreferences();
				loggingprefs.enable(LogType.BROWSER, Level.INFO);
				loggingprefs.enable(LogType.PERFORMANCE, Level.INFO);
				capabilities = new DesiredCapabilities();
				capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
				chromeOptions = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("download.default_directory", Constants.DEFAULT_DOWNLOAD_PATH);
				prefs.put("download.prompt_for_download", false);
				chromeOptions.setExperimentalOption("prefs", prefs);
				chromeOptions.addArguments("--disable-pdf-material-ui");
				chromeOptions.addArguments("--disable-out-of-process-pdf");
				chromeOptions.addArguments("--start-maximized");
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				webdriver = new ChromeDriver(chromeOptions);
				driver = new EventFiringWebDriver(webdriver);
				DriverManager.setDriver(driver);
				break;

			case "Firefox":
				System.setProperty("webdriver.gecko.driver", Constants.DRIVER_PATH + "/geckodriver.exe");
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				firefoxProfile.setPreference("browser.download.folderList", 2);
				firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
				firefoxProfile.setPreference("browser.download.dir", Constants.DEFAULT_DOWNLOAD_PATH);
				firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/xls");
				firefoxProfile.setPreference("pdfjs.disabled", true);
				firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
				firefoxProfile.setPreference("plugin.scan.plid.all", false);
				firefoxProfile.setPreference("plugin.disable_full_page_plugin_for_types", "application/pdf");

				// JavaScriptError.addExtension(firefoxProfile);
				FirefoxOptions options = new FirefoxOptions();
				options.setProfile(firefoxProfile);
				webdriver = new FirefoxDriver(options);
				driver = new EventFiringWebDriver(webdriver);
				DriverManager.setDriver(driver);
				break;
			default:

				break;
			}

			if (performanceFlag) {
				eventlistner = new EventListnerUtils();
				driver.register(eventlistner);
			}

			DriverManager.getDriver().get(FileUtil.readFromPropertyFile(Constants.Env_Property, "BASEURL"));
			// Ensure to load the page
			CommonUtils.wait(5);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	
}
