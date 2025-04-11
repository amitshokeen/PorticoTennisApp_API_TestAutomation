package com.api.helpers;

import com.api.data.PayloadFactory;
import com.api.data.Url;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.time.*;

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
        String isoStart = toIsoWithSydneyOffset(date, startTime);
        String isoEnd = toIsoWithSydneyOffset(date, endTime);

        return authRequest(token)
                .body(PayloadFactory.dateTimeRangePayload(date, isoStart, isoEnd, "Confirmed"))
            .when()
                .post(Url.bookings_confirm_booking)
            .then()
                .statusCode(200)
                .extract()
                .response();
    }

    private static String toIsoWithSydneyOffset(String date, String time) {
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZonedDateTime sydneyTime = localDateTime.atZone(ZoneId.of("Australia/Sydney"));
        return sydneyTime.toOffsetDateTime().toString(); // Outputs ISO 8601 with correct offset
    }
}
