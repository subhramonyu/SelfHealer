package org.client.factory.core;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import org.codehaus.plexus.util.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

import io.qameta.allure.Attachment;

public class AllureManager {

	private AllureManager() {
	}

	private static final Logger LOG = LoggerFactory.getLogger(AllureManager.class);

	public static void setAllureEnvironment() {
		String environmentFileName = "environment.xml";
		Path source = Path.of(System.getProperty("allure.results.directory") + File.separator + environmentFileName);
		if (Files.notExists(source)) {
			LOG.info("Setting up Allure environment information");

			allureEnvironmentWriter(
					ImmutableMap.<String, String>builder().orderEntriesByValue(Comparator.naturalOrder())
							.put("Environment", StringUtils.capitalise(System.getProperty("env.name", "dev")))
							.put("OS Name", System.getProperty("os.name"))
							.put("OS Version", System.getProperty("os.version")).build(),
					System.getProperty("allure.results.directory") + File.separator);
		}
	}

	public static void setAllureEnvironment(WebDriver driver) {
		String environmentFileName = "environment.xml";
		Path source = Path.of(System.getProperty("allure.results.directory") + File.separator + environmentFileName);
		if (Files.notExists(source)) {
			LOG.info("Setting up Allure environment information");
			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			Dimension dimension = driver.manage().window().getSize();

			allureEnvironmentWriter(
					ImmutableMap.<String, String>builder().orderEntriesByValue(Comparator.naturalOrder())
							.put("Environment", StringUtils.capitalise(System.getProperty("env.name", "dev")))
							.put("OS Name", System.getProperty("os.name"))
							.put("OS Version", System.getProperty("os.version"))
							.put("Browser Name", StringUtils.capitalise(cap.getBrowserName()))
							.put("Browser Version", cap.getVersion())
							.put("Browser Dimension", dimension.getWidth() + " * " + dimension.getHeight()).build(),
					System.getProperty("allure.results.directory") + File.separator);
		}
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
		if (DriverManager.getDriver() != null) {
			LOG.info("Taking screenshot for test: {}{}", methodName, System.getProperty("line.separator"));
			return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
		} else {
			LOG.info("Failed to take screenshot for test: '{}'. WebDriver is null.{}", methodName,
					System.getProperty("line.separator"));
			return null;
		}
	}

}
