package com.example.appdbservice.entity.enums;

public enum OrderStatus {

    CLOSED("", ""),
    CANCEL("", ""), //mijoz
    REJECTED("", ""), //tizim,
    RECEIVED("", ""),
    IN_PROGRESS("", ""),
    APPROVED("", ""),
    PENDING("", ""),
    DELIVERED("", "");

    private final String nameUz;
    private final String descriptionUz;

    OrderStatus(String nameUz, String descriptionUz) {
        this.nameUz = nameUz;
        this.descriptionUz = descriptionUz;
    }

    public String getNameUz() {
        return nameUz;
    }

    public String getDescriptionUz() {
        return descriptionUz;
    }

}
