package com.example.appdbservice.entity.enums;

public enum RegionEnum {

    TOSHKENT_SH("", ""),
    ANDIJON("", ""),
    BUXORO("", ""),
    FARGONA("", ""),
    JIZZAX("", ""),
    XORAZM("", ""),
    NAMANGAN("", ""),
    NAVOIY("", ""),
    QASHQADARYO("", ""),
    QORAQALPOGISTON_R("", ""),
    SAMARQAND("", ""),
    SIRDARYO("", ""),
    SURXONDARYO("", ""),
    TOSHKENT("", "");

    private final String nameUz;
    private final String nameRu;

    RegionEnum(String nameUz, String nameRu) {
        this.nameUz = nameUz;
        this.nameRu = nameRu;
    }

    public String getNameUz() {
        return nameUz;
    }

    public String getNameRu() {
        return nameRu;
    }

}
