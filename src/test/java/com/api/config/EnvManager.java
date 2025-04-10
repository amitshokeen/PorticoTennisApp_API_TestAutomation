package com.api.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvManager {
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    private static final String ENV = System.getenv("ENV") != null
            ? System.getenv("ENV")
            : (dotenv.get("ENV") != null ? dotenv.get("ENV") : "dev");

    public static String getBaseUrl() {
        switch (ENV.toLowerCase()) {
            case "prod":
                return get("BASE_URL_PROD");
            case "dev":
            default:
                return get("BASE_URL_DEV");
        }
    }

    public static String get(String key) {
        String value = System.getenv(key);
        if (value != null) return value;

        value = dotenv.get(key);
        if (value != null) return value;

        throw new RuntimeException("Missing required env var: " + key);
    }
}