package com.api.tests;

import com.api.helpers.BookingApiHelper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.*;


public class APITests extends BaseTest {
    @Epic("Bookings API")
    @Feature("Populate Start Times")
    @Story("Available times by date")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify if '/bookings/populate-start-times' responds with available start times for weekdays")
    @Test(description="Verify if '/bookings/populate-start-times' responds with available start times",
            dataProvider="Weekday Dates")
    public void testPopulateStartTimes_forWeekdays(String date) {
        Response response = BookingApiHelper.populateStartTimes(date, jwtToken);
        assertThat(response.jsonPath().getList("available_start_times").size(), greaterThan(0));
        assertThat(response.jsonPath().getList("available_start_times").size(), equalTo(64));
        assertThat(response.jsonPath().getList("available_start_times").get(0), equalTo("06:00"));
        assertThat(response.jsonPath().getList("available_start_times").getLast(), equalTo("21:45"));
        assertThat(response.jsonPath().getList("available_start_times"), hasItems("07:30", "10:15", "17:45"));
    }

    @Epic("Bookings API")
    @Feature("Populate Start Times")
    @Story("Available times by date")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify if '/bookings/populate-start-times' responds with available start times for weekends")
    @Test(description="Verify if '/bookings/populate-start-times' responds with available start times for weekends",
            dataProvider="Weekend Dates")
    public void testPopulateStartTimes_forWeekends(String date) {
        Response response = BookingApiHelper.populateStartTimes(date, jwtToken);
        assertThat(response.jsonPath().getList("available_start_times"), is(not(empty())));
        assertThat(response.jsonPath().getList("available_start_times").size(), greaterThan(0));
        assertThat(response.jsonPath().getList("available_start_times"), not(hasItems(
                                                                                "07:00", "07:15", "07:30", "07:45",
                                                                                "08:00", "08:15", "08:30", "08:45",
                                                                                "09:00", "09:15", "09:30", "09:45",
                                                                                "17:00", "17:15", "17:30", "17:45",
                                                                                "18:00", "18:15", "18:30", "18:45",
                                                                                "19:00", "19:15", "19:30", "19:45")));
    }

    @Epic("Bookings API")
    @Feature("Populate End Times")
    @Story("Available times by date")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify if '/bookings/populate-end-times' responds with available end times for weekdays")
    @Test(description="Verify if '/bookings/populate-end-times' responds with available start times for weekdays",
            dataProvider="Weekday Dates")
    public void testPopulateEndTimes_forWeekdays(String date) {
        Response response = BookingApiHelper.populateEndTimes(date, "06:00", jwtToken);
        assertThat(response.jsonPath().getList("available_end_times"), is(not(empty())));
        assertThat(response.jsonPath().getList("available_end_times").size(), greaterThan(0));
        assertThat(response.jsonPath().getList("available_end_times"), not(hasItems("06:00")));
        assertThat(response.jsonPath().getList("available_end_times"), hasItems("06:15"));
        assertThat(response.jsonPath().getList("available_end_times"), not(hasItems("22:15")));
        assertThat(response.jsonPath().getList("available_end_times"), hasItems("22:00"));
    }

    @Epic("Bookings API")
    @Feature("Populate End Times")
    @Story("Available times by date")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify if '/bookings/populate-end-times' responds with available end times for weekends")
    @Test(description="Verify if '/bookings/populate-end-times' responds with available start times for weekends",
            dataProvider="Weekend Dates")
    public void testPopulateEndTimes_forWeekends(String date) {
       Response response = BookingApiHelper.populateEndTimes(date, "06:00", jwtToken);
       assertThat(response.jsonPath().getList("available_end_times"), contains("06:15","06:30","06:45","07:00"));
    }

}

