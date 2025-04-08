package com.api.data;

import io.github.cdimascio.dotenv.Dotenv;

public class Credentials {
    public static final String USERNAME = getEnvVar("LOGIN_USERNAME");
    public static final String PASSWORD = getEnvVar("LOGIN_PASSWORD");
    public static final boolean REMEMBER_ME = false;

    private static String getEnvVar(String key) {
        String fromSystem = System.getenv(key);
        if (fromSystem != null) return fromSystem;

        // fallback to .env file
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String fromDotenv = dotenv.get(key);
        if (fromDotenv != null) return fromDotenv;

        throw new RuntimeException("Missing required environment variable: " + key);
    }
}


