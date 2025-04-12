package com.api.payloads;

/*This will be auto-serialized to JSON by Rest Assured
 as long as contentType("application/json") is set.*/
public class DatePayload {
    private String date;

    public DatePayload() {}

    public DatePayload(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
