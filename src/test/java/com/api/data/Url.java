package com.api.data;

import com.api.config.EnvManager;

public class Url {
    public static final String BASE_URL = EnvManager.getBaseUrl();;
    public static final String auth_login = "/auth/login";
    public static final String bookings_populate_start_times = "/bookings/populate-start-times";
    public static final String bookings_populate_end_times = "/bookings/populate-end-times";
    public static final String bookings_confirm_booking = "/bookings/confirm-booking";
}
