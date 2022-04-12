package org.client.UI.utils;

import org.client.Factory.utils.FactoryUtils;
import org.client.Factory.utils.FileUtil;

public class Config 
{
	
	// **** driver name constants ****
    final public static String WEB_DRIVER = "Web";
	
	// **** driver  name constants **** 
	final public static String CHROME = "Chrome";
	final public static String FIREFOX = "Firefox";
	final public static String IE = "InternetExploler";
	final public static String SAFARI = "Safari";
	final public static String OPERA = "Opera";
	
	 // **** environment name constants *****
    final public static String PRODUCTION = "Production";
    final public static String DEVELOPMENT = "Dev";
	
	
	// ****** Time out Constant **********
	final public static int TIMEOUT_IN_SECONDS = 15;
	final public static int POLLING_TIME_IN_SECONDS = 1;
	

	
	
	
	// *********** file path **************
	public final static String DRIVER_PATH = FactoryUtils.getUserCurrentDirectoryPath()+"/drivers";
	public final static String DEFAULT_DOWNLOAD_PATH = FactoryUtils.getUserCurrentDirectoryPath()+"/Downloads";
	public final static String Env_Property = FactoryUtils.getUserCurrentDirectoryPath()+"/src/main/resources/Environment.properties";
	public final static String Performance_JsonFile = FactoryUtils.getUserCurrentDirectoryPath()+"/files/PerformanceLogs/LatestPerfMatric.json";
	public final static String UILogs = FactoryUtils.getUserCurrentDirectoryPath()+"/files/UILogs/";
	
	
	
	public static final int MIN_BROWSER_WIDTH = Integer.parseInt(FileUtil.readFromPropertyFile(Env_Property,"minimum.browser.width"));
	public enum LocatorStrategy
	{

		WEB_LOCATOR_STRATEGY_ID, WEB_LOCATOR_STRATEGY_CSS, WEB_LOCATOR_STRATEGY_CSS_INPUT_DATA_FEATURE,
		WEB_LOCATOR_STRATEGY_XPATH, WEB_LOCATOR_STRATEGY_XPATH_TEXT, WEB_LOCATOR_STRATEGY_XPATH_DIV_TEXT_CONTAINS, WEB_LOCATOR_STRATEGY_XPATH_SPAN_TEXT_CONTAINS,
		WEB_LOCATOR_STRATEGY_XPATH_TEXT_CONTAINS,
		
		NONE 
	}
	
	// **** element locator strategies constants ****
	public final static String WEB_LOCATOR_STRATEGY_ID = "id";
	public final static String WEB_LOCATOR_STRATEGY_CSS = "css";
	public final static String WEB_LOCATOR_STRATEGY_XPATH = "xpath";
	public final static String WEB_LOCATOR_STRATEGY_XPATH_DIV_TEXT_CONTAINS = "xpath_div_text_contains";
	public final static String WEB_LOCATOR_STRATEGY_XPATH_SPAN_TEXT_CONTAINS = "xpath_span_text_contains";

    
    // **** seprators in session start time stamp **** 
 	final public static String WEB_DATE_FORMAT = "MM/dd/yyyy";
 	final public static String WEB_TIME_FORMAT = "H:mm";
 	
	
	
	// ****** scroll direction *********	
	public enum ScrollDiection
	{
		RIGHT, LEFT, UP, DOWN, NEXT
	}
	

	
    
	
	
	
}
