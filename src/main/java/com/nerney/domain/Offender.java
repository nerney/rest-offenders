package com.nerney.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nerney on 7/14/2017.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Offender implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String link;
    private String name;
    private String level;
    private String dob;
    private String sex;
    private String race;
    private String height;
    private String weight;
    private String eyes;
    private String hair;
    @Lob
    private String convictedOf;
    private String supervision;
    @Embedded
    private Address address;

    public Offender(String link) {
        this.link = link;
    }
}
