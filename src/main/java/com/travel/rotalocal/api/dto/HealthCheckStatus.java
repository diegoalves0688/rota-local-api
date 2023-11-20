package com.travel.rotalocal.api.dto;

public enum HealthCheckStatus {
    
    OK("ok"),
    ERROR("error");

    private String description;

    HealthCheckStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
