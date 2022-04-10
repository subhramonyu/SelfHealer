package org.client.UI.pages;

import org.apache.log4j.Logger;
import org.client.UI.core.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.model.Log;

import io.qameta.allure.Step;

public class HomePage  {

	@FindBy(how = How.CSS, using = "#enterimg")
	private WebElement SearchBox;
	
	private static Logger Log = Logger.getLogger(HomePage.class);

	

	public HomePage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
		

	}

	@Step("Clicking on Search box")
	public void clickOnSearchBox() {
		Log.info("Clearing the search field");
		//SearchBox.clear();
		Log.info("Clicking the search field");
		SearchBox.click();
		
	}
	
	@Step("Enter text to be Searched :{0}")
	public void clickOnSearchBox(String textTobeSearched) {
		Log.info("Entering the Text");
		//sendKeys(SearchBox, textTobeSearched);	
		//SearchBox.sendKeys(textTobeSearched);
	}

	
}
