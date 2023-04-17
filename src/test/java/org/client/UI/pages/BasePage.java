package org.client.ui.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.client.factory.core.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class BasePage {
	
	private JavascriptExecutor javaScriptExecutor;
	private static final Logger Log = Logger.getLogger(BasePage.class);
	
	public BasePage() {
		 PageFactory.initElements(DriverManager.getDriver(), this);
		 javaScriptExecutor = (JavascriptExecutor) DriverManager.getDriver();
	}
	
	
	 public WebElement getElementByDynamicPath(String commonPath, String name) {
	        return DriverManager.getDriver().findElement(By.xpath(String.format(commonPath, name)));
	    }

	    public List<WebElement> getElementsByDynamicPath(String commonPath, String name) {
	        return DriverManager.getDriver().findElements(By.xpath(String.format(commonPath, name)));
	    }

	    public List<WebElement> getElementsWithCommonPath(List<String> namesList, String commonPath) {
	        return namesList.stream()
	                .map(name -> getElementByDynamicPath(commonPath, name))
	                .collect(Collectors.toList());
	    }
	    
	    public  void click(WebElement element) {
	        try {
	            element.click();
	            Log.info("Clicked on the element sucessfully :"+element.toString());
	        } catch (Throwable e) {
	           Log.error("Failed to click", e);
	        }
	    }
	    public  void clearValue(WebElement element) {
	    	try {
				element.clear();
				Log.info("Cleared the element sucessfully:"+element.toString());
			} catch (Throwable e) {
				Log.error("Failed to clear ", e);
			}
	    }
	    
	    @Step("Sending Text : {1} to element : {0}")
		public  void enterText(WebElement element, String textToBeTyped) {
			try {
				element.sendKeys(textToBeTyped);
				Log.info("Text typed to the element sucessfully:"+element.toString());
			} catch (Throwable e) {
				Log.error("Failed to enter Text ", e);
			}
		}
	    
	    @Step("Clicking on the WebElement : {0}")
		public  void jsclick(WebElement element) {
			try {
				javaScriptExecutor.executeScript("arguments[0].click();", element);
				Log.info("Clicked on the element using jsexecutor sucessfully:"+element.toString());
			} catch (NoSuchElementException e) {
				Log.error("Failed to click using jsexecutor", e);
			}
		}
	    
	    public  void jsEnterText(WebElement element,String textToBeTyped) {
	    	try {
				javaScriptExecutor.executeScript("arguments[0].value = '"+textToBeTyped+"';", element);
				Log.info("Clicked on the element using jsexecutor sucessfully:"+element.toString());
			} catch (NoSuchElementException e) {
				Log.error("Failed to click using jsexecutor", e);
			}
			((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].value = '';", element);
		}
	   
	    public static void jsClearValue(WebElement element) {
			((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].value = '';", element);
		}
	    
	    public String jsgetText(WebElement element) {
			return (String) ((JavascriptExecutor) DriverManager.getDriver())
					.executeScript("arguments[0].getAttribute('innerHTML')", element);
		}
	    
	    
	   

}
