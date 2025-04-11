package com.api.tests;

import com.api.helpers.BookingApiHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*;

public class BookAndCancelTests extends BaseTest {

    @Feature("Populate Start Times")
    @Story("Available times by date")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run all booking-related APIs for a given date")
    @Test(dataProvider = "Weekday Dates", description = "Run all booking-related APIs for a given date")
    public void testBookingWorkflowForDate(String date) {
        BookingApiHelper.populateStartTimes(date, jwtToken);
        BookingApiHelper.populateEndTimes(date, "06:00", jwtToken);
        Response response = BookingApiHelper.confirmBooking(date, "06:00", "07:00", jwtToken);
        Assert.assertEquals(response.jsonPath().getString("message"), "Booking confirmed");
    }
}
