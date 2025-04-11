package com.api.helpers;

import com.api.data.PayloadFactory;
import com.api.data.Url;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookingApiHelper {

    // Common request builder with token
    private static RequestSpecification authRequest(String token) {
        return given()
                .baseUri(Url.BASE_URL)
                .cookie("access_token", token)
                .contentType("application/json");
    }

    public static Response populateStartTimes(String date, String token) {
        return authRequest(token)
                .body(PayloadFactory.selectedDatePayload(date))
            .when()
                .post(Url.bookings_populate_start_times)
            .then()
                .statusCode(200)
                .body("available_start_times", is(not(empty())))
                .extract()
                .response();
    }

    public static Response populateEndTimes(String date, String startTime, String token) {
        return authRequest(token)
                .body(PayloadFactory.selectedDateTimePayload(date, startTime))
            .when()
                .post(Url.bookings_populate_end_times)
            .then()
                .statusCode(200)
                .body("available_end_times", is(not(empty())))
                .extract()
                .response();
    }

    public static Response confirmBooking(String date, String startTime, String endTime, String token) {
        String isoStart = date + "T" + startTime + ":00+10:00";
        String isoEnd = date + "T" + endTime + ":00+10:00";

        return authRequest(token)
                .body(PayloadFactory.dateTimeRangePayload(date, isoStart, isoEnd, "Confirmed"))
            .when()
                .post(Url.bookings_confirm_booking)
            .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
