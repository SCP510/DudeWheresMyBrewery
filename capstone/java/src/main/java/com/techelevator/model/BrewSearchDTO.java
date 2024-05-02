package com.techelevator.model;

public class BrewSearchDTO {
    private String city = null;
    private String state = null;
    private String zipcode = null;


    public BrewSearchDTO(String city, String state, String zipcode) {
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }


    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }
}
