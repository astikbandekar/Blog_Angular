package com.blog.payload;

import java.util.Date;

public class ErrorDetails {

    private Date timeStamp;
    private String message;
    private String details;
    private boolean result = false;

    public ErrorDetails(Date timeStamp, String message, String details,boolean result) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }


}
