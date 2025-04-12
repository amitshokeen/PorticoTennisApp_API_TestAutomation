package com.api.helpers;

import com.api.data.Url;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiRequestBuilder {

    public static RequestSpecification authRequest(String token) {
        return given()
                .baseUri(Url.BASE_URL)
                .cookie("access_token", token)
                .contentType("application/json");
    }
}
