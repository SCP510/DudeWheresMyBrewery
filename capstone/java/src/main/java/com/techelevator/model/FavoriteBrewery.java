package com.techelevator.model;

public class FavoriteBrewery {

    // Instance Variables/Properties
    private int favBreweryId;
    private int breweryId;
    private int userId;



    // Constructors
    public FavoriteBrewery() {
    }

    public FavoriteBrewery(int favBreweryId, int breweryId, int userId) {
        this.favBreweryId = favBreweryId;
        this.breweryId = breweryId;
        this.userId = userId;
    }



    // Getters and Setters
    public int getFavBreweryId() {
        return favBreweryId;
    }

    public void setFavBreweryId(int favBreweryId) {
        this.favBreweryId = favBreweryId;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
