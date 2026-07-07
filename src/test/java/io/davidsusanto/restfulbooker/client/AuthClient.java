package io.davidsusanto.restfulbooker.client;

import io.davidsusanto.restfulbooker.models.AuthRequest;
import io.davidsusanto.restfulbooker.specs.RequestSpecFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Client for the Auth resource.
 * POST /auth -> CreateToken
 */
public class AuthClient {

    public Response createToken(AuthRequest request) {
        return given()
                .spec(RequestSpecFactory.request())
                .body(request)
                .when()
                .post("/auth");
    }

    public Response createToken(String username, String password) {
        return createToken(new AuthRequest(username, password));
    }
}
