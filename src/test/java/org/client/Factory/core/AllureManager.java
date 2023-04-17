package org.client.factory.core;

import static org.client.factory.utils.FileUtil.getProperty;

import java.util.Comparator;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.util.ResultsUtils;
public class AllureManager {

	private AllureManager() {
	}

	private static final Logger Log = Logger.getLogger(AllureManager.class);

	public static void setAllureEnvironment(WebDriver driver, Dimension dimension) {
		Log.info("Setting up Allure environment information");
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();

		allureEnvironmentWriter1(ImmutableMap.<String, String>builder().orderEntriesByValue(Comparator.naturalOrder())
				.put("OS Name", System.getProperty("os.name")).put("OS Version", System.getProperty("os.version"))
				.put("Browser Name", cap.getBrowserName()).put("Browser Version", cap.getVersion())
				.put("Browser Dimension", dimension.getWidth() + " * " + dimension.getHeight()).build(),
				System.getProperty("allure.results.directory") + "/");
	}
	
	public static void setAllureEnvironment() {
		Log.info("Setting up Allure environment information");
		

		allureEnvironmentWriter(ImmutableMap.<String, String>builder().orderEntriesByValue(Comparator.naturalOrder())
				.put("OS Name", System.getProperty("os.name"))
				.put("OS Version", System.getProperty("os.version"))
				.put("PROJECT", getProperty(Config.Env_Property, "PROJECT"))
				.put("Environment", getProperty(Config.Env_Property, "ENV"))
				.put("Release Version", getProperty(Config.Env_Property, "Release.version")),
				System.getProperty("allure.results.directory") + "/");
	}
	private static void allureEnvironmentWriter(Builder<String, String> builder, String string) {

	}

	private static void allureEnvironmentWriter1(ImmutableMap<String, String> immutableMap, String string) {

	}

	private static void step(String name, Runnable runnable) {
		String uuid = UUID.randomUUID().toString();
		StepResult result = new StepResult().setName(name);
		Allure.getLifecycle().startStep(uuid, result);
		try {
			runnable.run();
			Allure.getLifecycle().updateStep(uuid, s -> s.setStatus(Status.PASSED));
		} catch (Throwable e) {
			Allure.getLifecycle().updateStep(uuid, s -> s.setStatus(ResultsUtils.getStatus(e).orElse(Status.BROKEN))
					.setStatusDetails(ResultsUtils.getStatusDetails(e).orElse(null)));
			throw e;
		} finally {
			Allure.getLifecycle().stopStep(uuid);
		}
	}

	/* To Add Allure step explicitly to the script */
	public static void step(String stepName) {
		step(stepName, () -> {
		});
	}

	@Attachment(value = "Screenshot of {0} test", type = "image/png")
	public static byte[] takeScreenShot(String methodName) {
		Log.info("Taking screenshot for test: " + methodName);
		return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
	}

}
