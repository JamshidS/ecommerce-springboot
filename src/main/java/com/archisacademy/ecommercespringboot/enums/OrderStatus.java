package com.archisacademy.ecommercespringboot.enums;

public enum OrderStatus {
    DELIVERED("Delivered"),
    RETURNED("Returned"),
    PREPARING("Preparing"),
    ON_THE_WAY("On the way"),
    DELAYED("Delayed"),
    CANCELED("Canceled");

    private final String label;

    OrderStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
