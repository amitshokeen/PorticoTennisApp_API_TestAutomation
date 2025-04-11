package com.api.payload;


/*This will be auto-serialized to JSON by Rest Assured
 as long as contentType("application/json") is set.*/
public class DateTimePayload {
    private String date;
    private String start_time;

    public DateTimePayload() {}

    public DateTimePayload(String date, String start_time) {
        this.date = date;
        this.start_time = start_time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
}
