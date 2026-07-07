package io.davidsusanto.restfulbooker.context;

import io.restassured.response.Response;

/**
 * Per-scenario shared state. Injected into step classes by cucumber-picocontainer,
 * giving each scenario a fresh, isolated instance.
 */
public class ScenarioContext {

    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
