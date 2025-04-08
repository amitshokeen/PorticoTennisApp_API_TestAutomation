package com.api.tests;

import com.api.helpers.AuthHelper;
import com.api.data.Url;
import com.api.data.Data;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class ApiTest {

    String jwtToken;

    @BeforeClass(description = "Login and extract JWT token from access_token cookie")
    public void setup() {
        jwtToken = AuthHelper.loginAndExtractToken("access_token");
    }

    // Test for each day in the upcoming week starting from today
    @DataProvider(name = "dateData")
    public Object[][] dateData() {
        return new Object[][] {
                { LocalDate.now().toString() }, // Today
                { LocalDate.now().plusDays(1).toString() }, // Tomorrow
                { LocalDate.now().plusDays(2).toString() },// 2 days after today
                { LocalDate.now().plusDays(3).toString() }, // 3 days after today
                { LocalDate.now().plusDays(4).toString() }, // 4 days after today
                { LocalDate.now().plusDays(5).toString() }, // 5 days after today
                { LocalDate.now().plusDays(6).toString() } // 6 days after today

        };
    }

    @Test(description = "Verify if '/bookings/populate-start-times' responds with available start times",
            dataProvider = "dateData")
    public void testPopulateStartTimes(String date) {
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
                .body("available_start_times.size()", equalTo(64))
                .body("available_start_times[0]", equalTo("06:00"))
                .body("available_start_times[-1]", equalTo("21:45"))
                .body("available_start_times", hasItems("07:30", "10:15", "17:45"));
    }
}
