package com.woolam.commerce.dto;

public class ServiceResponse {
    private final String nextService;
    private final Object data;

    public ServiceResponse(String nextService, Object data) {
        this.nextService = nextService;
        this.data = data;
    }

    public String getNextService() {
        return nextService;
    }

    public Object getData() {
        return data;
    }
}
