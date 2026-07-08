package io.davidsusanto.restfulbooker.steps;

import io.cucumber.java.en.Then;
import io.davidsusanto.restfulbooker.context.ScenarioContext;

/**
 * Entity-agnostic step definitions usable across all features.
 * These operate purely on the shared ScenarioContext response and hold
 * no domain (booking/auth/health) knowledge.
 */
public class CommonSteps {

    private final ScenarioContext context;

    public CommonSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        context.getResponse().then().statusCode(statusCode);
    }
}
