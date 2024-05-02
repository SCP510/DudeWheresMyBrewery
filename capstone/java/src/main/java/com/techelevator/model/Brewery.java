package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Brewery {

    // Instance Variables/Properties
    private int breweryId;
    private String breweryName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private LocalDateTime dateEst;
    private String phoneNumber;
    private String aboutUs;
    private String website;
    private String logoImage;
    private int founderId;



    // Constructors
    public Brewery() {
    }

    public Brewery(int breweryId, String breweryName, String streetAddress, String city, String state,
                   String zipcode, LocalDateTime dateEst, String phoneNumber, String aboutUs,
                   String website, String logoImage, int founderId) {
        this.breweryId = breweryId;
        this.breweryName = breweryName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.dateEst = dateEst;
        this.phoneNumber = phoneNumber;
        this.aboutUs = aboutUs;
        this.website = website;
        this.logoImage = logoImage;
        this.founderId = founderId;
    }



    // Getter and Setters
    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public LocalDateTime getDateEst() {
        return dateEst;
    }

    public void setDateEst(LocalDateTime dateEst) {
        this.dateEst = dateEst;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public int getFounderId() {
        return founderId;
    }

    public void setFounderId(int founderId) {
        this.founderId = founderId;
    }



    // Methods
    @Override
    public String toString() {
        return "Brewery{" +
                "breweryId=" + breweryId +
                ", breweryName=" + breweryName +
                ", streetAddress='" + streetAddress +
                ", city='" + city +
                ", state='" + state +
                ", zipcode=" + zipcode +
                ", dateEst=" + dateEst +
                ", phoneNumber=" + phoneNumber +
                ", aboutUs='" + aboutUs +
                ", website='" + website +
                ", logoImage='" + logoImage +
                ", founderId=" + founderId +
                '}';
    }
}
