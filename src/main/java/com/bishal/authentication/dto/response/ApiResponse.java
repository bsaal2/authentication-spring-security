package com.bishal.authentication.dto.response;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    private int statusCode;
    private boolean isError;
    private String message;
    private Object data;

    // empty constructor
    public ApiResponse() {}

    // all arg constructor
    public ApiResponse(int statusCode, boolean isError, String message, Object data) {
        this.statusCode = statusCode;
        this.isError = isError;
        this.message = message;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ApiResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public boolean isError() {
        return isError;
    }

    public ApiResponse setError(boolean isError) {
        this.isError = isError;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ApiResponse setData(Object data) {
        this.data = data;
        return this;
    }
}
