@ping @health @smoke
Feature: Ping - HealthCheck
  As an API consumer
  I want to verify the service is up
  So that I can confirm availability before running tests

  Scenario: Service health check returns Created
    When I check the service health
    Then the service should respond as healthy