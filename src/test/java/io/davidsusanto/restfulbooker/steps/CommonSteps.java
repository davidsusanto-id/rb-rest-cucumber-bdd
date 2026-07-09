package io.davidsusanto.restfulbooker.steps;

import io.cucumber.java.en.Then;
import io.davidsusanto.restfulbooker.context.ScenarioContext;
import io.davidsusanto.restfulbooker.specs.ResponseSpecFactory;

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

    @Then("the request should be forbidden")
    public void theRequestShouldBeForbidden() {
        context.getResponse().then().spec(ResponseSpecFactory.forbidden());
    }
}
