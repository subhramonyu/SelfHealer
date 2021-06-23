package org.client.UI.tests;

import org.client.UI.pages.HomePage;
import org.client.UI.tools.Config;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;




@Story("Search")
@Feature("Home Page")


public class SearchTests {
	private HomePage home;

	
	@BeforeClass(groups = { Config.TEST})
	public void initClass() {
		home = new HomePage();
	}
	
	
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups = {Config.TEST }, priority = 0001)
	@Parameters({"text"})
	@Description("Searching the text Gmail")
	public void searchTest(String text) throws Exception {
		home.clickOnSearchBox();
		home.clickOnSearchBox(text);
	}
	
	
	
	
	

}
