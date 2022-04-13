package org.client.factory.config;

import static org.client.factory.config.FileUtil.getProperty;
import static org.client.factory.config.AllureManager.setAllureEnvironment;

import org.apache.log4j.Logger;
import org.client.ui.config.ListenerService;
import org.client.ui.core.DriverFactory;
import org.client.ui.core.DriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners(ListenerService.class)
public class BaseTest extends DriverFactory {
	private String URL;
	private boolean isGUI;

	private Logger Log = Logger.getLogger(BaseTest.class);

	@Parameters({ "isGUI", "browserName", "URL", "driverName", "userName", "password", "environmentName",
			"performanceFlag", "isSelfHealing" })
	@BeforeTest(alwaysRun = true)
	public void init(@Optional("true") boolean isGUI, @Optional("Chrome")String browserName, @Optional("") String URL,
			@Optional String driverName, @Optional String userName, @Optional String password,
			@Optional("QA env") String environmentName, @Optional("false") boolean performanceFlag,
			@Optional("false") boolean isSelfHealing) {
		this.isGUI = isGUI;

		try {
			if (!isGUI) {
				setAllureEnvironment();
				setURL((!URL.isEmpty() ? this.URL : getProperty(Config.Env_Property, "BASEURL")));
			} else {

				driverInit(browserName, driverName, userName, password, performanceFlag, isSelfHealing);
				setURL((!URL.isEmpty() ? this.URL : getProperty(Config.Env_Property, "BASEURL")));
				driver.get(getURL());

			}
		}

		catch (Exception e) {
			e.printStackTrace();
			Log.error("Test initialization fails");
		}

	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		
		if (isGUI) {
			try {
				DriverManager.getDriver().quit();
				DriverManager.clearContext();
			} catch (Exception e) {
				Log.error("Driver tear down error", e);
			}
		}else {
				Log.info("Tearing down the test...");
			}
		
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

}
