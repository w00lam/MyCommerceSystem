package com.woolam.commerce.dto;

public class ServiceFlag {
    private final String nextService;
    private final ServiceData data;

    public ServiceFlag(String nextService, ServiceData data) {
        this.nextService = nextService;
        this.data = data;
    }

    // Getter
    public String getNextService() {
        return nextService;
    }
    public ServiceData getData() {
        return data;
    }
}
