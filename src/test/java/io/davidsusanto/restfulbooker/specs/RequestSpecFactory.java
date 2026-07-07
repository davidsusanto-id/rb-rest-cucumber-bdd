package io.davidsusanto.restfulbooker.specs;

import io.davidsusanto.restfulbooker.config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Centralized REST Assured request specification.
 * Keeps base URI, headers, content type, and logging consistent across all API clients.
 */
public class RequestSpecFactory {

    public static RequestSpecification request() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.baseUri())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}
