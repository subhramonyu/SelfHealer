package org.client.api.config;

public enum ContentType {

    JSON("application/json"),
    XML("text/xml; charset=utf-8");

    private String type;

    private ContentType(String contentType) {
        this.type = contentType;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.getType();
    }
}