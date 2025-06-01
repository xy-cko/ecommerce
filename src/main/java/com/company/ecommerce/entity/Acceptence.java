package com.company.ecommerce.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum Acceptence implements EnumClass<String> {

    ACCEPTED("accepted"),
    REJECTED("rejected"),
    WAITING_FOR_APPROVAL("waiting");

    private final String id;

    Acceptence(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Acceptence fromId(String id) {
        for (Acceptence at : Acceptence.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}