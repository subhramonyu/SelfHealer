package org.client.api.core;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
/**
 * This class is to handle all the HTTP DELETE requests.
 * @author subhra.das
 *
 */
public class DeleteAdapter extends AbstractAdapter implements RestAdapter {
    private String name;

    protected DeleteAdapter(DeleteBuilder<?, ?> builder) {
        super(builder);
        this.name = builder.name;

    }

    public static DeleteBuilder<?, ?> builder() {
        return new DefaultDeleteBuilder();
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
                .delete(getEndPoint());
        return response;
    }
    
    @Override
	public Response execute(Map<String, ?> parametersMap) {
		// TODO Auto-generated method stub
		return null;
	}

   

    public static abstract class DeleteBuilder<S extends DeleteAdapter, B extends DeleteBuilder<S, B>> extends AbstractBuilder<S, B> {
        private String name;

        @SuppressWarnings("unchecked")
        public B name(String name) {
            this.name = name;
            return (B) this;
        }

    }

    private static class DefaultDeleteBuilder extends DeleteBuilder<DeleteAdapter, DefaultDeleteBuilder> {
        @Override
        public DeleteAdapter build() {
            return new DeleteAdapter(this);
        }
    }

	@Override
	public JsonPath execute(Response response) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}