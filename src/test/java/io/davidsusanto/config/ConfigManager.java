package io.davidsusanto.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads configuration from config.properties
 * Any property can be overridden at runtime via a JVM system property,
 * e.g. -Dbase.uri=https://staging.example.com
 */
public class ConfigManager {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("config.properties not found on classpath");
            }
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return System.getProperty(key, PROPERTIES.getProperty(key));
    }

    public static String baseUri() {
        return get("base.uri");
    }
}
