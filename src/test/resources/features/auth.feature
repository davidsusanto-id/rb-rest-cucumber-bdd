Feature: Generate an authentication token
  As an API consumer
  I want to obtain an auth token
  So that I can perform protected booking operations

  Scenario: Generate a token with valid credentials
    When I request a token with valid credentials
    Then a valid token should be returned

  Scenario: Reject token generation with invalid credentials
    When I request a token with username "wrong" and password "wrong"
    Then no token should be returned and a reason should be present