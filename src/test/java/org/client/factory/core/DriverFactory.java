package org.client.factory.core;

import org.apache.log4j.Logger;
import org.client.performance.config.EventListnerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.epam.healenium.SelfHealingDriver;

public class DriverFactory {

	public static WebDriver driver;


	private static Logger Log = Logger.getLogger(DriverFactory.class);

	public final void driverInit(String browserName, String userName, String password,
			boolean performanceFlag, boolean isSelfHealing, boolean checkBrowserDimension, boolean isHeadless)
			throws Exception {

		try {

			DriverManager.setDriverName(browserName);
			DriverManager.setUserName(userName);
			DriverManager.setPassword(password);
			DriverManager.setIsHeadless(isHeadless);
			DriverFactory.driver = BrowserManager.getBrowser(browserName, checkBrowserDimension);

			if (performanceFlag) {
				EventListnerUtils eventlistner = new EventListnerUtils();
				EventFiringWebDriver Eventdriver = new EventFiringWebDriver(DriverFactory.driver)
						.register(eventlistner);
				Log.info("Driver instantiated in Performace mode....");
				DriverManager.setDriver(Eventdriver);
			}

			if (isSelfHealing) {
				SelfHealingDriver MLDriver = SelfHealingDriver.create(DriverFactory.driver);
				Log.info("Driver instantiated in SelfHealing mode....");
				DriverManager.setDriver(MLDriver);
			} else {
				DriverManager.setDriver(DriverFactory.driver);
			}
			Log.info("Driver initialized sucessfully....");

		} catch (Throwable e) {
			Log.error("Driver initialization fails", e);
		}
	}

}
