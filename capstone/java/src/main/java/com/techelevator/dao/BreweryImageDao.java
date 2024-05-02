package com.techelevator.dao;

import com.techelevator.model.Image;

public interface BreweryImageDao {
    Image getById(int id);
    Image save(Image image, int id);
    boolean imageExists(int id);
}
