package io.davidsusanto.restfulbooker.client;

import io.davidsusanto.restfulbooker.specs.RequestSpecFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Client for the health/availability resource.
 *  GET /ping -> HealthCheck
 */
public class HealthClient {

    public Response ping() {
        return given()
                .spec(RequestSpecFactory.request())
                .when()
                .get("/ping");
    }
}
