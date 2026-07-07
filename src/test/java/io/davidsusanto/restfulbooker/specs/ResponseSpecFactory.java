package io.davidsusanto.restfulbooker.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

/**
 * Centralized REST Assured response specifications, named nu intent.
 */
public class ResponseSpecFactory {

    /**
     * 201 Created: Restful-booker returns this for DeleteBooking and Ping.
     */
    public static ResponseSpecification created() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }
}
