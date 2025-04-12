package com.api.tests;

import com.api.helpers.BookingApiHelper;
import com.api.helpers.CancelBookingApiHelper;
import com.api.payloads.CancelBookingPayload;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*;

public class BookAndCancelTests extends BaseTest {

    @Feature("Populate Start Times")
    @Story("Available times by date")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run all booking-related APIs for a given date and cancel the bookings")
    @Test(dataProvider = "Weekday Dates",
            description = "Run all booking-related APIs for a given date and cancel the bookings")
    public void testBookingWorkflowForDate(String date) {
        BookingApiHelper.populateStartTimes(date, jwtToken);
        BookingApiHelper.populateEndTimes(date, "06:00", jwtToken);
        Response response = BookingApiHelper.confirmBooking(date, "06:00", "07:00", jwtToken);
        /// //
        System.out.println("**** BOOKING RESPONSE >>> " + response.asPrettyString());
        /// //
        Assert.assertEquals(response.jsonPath().getString("message"), "Booking confirmed");

        CancelBookingPayload payload = CancelBookingPayload.fromConfirmResponse(response, "d203", date);
        CancelBookingApiHelper.cancelBookings(payload, jwtToken);
    }
}
