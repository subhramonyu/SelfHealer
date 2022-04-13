package org.client.api.core;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * This class is to handle all the HTTP GET requests.
 * @author subhra.das
 *
 */

public class GetAdapter extends AbstractAdapter implements RestAdapter {
	private String name;

	protected GetAdapter(GetBuilder<?, ?> builder) {
		super(builder);
		this.name = builder.name;

	}

	public static GetBuilder<?, ?> builder() {
		return new DefaultGetBuilder();
	}

	public String getName() {
		return name;
	}

	@Override
	public Response execute() {
		Response response = given().baseUri(getBaseURL()).contentType(getContentType().getType())
				.expect().log().all().when().get(getEndPoint());
		return response;
	}
	
	@Override
	public JsonPath execute(Response response) {
		return new JsonPath(response.asString());
	}

	@Override
	public Response execute(Map<String,?> parametersMap) {
		Response response=	given().baseUri(getBaseURL()).contentType(getContentType().getType())
		.queryParams(parametersMap).expect().log().all().when()
		.get(getEndPoint());
		return response;
	}

	public static abstract class GetBuilder<S extends GetAdapter, B extends GetBuilder<S, B>>
			extends AbstractBuilder<S, B> {
		private String name;

		@SuppressWarnings("unchecked")
		public B name(String name) {
			this.name = name;
			return (B) this;
		}

	}

	private static class DefaultGetBuilder extends GetBuilder<GetAdapter, DefaultGetBuilder> {
		@Override
		public GetAdapter build() {
			return new GetAdapter(this);
		}
	}



}