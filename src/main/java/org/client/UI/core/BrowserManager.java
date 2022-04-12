package org.client.UI.core;

import static org.client.UI.utils.Config.MIN_BROWSER_WIDTH;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.client.Factory.utils.AllureManager;
import org.client.UI.utils.Config;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserManager {
	public static final int DRIVER_IMPLICIT_WAIT = 5;
	private static final Logger Log = Logger.getLogger(BrowserManager.class);
	private static DesiredCapabilities capabilities;

	public static WebDriver getBrowser(String browserName) {

		WebDriver driver = getDriver(browserName);
		driver.manage().timeouts().implicitlyWait(DRIVER_IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Dimension dimension = driver.manage().window().getSize();
		Log.info("Browser started with dimension : " + dimension);

		if (dimension.getWidth() < MIN_BROWSER_WIDTH) {
			driver.manage().window().setSize(new Dimension(MIN_BROWSER_WIDTH, dimension.getHeight()));
			dimension = driver.manage().window().getSize();
			Log.info("Browser dimension was reset to " + dimension);
		}
		AllureManager.setAllureEnvironment(driver, dimension);
		return driver;
	}

	private static WebDriver getDriver(String browserName) {
		String browserType = Config.CHROME;
		WebDriver driver;

		if (Config.FIREFOX.equalsIgnoreCase(browserName)) {
			browserType = Config.FIREFOX;

			WebDriverManager.firefoxdriver().setup();
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.download.folderList", 2);
			firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
			firefoxProfile.setPreference("browser.download.dir", Config.DEFAULT_DOWNLOAD_PATH);
			firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/xls");
			firefoxProfile.setPreference("pdfjs.disabled", true);
			firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
			firefoxProfile.setPreference("plugin.scan.plid.all", false);
			firefoxProfile.setPreference("plugin.disable_full_page_plugin_for_types", "application/pdf");

			// JavaScriptError.addExtension(firefoxProfile);
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(firefoxProfile);
			driver = new FirefoxDriver(options);
		}

		else if (Config.OPERA.equalsIgnoreCase(browserName)) {
			browserType = Config.OPERA;

			WebDriverManager.operadriver().setup();
			OperaOptions operaOption = new OperaOptions();
			
			driver = new OperaDriver(operaOption);
			

		} else {

			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			LoggingPreferences loggingprefs = new LoggingPreferences();
			loggingprefs.enable(LogType.BROWSER, Level.INFO);
			loggingprefs.enable(LogType.PERFORMANCE, Level.INFO);
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", Config.DEFAULT_DOWNLOAD_PATH);
			prefs.put("download.prompt_for_download", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-pdf-material-ui");
			options.addArguments("--disable-out-of-process-pdf");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(options);

		}
		Log.info("Started " + browserType + " browser");
		return driver;

	}
}
