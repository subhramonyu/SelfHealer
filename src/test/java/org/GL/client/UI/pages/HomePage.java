package org.GL.client.UI.pages;

import org.GL.client.UI.core.DriverManager;
import org.GL.client.UI.core.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class HomePage  {

	@FindBy(how = How.CSS, using = "#enterimg")
	private WebElement SearchBox;
	
	

	

	public HomePage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
		Log.setLogger("LandingPage");

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
