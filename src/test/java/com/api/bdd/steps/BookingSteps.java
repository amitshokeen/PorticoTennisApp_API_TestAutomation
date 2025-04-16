package com.api.bdd.steps;

import com.api.helpers.AuthHelper;
import com.api.helpers.BookingApiHelper;
import com.api.helpers.CancelBookingApiHelper;
import com.api.payloads.CancelBookingPayload;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

public class BookingSteps {

    private String token;
    private Response confirmResponse;
    private Response cancelResponse;
    private String date;
    private String username = "d203";

    @Given("I am logged in")
    public void iAmLoggedIn() {
        this.token = AuthHelper.loginAndExtractToken("access_token");
    }

    @When("I book a slot today from {string} to {string}")
    public void i_book_a_slot_today_from_to(String startTime, String endTime) {
        this.date = String.valueOf(LocalDate.now());
        confirmResponse = BookingApiHelper.confirmBooking(date, startTime, endTime, token);
    }

    @Then("the booking should be confirmed")
    public void the_booking_should_be_confirmed() {
        assertEquals(confirmResponse.getStatusCode(), 200);
        assertEquals(confirmResponse.jsonPath().getString("message"), "Booking confirmed");
    }

    @When("I cancel the booking on {string}")
    public void i_cancel_the_booking_on(String date) {
        date = "today".equals(date) ? String.valueOf(LocalDate.now()) : date;
        CancelBookingPayload payload =
                CancelBookingPayload.fromConfirmResponse(confirmResponse, username, date);
        cancelResponse = CancelBookingApiHelper.cancelBookings(payload, token);
    }

    @Then("the booking should be cancelled")
    public void the_booking_should_be_cancelled() {
        //response is {"message":"Bookings canceled successfully"}
        assertEquals(cancelResponse.jsonPath()
                .getString("message"), "Bookings canceled successfully");
    }
}