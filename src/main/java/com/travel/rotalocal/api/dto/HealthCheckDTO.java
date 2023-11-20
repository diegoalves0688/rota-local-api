package com.travel.rotalocal.api.dto;

public class HealthCheckDTO {
    private HealthCheckStatus status;

    public HealthCheckStatus getStatus() {
        return status;
    }

    public void setStatus(HealthCheckStatus status) {
        this.status = status;
    }
}
