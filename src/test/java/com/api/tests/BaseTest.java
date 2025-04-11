package com.api.tests;

import com.api.helpers.AuthHelper;
import com.api.config.EnvManager;
import com.api.data.Url;
import com.api.utils.DateUtil;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public abstract class BaseTest {
    protected String jwtToken;

    @BeforeClass(description = "Login and extract JWT token from access_token cookie")
    public void setup() {
        String env = EnvManager.get("ENV");

        Allure.addAttachment("Environment", env);
        Allure.addAttachment("Base URL", Url.BASE_URL);

        System.out.println("Active ENV: " + env);
        System.out.println("Running tests against: " + EnvManager.getBaseUrl());

        jwtToken = AuthHelper.loginAndExtractToken("access_token");
    }

    @DataProvider(name = "Weekday Dates")
    public Object[][] weekdayDates() {
        return DateUtil.getNextWeekdays(7);
    }

    @DataProvider(name = "Weekend Dates")
    public Object[][] weekendDates() {
        return DateUtil.getNextWeekends(7);
    }
}
