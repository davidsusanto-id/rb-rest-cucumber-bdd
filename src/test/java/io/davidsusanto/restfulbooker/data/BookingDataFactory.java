package io.davidsusanto.restfulbooker.data;

import io.davidsusanto.restfulbooker.models.Booking;
import io.davidsusanto.restfulbooker.models.BookingDates;

/**
 * Produces deteministic Booking payloads for tests.
 * <p>
 * Grouped by the Booking entity: as negative/boundary cases are added
 * (missing required fields, invalid dates, boundary prices), they belong here.
 */
public class BookingDataFactory {

    private BookingDataFactory() {

    }

    /**
     * A complete, valid booking used as the standard create fixture.
     */
    public static Booking defaultBooking() {
        return new Booking(
                "James",
                "Brown",
                150,
                true,
                new BookingDates("2025-01-10", "2025-01-15"),
                "breakfast"
        );
    }

    /**
     * A distinct, valid booking used as the update fixture
     */
    public static Booking updatedBooking() {
        return new Booking(
                "Mary",
                "Jane",
                275,
                false,
                new BookingDates("2025-03-01", "2025-03-08"),
                "Late checkout"
        );
    }
}
