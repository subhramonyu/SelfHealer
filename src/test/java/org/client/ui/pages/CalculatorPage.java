package org.client.ui.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.qameta.allure.Step;

public class CalculatorPage  extends BasePage{
	
	@FindBy(how = How.CSS, using = "#key-add")
	private WebElement addButton;
	
	@FindBy(how =How.CSS,using ="#key-equals")
	private WebElement equalButton;
	
	@FindBy(how=How.CSS,using ="div.calculator-display")
	private WebElement calculatorDisplay;
	private static Logger Log = Logger.getLogger(CalculatorPage.class);

	

	@Step("Add two number")
	public int add(String num1 ,String num2) {
		Log.info("Clicking 1st number");
		click(getNumberKey(num1));
		Log.info("Clicking on the + button");
		click(addButton);
		Log.info("Clicking 2nd number");
		click(getNumberKey(num2));
		Log.info("Enter equal");
		click(equalButton);
		return Integer.valueOf(calculatorDisplay.getText()); 
		
	}
	
	private WebElement getNumberKey(String num) {
		
		return getElementByDynamicPath("//*[@value='%s']", num);
		
	}
	

}
