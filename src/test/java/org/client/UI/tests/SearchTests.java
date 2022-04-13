package org.client.ui.tests;

import org.client.ui.pages.HomePage;
import org.client.ui.utils.TestGroup;
import org.client.factory.config.BaseTest;
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


public class SearchTests extends BaseTest {
	private HomePage home;

	
	@BeforeClass(groups = { TestGroup.TEST})
	public void initClass() {
		home = new HomePage();
	}
	
	
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups = {TestGroup.TEST }, priority = 0001)
	@Parameters({"text"})
	@Description("Searching the text Gmail")
	public void searchTest(String text) throws Exception {
		home.enterEmail();
		home.clickOnSearchBox(text);
		
	}
	
	
	
	
	

}
