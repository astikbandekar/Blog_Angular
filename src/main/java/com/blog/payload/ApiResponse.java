package com.blog.payload;

public class ApiResponse {
    private String success = "post deleted successfully";

    public ApiResponse(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }
}
