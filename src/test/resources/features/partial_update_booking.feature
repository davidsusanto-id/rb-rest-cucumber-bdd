@booking @partial-update
Feature: Partially update a booking
  As an authenticated API consumer
  I want to partially update a booking
  So that I can change individual fields without sending the full payload

  Background:
    Given a valid authentication token has been generated
    And a booking has been created with default data

  Scenario: Partially update the firstname
    When I partially update the booking firstname to "Alexander"
    Then the booking firstname should be "Alexander"