package io.davidsusanto.restfulbooker.steps;

import io.cucumber.java.en.Given;
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

    // ---------- Get ----------

    @When("I request all booking ids")
    public void iRequestAllBookingIds() {
        context.setResponse(bookingClient.getBookingIds());
    }

    @When("I request the created booking by id")
    public void iRequestTheCreatedBookingById() {
        context.setResponse(bookingClient.getBooking(context.getBookingId()));
    }

    @When("I request a booking with id {int}")
    public void iRequestABookingWithId(int id) {
        context.setResponse(bookingClient.getBooking(id));
    }

    @Then("a non-empty list of booking ids should be returned")
    public void aNonEmptyListOfBookingIdsShouldBeReturned() {
        context.getResponse().then().spec(ResponseSpecFactory.okJson());
        int size = context.getResponse().jsonPath().getList("bookingid").size();
        assertThat(size, greaterThan(0));
    }

    @Then("the booking details should be returned")
    public void theBookingDetailsShouldBeReturned() {
        context.getResponse().then()
                .spec(ResponseSpecFactory.okJson())
                .body("firstname", notNullValue())
                .body("lastname", notNullValue())
                .body("bookingdates.checkin", notNullValue())
                .body("bookingdates.checkout", notNullValue());
    }

    // ---------- Create ----------

    @Given("a booking has been created with default data")
    public void aBookingHasBeenCreatedWithDefaultData() {
        iCreateBookingWithDefaultData();
        assertThat("Precondition: booking id must exist", context.getBookingId(), notNullValue());
    }

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

    // ---------- Update (PUT) ----------

    @When("I update the booking with new data")
    public void iUpdateTheBookingWithNewData() {
        Booking updated = BookingDataFactory.updatedBooking();
        context.put("payload", updated);
        context.setResponse(
                bookingClient.updateBooking(
                        context.getBookingId(),
                        updated,
                        context.getToken()
                )
        );
    }

    @When("I update the booking with new data without a token")
    public void iUpdateTheBookingWithNewDataWithoutToken() {
        Booking updated = BookingDataFactory.updatedBooking();
        context.setResponse(
                bookingClient.updateBooking(
                        context.getBookingId(),
                        updated,
                        "invalid-token"
                )
        );
    }

    @Then("the booking should reflect the updated data")
    public void theBookingShouldReflectTheUpdatedData() {
        Booking updated = (Booking) context.get("payload");
        context.getResponse().then()
                .spec(ResponseSpecFactory.okJson())
                .body("firstname", equalTo(updated.getFirstname()))
                .body("lastname", equalTo(updated.getLastname()))
                .body("totalprice", equalTo(updated.getTotalprice()));
    }

    // ---------- Partial Update (PATCH) ----------
    @When("I partially update the booking firstname to {string}")
    public void iPartiallyUpdateTheBookingFirstname(String firstname) {
        String body = "{\"firstname\":\"" + firstname + "\"}";
        context.put("firstname", firstname);
        context.setResponse(
                bookingClient.partialUpdateBooking(
                        context.getBookingId(),
                        body,
                        context.getToken()
                )
        );
    }

    @Then("the booking firstname should be {string}")
    public void theBookingFirstnameShouldBe(String firstname) {
        context.getResponse().then()
                .spec(ResponseSpecFactory.okJson())
                .body("firstname", equalTo(firstname));
    }

    // ---------- Delete ----------

    @When("I delete the booking")
    public void iDeleteTheBooking() {
        context.setResponse(
                bookingClient.deleteBooking(
                        context.getBookingId(),
                        context.getToken()
                )
        );
    }

    @Then("the booking should be deleted successfully")
    public void theBookingShouldBeDeletedSuccessfully() {
        context.getResponse().then().spec(ResponseSpecFactory.created());
    }

    @Then("the deleted booking should no longer be retrievable")
    public void theDeletedBookingShouldNoLongerBeRetrievable() {
        context.setResponse(bookingClient.getBooking(context.getBookingId()));
        context.getResponse().then().spec(ResponseSpecFactory.notFound());
    }

    // ---------- Request Status ----------

    @Then("the booking should not be found")
    public void theBookingShouldNotBeFound() {
        context.getResponse().then().spec(ResponseSpecFactory.notFound());
    }
}
