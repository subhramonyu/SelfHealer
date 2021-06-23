package org.client.UI.core;

import org.client.CommonUtils.CommonUtils;
import org.client.UI.tools.Config;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners(ListenerService.class)
public class BaseTest {

	@BeforeTest(groups = { Config.TEST })
	@Parameters({ "browserName", "environmentName", "performanceFlag" })
	public void envSetUp(String browserName, @Optional("QA env") String environmentName,
			@Optional("false") boolean performanceFlag) {
		Log.setLogger("ApplicationLogs");

		try {
			DriverFactory.getInstance().driverInit(browserName, performanceFlag);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterTest(groups = { Config.TEST })
	public void tearDown() {
		try {
			CommonUtils.wait(2);
			DriverManager.getDriver().quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
