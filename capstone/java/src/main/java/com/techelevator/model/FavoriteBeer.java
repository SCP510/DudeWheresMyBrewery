package com.techelevator.model;

public class FavoriteBeer {

    // Instance Variables/Properties
    private int favBeerId;
    private int userId;
    private int beerId;



    // Constructors
    public FavoriteBeer() {
    }

    public FavoriteBeer(int favBeerId, int userId, int beerId) {
        this.favBeerId = favBeerId;
        this.userId = userId;
        this.beerId = beerId;
    }



    // Getters and Setters
    public int getFavBeerId() {
        return favBeerId;
    }

    public void setFavBeerId(int favBeerId) {
        this.favBeerId = favBeerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }
}
