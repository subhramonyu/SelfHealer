package org.client.performance.core;

import java.util.HashMap;

public class NavigationTiming extends PerformanceEvent {

	public void getEventTimeInSeconds(String EventName) {
		loadData();
	}

	public  static HashMap<String, Long> loadData() {
		HashMap<String, Long> performancematrix = new HashMap<String, Long>();
		performancematrix.put(EventAttribute.PAGE_LOAD_TIME.toString(), getPageLoadTIme());
		performancematrix.put(EventAttribute.PAGE_RENDER_TIME.toString(), getPageRenderTime());
		performancematrix.put(EventAttribute.NETWORK_CONNECTION_TIME.toString(), getNetworkConnectionTime());
		performancematrix.put(EventAttribute.SERVER_RESPONSE_TIME.toString(), getServerResponseTime());
		
		return performancematrix;
		
	}
	

}
