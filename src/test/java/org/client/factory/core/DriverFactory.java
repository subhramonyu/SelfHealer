package org.client.factory.core;

import org.apache.log4j.Logger;
import org.client.performance.config.EventListnerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.epam.healenium.SelfHealingDriver;

public class DriverFactory {

	public static WebDriver driver;


	private static Logger Log = Logger.getLogger(DriverFactory.class);

	public final void driverInit(String browserName, String driverName, String userName, String password,
			boolean performanceFlag, boolean isSelfHealing) throws Exception {

		try {

			DriverFactory.driver = BrowserManager.getBrowser(browserName);
			Log.info("Driver initialized sucessfully....");
			DriverManager.setDriver(DriverFactory.driver);
			DriverManager.setDriverName(driverName);
			DriverManager.setUserName(userName);
			DriverManager.setPassword(password);

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
			}

		} catch (Throwable e) {
			Log.error("Driver initialization fails", e);
		}
	}

}
