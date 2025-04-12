package com.api.payloads;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CancelBookingPayload {
    private List<Integer> booking_ids;

    // Required no-arg constructor for Jackson
    public CancelBookingPayload() {}

    // All-arg constructor
    public CancelBookingPayload(List<Integer> booking_ids) {
        this.booking_ids = booking_ids;
    }

    public List<Integer> getBooking_ids() {
        return booking_ids;
    }

    public void setBooking_ids(List<Integer> booking_ids) {
        this.booking_ids = booking_ids;
    }

    // Static builder method from a response
    public static CancelBookingPayload fromConfirmResponse(Response response, String username, String date) {
        List<Map<String, Object>> bookings = response.jsonPath().getList("bookings");

        List<Integer> ids = bookings.stream()
                .filter(b -> username.equals(String.valueOf(b.get("username"))) &&
                        date.equals(String.valueOf(b.get("date"))))
                .map(b -> ((Number) b.get("id")).intValue())
                .collect(Collectors.toList());

        /// //
        System.out.println("Extracting bookings for user: " + username + ", date: " + date);
        System.out.println("Matched IDs: " + ids);
        /// //

        return new CancelBookingPayload(ids);
    }
}
