@booking @create @smoke
Feature: CreateBooking
  As an API consumer
  I want to create a booking
  So that a new reservation is stored with a unique id

  Scenario: Create a booking with valid data
    When I create a booking with default data
    Then the booking should be created successfully
    And the created booking should match the submitted data