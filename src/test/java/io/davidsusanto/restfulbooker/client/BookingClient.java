package io.davidsusanto.restfulbooker.client;

import io.davidsusanto.restfulbooker.models.Booking;
import io.davidsusanto.restfulbooker.specs.RequestSpecFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Client for the Booking resource
 * GET     /booking        -> GetBookingIds
 * GET     /booking/{id}   -> GetBooking
 * POST    /booking        -> CreateBooking
 * PUT     /booking/{id}   -> UpdateBooking        (auth required)
 * PATCH   /booking/{id}   -> PartialUpdateBooking (auth required)
 * DELETE  /booking/{id}   -> DeleteBooking        (auth required)
 * <p>
 * The API accepts auth either via Cookie "token=..." or Basic Authorization.
 * This client uses the Cookie token approach.
 */
public class BookingClient {

    private static final String BOOKING = "/booking";

    public Response createBooking(Booking booking) {
        return given()
                .spec(RequestSpecFactory.request())
                .body(booking)
                .when()
                .post(BOOKING);
    }
}
