package com.techelevator.model;

import java.util.Objects;

public class Beer {

    // Instance Variables/Properties
    private int beerId;
    private String beerName;
    private int breweryId;
    private String beerType;
    private double abv;
    private String labelImage;
    private String description;



    // Constructors
    public Beer() {
    }

    public Beer(int beerId, String beerName, int breweryId, String beerType, double abv, String labelImage, String description) {
        this.beerId = beerId;
        this.beerName = beerName;
        this.breweryId = breweryId;
        this.beerType = beerType;
        this.abv = abv;
        this.labelImage = labelImage;
        this.description = description;
    }



    // Getters and Setters
    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public String getBeerType() {
        return beerType;
    }

    public void setBeerType(String beerType) {
        this.beerType = beerType;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public String getLabelImage() {
        return labelImage;
    }

    public void setLabelImage(String labelImage) {
        this.labelImage = labelImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    // Methods
    @Override
    public String toString() {
        return "Beer{" +
                "beerId=" + beerId +
                ", beerName='" + beerName +
                ", breweryId=" + breweryId +
                ", beerType='" + beerType +
                ", abv=" + abv +
                ", labelImage='" + labelImage +
                ", description='" + description +
                '}';
    }
}
