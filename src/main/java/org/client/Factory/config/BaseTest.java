package org.client.Factory.config;

import static org.client.Factory.config.FileUtil.readFromPropertyFile;

import org.apache.log4j.Logger;
import org.client.UI.config.ListenerService;
import org.client.UI.core.DriverFactory;
import org.client.UI.core.DriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners(ListenerService.class)
public class BaseTest extends DriverFactory{

	private Logger Log = Logger.getLogger(BaseTest.class);
	
	@BeforeSuite(alwaysRun = true)
	
	@Parameters({ "browserName","URL","driver","driverName","userName","password", "environmentName", "performanceFlag","isSelfHealing" })
	
	public void envSetup(@Optional("true")boolean isGUI) {
		if (!isGUI) {
			
		}
		else {
			//init();
		}
		
	}

	
	
@BeforeTest()
	public void init(String browserName,
			@Optional("") String URL,
			@Optional String driver,
			@Optional String driverName,
			@Optional String userName,
			@Optional String password,
			@Optional("QA env") String environmentName,
			@Optional("false") boolean performanceFlag,
			@Optional("false") boolean isSelfHealing) {

		try {
			
			driverInit(browserName,driver,driverName,userName,password, performanceFlag ,isSelfHealing);
			if (URL.isEmpty()) {
			DriverManager.getDriver().get(readFromPropertyFile(Config.Env_Property, "BASEURL"));
			Log.warn("loading the Testing URL.....if not expected ,please provide the Actual URL in TestNg XML ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Test initialization fails");
		}

	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		try {
			 try {
		           DriverManager.getDriver().quit();
		           DriverManager.clearContext();
		        } catch (Exception e) {
		            Log.error("Driver tear down error", e);
		        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
