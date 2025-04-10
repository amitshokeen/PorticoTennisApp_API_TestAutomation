package com.api.data;

import com.api.config.EnvManager;

public class Url {
    public static final String BASE_URL = EnvManager.getBaseUrl();;
    public static final String auth_login = "/auth/login";
    public static final String bookings_populate_start_times = "/bookings/populate-start-times";
}
