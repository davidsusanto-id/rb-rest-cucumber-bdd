package io.davidsusanto.restfulbooker.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.davidsusanto.restfulbooker.client.BookingClient;
import io.davidsusanto.restfulbooker.context.ScenarioContext;
import io.davidsusanto.restfulbooker.data.BookingDataFactory;
import io.davidsusanto.restfulbooker.models.Booking;
import io.davidsusanto.restfulbooker.specs.ResponseSpecFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookingSteps {

    private final ScenarioContext context;
    private final BookingClient bookingClient = new BookingClient();

    public BookingSteps(ScenarioContext context) {
        this.context = context;
    }

    // ---------- Create ----------

    @When("I create a booking with default data")
    public void iCreateBookingWithDefaultData() {
        Booking booking = BookingDataFactory.defaultBooking();
        context.put("payload", booking);
        context.setResponse(bookingClient.createBooking(booking));
        Integer id = context.getResponse().jsonPath().getObject("bookingid", Integer.class);
        context.setBookingId(id);
    }

    @Then("the booking should be created successfully")
    public void theBookingShouldBeCreatedSuccessfully() {
        context.getResponse().then().spec(ResponseSpecFactory.okJson());
        assertThat(context.getBookingId(), notNullValue());
        assertThat(context.getBookingId(), greaterThan(0));
    }

    @Then("the created booking should match the submitted data")
    public void theCreatedBookingshouldMatchTheSubmittedData() {
        Booking submitted = (Booking) context.get("payload");
        context.getResponse().then()
                .body("booking.firstname", equalTo(submitted.getFirstname()))
                .body("booking.lastname", equalTo(submitted.getLastname()))
                .body("booking.totalprice", equalTo(submitted.getTotalprice()))
                .body("booking.depositpaid", equalTo(submitted.getDepositpaid()))
                .body("booking.additionalneeds", equalTo(submitted.getAdditionalneeds()));
    }
}
