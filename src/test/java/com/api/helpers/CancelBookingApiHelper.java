package com.api.helpers;

import com.api.data.Url;
import com.api.payloads.CancelBookingPayload;
import io.restassured.response.Response;
import java.util.List;

public class CancelBookingApiHelper {

    public static Response cancelBookings(CancelBookingPayload payload, String token) {
        return ApiRequestBuilder.authRequest(token)
                .body(payload)
            .when()
                .delete(Url.bookings_cancel_multiple)
            .then()
                .statusCode(200)
                .extract()
                .response();
    }
}

