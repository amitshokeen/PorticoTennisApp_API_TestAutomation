package com.api.helpers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import com.api.data.Credentials;
import com.api.data.Url;

public class AuthHelper {

    public static String loginAndExtractToken(String cookieName) {
        return given()
                .baseUri(Url.BASE_URL)
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", Credentials.USERNAME)
                .formParam("password", Credentials.PASSWORD)
                .formParam("remember_me", Credentials.REMEMBER_ME)
            .when()
                .post(Url.auth_login)
            .then()
                .statusCode(anyOf(is(200), is(301)))
                .extract()
                .cookie(cookieName);
    }
}
