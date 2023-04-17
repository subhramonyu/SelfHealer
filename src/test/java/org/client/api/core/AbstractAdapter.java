package org.client.api.core;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.client.api.config.ContentType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.qameta.allure.Step;
/**
 * This class is to construct the HTTP request using getter and setter
 * @author subhra.das
 *
 */

public class AbstractAdapter {
    private Object object;
    private String endPoint;
    private String baseURL;
    private ContentType contentType;
    private String parameterName;
    private String parameterValue;
    private int statusCode;

    protected AbstractAdapter(AbstractBuilder<?, ?> builder) {
        this.object = builder.object;
        this.endPoint = builder.endPoint;
        this.baseURL = builder.baseURL;
        this.contentType = builder.contentType;
        this.parameterName = builder.parameterName;
        this.parameterValue = builder.parameterValue;
        this.statusCode = builder.statusCode;
    }

    public static AbstractBuilder<?, ?> builder() {
        return new DefaultAbstractBuilder();
    }

    public Object getObject() {
        prettyPrint();
        return object;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public ContentType getContentType() {
        return contentType;
    }
    
    public String getParameterName() {
  		return parameterName;
  	}
    
    public String getParameterValue() {
		return parameterValue;
	}

    public int getStatusCode() {
		return statusCode;
	}
    
    
    private void prettyPrint() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(("\nNavigating to the URL : "
                + getBaseURL()+ getEndPoint()));
        System.out.println("Sending request with  : \t\t");
        System.out.println(gson.toJson(object) + "\n");
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("object", object)
                .append("BASE URL", baseURL)
                .append("endPoint", endPoint)
                .append("contentType", contentType)
                .toString();
    }

  

	

	

	





	public static abstract class AbstractBuilder<S extends AbstractAdapter, B extends AbstractBuilder<S, B>> {
        private Object object;
        private String endPoint;
        private String baseURL;
        private ContentType contentType;
        private String parameterName;
        private String parameterValue;
        private int statusCode;

        @Step("Setting the PayLoad as : {0}")
        @SuppressWarnings("unchecked")
        public B setRequestObject(Object object) {
            this.object = object;
            return (B) this;
        }

        @Step("Settin the END Point to : {0}")
        @SuppressWarnings("unchecked")
        public B setEndPoint(String endPoint) {
            this.endPoint = endPoint;
            return (B) this;
        }

        @Step("Settingg up the Base URL as : {0}")
        @SuppressWarnings("unchecked")
        public B setBaseURL(String baseURL) {
            this.baseURL = baseURL;
            return (B) this;
        }

        @Step("Setting the Content Type as {0}" )
        @SuppressWarnings("unchecked")
        public B setContentType(ContentType contentType) {
            this.contentType = contentType;
            return (B) this;
        }
        
        
        @SuppressWarnings("unchecked")
        public B setParameterName(String parameterName) {
    		this.parameterName = parameterName;
    		return (B) this;
    	}

    	
        @SuppressWarnings("unchecked")
    	public B setParameterValue(String parameterValue) {
    		this.parameterValue = parameterValue;
    		return (B) this;
    	}
        
        public B setStatusCode(int statusCode) {
    		this.statusCode = statusCode;
    		return (B) this;
    	}

        public abstract S build();
    }

    private static class DefaultAbstractBuilder extends AbstractBuilder<AbstractAdapter, DefaultAbstractBuilder> {

        @Override
        public AbstractAdapter build() {
            return new AbstractAdapter(this);
        }
    }
}