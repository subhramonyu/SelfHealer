package org.client.Performance.core;

import org.client.Factory.utils.FactoryUtils;
import org.client.UI.core.DriverManager;
import org.openqa.selenium.JavascriptExecutor;

public class PerformanceEvent extends FactoryUtils {
	
	
	
	public static long getPageLoadTIme() {
		return getEventValue(EventAttribute.LOAD_EVENT_END) - getEventValue(EventAttribute.NAVIGATION_START) ;
	}

	public static long getNetworkConnectionTime() {
		return getEventValue(EventAttribute.CONNECT_END) - getEventValue(EventAttribute.DOMAIN_LOOKUP_START);
	}

	public static long getPageRenderTime() {
		return getEventValue(EventAttribute.DOM_COMPLETE) - getEventValue(EventAttribute.DOM_LOADING);
	}

	public static long getServerResponseTime() {
		return getEventValue(EventAttribute.RESPONSE_END) - getEventValue(EventAttribute.REQUEST_START);
	}

	public static long getEventValue(EventAttribute event) {
		String script = String.format("return window.performance.timing.%s;", event);
		JavascriptExecutor js =  (JavascriptExecutor) DriverManager.getDriver();
		return (long) js.executeScript(script);
	}

}
