package com.application.java.domains;

import lombok.Getter;

import java.io.Serializable;

@Getter
public final class Manufacturer implements Serializable {

    private String id;
    private String name;
    private String address;
    private String phone;

    public Manufacturer() {
    }

    private Manufacturer(final String id, final String name, final String address, final String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public static Manufacturer of(final String id, final String name, final String address, final String phone) {
        return new Manufacturer(id, name, address, phone);
    }
}
