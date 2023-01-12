package com.app.dto;

import com.app.entity.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.OneToOne;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {

    private Long id;
    private String street;
    private String state;
    private String location;
    private String number;


    public AddressDto() {
    }

    public Long getId() {
        return id;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
