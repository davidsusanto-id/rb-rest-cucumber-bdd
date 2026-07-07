package io.davidsusanto.restfulbooker.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.davidsusanto.restfulbooker.client.AuthClient;
import io.davidsusanto.restfulbooker.config.ConfigManager;
import io.davidsusanto.restfulbooker.context.ScenarioContext;
import io.davidsusanto.restfulbooker.models.AuthRequest;
import io.davidsusanto.restfulbooker.specs.ResponseSpecFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AuthSteps {

    private final ScenarioContext context;
    private final AuthClient authClient = new AuthClient();

    public AuthSteps(ScenarioContext context) {
        this.context = context;
    }

    @When("I request a token with valid credentials")
    public void iRequestTokenWithValidCredentials() {
        context.setResponse(authClient.createToken(ConfigManager.username(), ConfigManager.password()));
    }

    @When("I request a token with username {string} and password {string}")
    public void iRequestTokenWithUsernameAndPassword(String username, String password) {
        context.setResponse(authClient.createToken(new AuthRequest(username, password)));
    }

    @Then("a valid token should be returned")
    public void aValidTokenShouldBeReturned() {
        context.getResponse().then().spec(ResponseSpecFactory.okJson());
        String token = context.getResponse().jsonPath().getString("token");
        assertThat(token, notNullValue());
        assertThat(token.length(), greaterThan(0));
    }

    @Then("no token should be returned and a reason should be present")
    public void noTokenShouldBeReturnedAndReasonShouldBePresent() {
        // The API returns 200 with a "reason" field for bad credentials.
        String token = context.getResponse().jsonPath().getString("token");
        String reason = context.getResponse().jsonPath().getString("reason");
        assertThat(token, nullValue());
        assertThat(reason, notNullValue());
    }
}
