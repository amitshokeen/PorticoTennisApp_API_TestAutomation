package com.api.tests;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class LoginAPITest_simplified {
    public static void main(String[] args){
        Response response = given()
                .baseUri("http://127.0.0.1:8000")
                .contentType("application/x-www-form-urlencoded")
                .body("username=d203&password=anoPwd%4079999&remember_me=false")
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Get the JWT token cookie
        String jwtToken = response.getCookie("access_token");
        System.out.println("jwtToken: " + jwtToken);
    }
}
