package com.api.data;
//import java.util.HashMap;
//import java.util.Map;
//
//public class PayloadFactory {
//    public static Map<String, String> selectedDatePayload(String date) {
//        Map<String, String> body = new HashMap<>();
//        body.put("date", date);
//        return body;
//    }
//
//    public static Map<String, String> selectedDateTimePayload(String date, String startTime) {
//        Map<String, String> body = new HashMap<>();
//        body.put("date", date);
//        body.put("start_time", startTime);
//        return body;
//    }
//}

import com.api.payloads.DatePayload;
import com.api.payloads.DateTimePayload;

import java.util.HashMap;
import java.util.Map;

public class PayloadFactory {
    public static DatePayload selectedDatePayload(String date) {
        return new DatePayload(date);
    }

    public static DateTimePayload selectedDateTimePayload(String date, String startTime) {
        return new DateTimePayload(date, startTime);
    }

    public static Map<String, String> dateTimeRangePayload(String date, String startTime, String endTime, String status) {
        Map<String, String> body = new HashMap<>();
        body.put("date", date);
        body.put("start_time", startTime);
        body.put("end_time", endTime);
        body.put("status", status);
        return body;
    }

}





