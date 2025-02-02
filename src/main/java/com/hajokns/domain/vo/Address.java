package com.hajokns.domain.vo;

import java.util.Objects;

public final class Address {
    private final String country;
    private final String city;
    private final String street;
    private final String postalCode;

    public Address(String country, String city, String street, String postalCode) {
        if (country.isBlank() || city.isBlank() || street.isBlank() || postalCode.isBlank()) {
            throw new IllegalArgumentException("All fields must be non-empty");
        }
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + country + " (" + postalCode + ")";
    }
}
