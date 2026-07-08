Feature: GetBookingIds and GetBooking
  As an API consumer
  I want to retrieve booking ids and individual bookings
  So that I can read reservation data

  Scenario: Retrieve all booking ids
    When I request all booking ids
    Then a non-empty list of booking ids should be returned

  Scenario: Retrieve a specific booking by id
    Given a booking has been created with default data
    When I request the created booking by id
    Then the booking details should be returned

  Scenario: Requesting a non-existing booking returns 404
    When I request a booking with id 99999999
    Then the response status code should be 404