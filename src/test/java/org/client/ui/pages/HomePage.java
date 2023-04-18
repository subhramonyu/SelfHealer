package org.client.ui.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.qameta.allure.Step;

public class HomePage extends BasePage {

	@FindBy(how = How.CSS, using = "#enterimg")
	private WebElement SearchBox;
	
	@FindBy(how =How.CSS,using ="#email")
	private WebElement email;
	
	private static Logger Log = Logger.getLogger(HomePage.class);

	

	@Step("Clicking on Search box")
	public void enterEmail() {
		Log.info("Clicking Email Field");
		click(email);
		Log.info("Clearing email field");
		jsClearValue(email);
		Log.info("Enter Email....");
		enterText(email, "Something");
				
		
	}
	
	@Step("Enter text to be Searched :{0}")
	public void clickOnSearchBox(String textTobeSearched) {
		
		Log.info("Clicking the search field");
		jsclick(SearchBox);
	}

	
}
