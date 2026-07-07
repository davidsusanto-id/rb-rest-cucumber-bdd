package io.davidsusanto.restfulbooker.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.davidsusanto.restfulbooker.client.HealthClient;
import io.davidsusanto.restfulbooker.context.ScenarioContext;
import io.davidsusanto.restfulbooker.specs.ResponseSpecFactory;

public class PingSteps {

    private final ScenarioContext context;
    private final HealthClient healthClient = new HealthClient();

    public PingSteps(ScenarioContext context) {
        this.context = context;
    }

    @When("I check the service health")
    public void iCheckTheServiceHealth() {
        context.setResponse(healthClient.ping());
    }

    @Then("the service should respond as healthy")
    public void theServiceShouldResponseAsHealthy() {
        context.getResponse().then().spec(ResponseSpecFactory.created());
    }
}
