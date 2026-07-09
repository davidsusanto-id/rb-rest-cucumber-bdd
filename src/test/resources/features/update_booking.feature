@booking @update
Feature: Update an existing booking
  As an authenticated API consumer
  I want to fully update a booking
  So that the reservation reflects new details

  Background:
    Given a valid authentication token has been generated
    And a booking has been created with default data

  Scenario: Update a booking with a valid token
    When I update the booking with new data
    Then the booking should reflect the updated data

  Scenario: Reject update when token is invalid
    When I update the booking with new data without a token
    Then the request should be forbidden