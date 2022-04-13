package org.client.api.core;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * This class is to handle all HTTP POST requests .
 * 
 * @author subhra.das
 *
 */


public class PostAdapter extends AbstractAdapter implements RestAdapter {
	private String name;

	protected PostAdapter(GetBuilder<?, ?> builder) {
		super(builder);
		this.name = builder.name;

	}

	public static GetBuilder<?, ?> builder() {
		return new DefaultGetBuilder();
	}

	public String getName() {
		return name;
	}

	/**
	 * @param : No @param with out query @param
	 * @return : Response type object
	 */

	@Step("Executing the response")
	@Override
	public Response execute() {
		Response response = given().baseUri(getBaseURL())
				.contentType(getContentType().getType())
				.body(getObject()).expect().log().all().when().post(getEndPoint());

		return response;
	}
	




	/**
	 * @param :Query parameter value and query parameter name         
	 * @return : Response type object
	 */

	@Step("Executing the response ")
	@Override
	public Response execute(Map<String, ?> parametersMap) {
		Response response = given().baseUri(getBaseURL()).contentType(getContentType().getType())
				.queryParams(parametersMap).body(getObject()).expect().log().all().when()
				.post(getEndPoint());

		return response;
	}

	public static abstract class GetBuilder<S extends PostAdapter, B extends GetBuilder<S, B>>
			extends AbstractAdapter.AbstractBuilder<S, B> {
		private String name;

		@SuppressWarnings("unchecked")
		public B name(String name) {
			this.name = name;
			return (B) this;
		}

	}

	private static class DefaultGetBuilder extends GetBuilder<PostAdapter, DefaultGetBuilder> {
		@Override
		public PostAdapter build() {
			return new PostAdapter(this);
		}
	}

	@Override
	public JsonPath execute(Response response) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
