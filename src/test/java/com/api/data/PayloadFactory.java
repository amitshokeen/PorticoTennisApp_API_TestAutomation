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

import com.api.payload.DatePayload;
import com.api.payload.DateTimePayload;

public class PayloadFactory {
    public static DatePayload selectedDatePayload(String date) {
        return new DatePayload(date);
    }

    public static DateTimePayload selectedDateTimePayload(String date, String startTime) {
        return new DateTimePayload(date, startTime);
    }
}





