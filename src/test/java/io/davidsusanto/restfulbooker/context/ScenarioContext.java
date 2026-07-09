package io.davidsusanto.restfulbooker.context;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Per-scenario shared state. Injected into step classes by cucumber-picocontainer,
 * giving each scenario a fresh, isolated instance.
 */
public class ScenarioContext {

    private Response response;
    private String token;
    private Integer bookingId;
    private final Map<String, Object> store = new HashMap<>();

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Object get(String key) {
        return store.get(key);
    }

    public void put(String key, Object value) {
        store.put(key, value);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
}
