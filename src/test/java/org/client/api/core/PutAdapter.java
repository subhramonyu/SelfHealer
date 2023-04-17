package org.client.api.core;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * This class is to handle all the HTTP PUT requests.
 * @author subhra.das
 *
 */

public class PutAdapter extends AbstractAdapter implements RestAdapter {
    private String name;

    protected PutAdapter(GetBuilder<?, ?> builder) {
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
        Response response = given()
                .baseUri(getBaseURL())
                .contentType(getContentType().getType())
                .body(getObject())
                .expect()
                .log().all()

                .when()
                .put(getEndPoint());

        return response;
    }


    @Override
	public Response execute(Map<String, ?> parametersMap) {
		// TODO Auto-generated method stub
		return null;
	}



    public static abstract class GetBuilder<S extends PutAdapter, B extends GetBuilder<S, B>> extends AbstractAdapter.AbstractBuilder<S, B> {
        private String name;

        @SuppressWarnings("unchecked")
        public B name(String name) {
            this.name = name;
            return (B) this;
        }

    }

    private static class DefaultGetBuilder extends GetBuilder<PutAdapter, DefaultGetBuilder> {
        @Override
        public PutAdapter build() {
            return new PutAdapter(this);
        }
    }

	@Override
	public JsonPath execute(Response response) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	
}