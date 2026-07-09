@booking @delete
Feature: Delete a booking
  As an authenticated API consumer
  I want to delete a booking
  So that obsolete reservations are removed

  Background:
    Given a valid authentication token has been generated
    And a booking has been created with default data

  Scenario: Delete a booking with a valid token
    When I delete the booking
    Then the booking should be deleted successfully
    And the deleted booking should no longer be retrievable