package com.api.tests;

import com.api.helpers.AuthHelper;
import com.api.data.Url;
import com.api.data.Data;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import io.qameta.allure.*;


public class ApiTest {

    String jwtToken;

    @BeforeClass(description = "Login and extract JWT token from access_token cookie")
    public void setup() {
        jwtToken = AuthHelper.loginAndExtractToken("access_token");
    }

    // DataProvider for each weekday date in the upcoming week starting from today
    @DataProvider(name = "Weekday Dates")
    public Object[][] weekdayDates() {
        // This commented out code works well, but there is too much repetition here,
        // and it does not filter out weekend dates
//        return new Object[][] {
//                { LocalDate.now().toString() }, // Today
//                { LocalDate.now().plusDays(1).toString() }, // Tomorrow
//                { LocalDate.now().plusDays(2).toString() },// 2 days after today
//                { LocalDate.now().plusDays(3).toString() }, // 3 days after today
//                { LocalDate.now().plusDays(4).toString() }, // 4 days after today
//                { LocalDate.now().plusDays(5).toString() }, // 5 days after today
//                { LocalDate.now().plusDays(6).toString() } // 6 days after today
//        };
        // Smarter code is given below:
        int numberOfDays = 7;
        List<Object[]> resultList = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            DayOfWeek day = date.getDayOfWeek();
            if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) {
                resultList.add(new Object[]{ date.toString() });
            }
        }
        Object[][] x = resultList.toArray(new Object[resultList.size()][]);
        return x;
    }

    // DataProvider for weekend dates in the upcoming week starting from today
    @DataProvider(name = "Weekend Dates")
    public Object[][] weekendDates() {
        int numberOfDays = 7;
        List<Object[]> resultList = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            DayOfWeek day = date.getDayOfWeek();
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                resultList.add(new Object[]{ date.toString() });
            }
        }
        Object[][] x = resultList.toArray(new Object[resultList.size()][]);
        return x;
    }

    @Epic("Bookings API")
    @Feature("Populate Start Times")
    @Story("Available times by date")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify if '/bookings/populate-start-times' responds with available start times for weekdays")
    @Test(description="Verify if '/bookings/populate-start-times' responds with available start times",
            dataProvider="Weekday Dates")
    public void testPopulateStartTimes_forWeekdays(String date) {
        given()
                .baseUri(Url.BASE_URL)
                .cookie("access_token", jwtToken)
                .contentType("application/json")
                .body(Data.selectedDatePayload(date))
        .when()
                .post(Url.bookings_populate_start_times)
        .then()
                .statusCode(200)
                .body("available_start_times", is(not(empty())))
                .body("available_start_times.size()", greaterThan(0));
                //.body("available_start_times.size()", equalTo(64))
                //.body("available_start_times[0]", equalTo("06:00"))
                //.body("available_start_times[-1]", equalTo("21:45"))
                //.body("available_start_times", hasItems("07:30", "10:15", "17:45"));
    }

    @Epic("Bookings API")
    @Feature("Populate Start Times")
    @Story("Available times by date")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify if '/bookings/populate-start-times' responds with available start times for weekends")
    @Test(description="Verify if '/bookings/populate-start-times' responds with available start times for weekends",
            dataProvider="Weekend Dates")
    public void testPopulateStartTimes_forWeekends(String date) {
        given()
                .baseUri(Url.BASE_URL)
                .cookie("access_token", jwtToken)
                .contentType("application/json")
                .body(Data.selectedDatePayload(date))
        .when()
                .post(Url.bookings_populate_start_times)
        .then()
                .statusCode(200)
                .body("available_start_times", is(not(empty())))
                .body("available_start_times.size()", greaterThan(0))
                .body("available_start_times", not(hasItems("07:00", "07:15", "07:30", "07:45",
                                                                "08:00", "08:15", "08:30", "08:45",
                                                                "09:00", "09:15", "09:30", "09:45",
                                                                "17:00", "17:15", "17:30", "17:45",
                                                                "18:00", "18:15", "18:30", "18:45",
                                                                "19:00", "19:15", "19:30", "19:45")));
    }
}

