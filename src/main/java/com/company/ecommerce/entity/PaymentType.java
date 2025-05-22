package com.company.ecommerce.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum PaymentType implements EnumClass<String> {

    CASH("cash"),
    CARD("card");

    private final String id;

    PaymentType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static PaymentType fromId(String id) {
        for (PaymentType at : PaymentType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}