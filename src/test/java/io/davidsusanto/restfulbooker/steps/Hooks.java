package io.davidsusanto.restfulbooker.steps;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.davidsusanto.restfulbooker.config.ConfigManager;
import io.restassured.RestAssured;

public class Hooks {

    @BeforeAll
    public static void globalSetup() {
        RestAssured.baseURI = ConfigManager.baseUri();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
    }
}
