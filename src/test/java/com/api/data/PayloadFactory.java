package com.api.data;
import java.util.HashMap;
import java.util.Map;

public class Data {
    public static Map<String, String> selectedDatePayload(String date) {
        Map<String, String> body = new HashMap<>();
        body.put("date", date);
        return body;
    }
}






