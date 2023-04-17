package org.client.api.core;

import java.util.Map;



import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * This Interface for building the different prototype of the methods for executing the REST requests.
 * @author subhra.das
 *
 */
public interface RestAdapter {

	Response execute();
	JsonPath execute(Response response);
	Response execute(Map<String, ?> parametersMap);
	
}