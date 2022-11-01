package com.example.appdbservice.entity.enums;

public enum LanguageEnum {

    UZ(""),
    RU("");
    private final String nameUz;

    LanguageEnum(String nameUz) {
        this.nameUz = nameUz;
    }

    public String getNameUz() {
        return nameUz;
    }

}
