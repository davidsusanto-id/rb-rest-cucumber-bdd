package io.davidsusanto.restfulbooker.data;

import io.davidsusanto.restfulbooker.config.ConfigManager;
import io.davidsusanto.restfulbooker.models.AuthRequest;

/**
 * Produces AuthRequest payloads for tests.
 * <p>
 * Valid credentials are sourced from configuration so environment overrides
 * (-Dauth.username / -Dauth.password) flow through automatically.
 */
public class AuthDataFactory {

    private AuthDataFactory() {

    }

    /**
     * Valid admin credentials from configuration.
     */
    public static AuthRequest valid() {
        return new AuthRequest(ConfigManager.username(), ConfigManager.password());
    }

    /**
     * Arbitrary explicit credentials.
     */
    public static AuthRequest of(String username, String password) {
        return new AuthRequest(username, password);
    }
}
