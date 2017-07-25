package com.nerney.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * Created by nerney on 7/14/2017.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {
    private String street;
    private String city;

    @Override
    public String toString() {
        return street + " " + city;
    }
}
