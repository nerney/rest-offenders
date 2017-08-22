package com.nerney.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * Created by nerney on 7/14/2017.
 */
@Embeddable
@NoArgsConstructor
public class Address {
    private String street;
    private String city;

    @Override
    public String toString() {
        return street + " " + city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
