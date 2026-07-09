package io.davidsusanto.restfulbooker.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

/**
 * Centralized REST Assured response specifications, named nu intent.
 */
public class ResponseSpecFactory {

    /**
     * 200 OK with a JSON body: get/create/update/partial-update.
     */
    public static ResponseSpecification okJson() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    /**
     * 201 Created: Restful-booker returns this for DeleteBooking and Ping.
     */
    public static ResponseSpecification created() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }

    /**
     * 403 Forbidden: protected operation with a missing/invalid token.
     */
    public static ResponseSpecification forbidden() {
        return new ResponseSpecBuilder()
                .expectStatusCode(403)
                .build();
    }

    /**
     * 404 Not Found: booking id does not exist.
     */
    public static ResponseSpecification notFound() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }
}
