package com.techelevator.service;

import com.techelevator.model.Beer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface BeerService {
    List<Beer> getBeers();
    Beer getRandomBeer();
    Beer getBeerById(int beerId);
    List<Beer> getBeersByBreweryId(int breweryId);
    List<Beer> getSavedBeers(Principal principal);
    Beer addBeerToSaved(int beerId, Principal principal);
    Beer createBeer(Beer beer, Principal principal);
    Beer updateBeer(Beer beer, Principal principal);
    void deleteSavedBeer(int beerId, Principal principal);
    void deleteBeer(int beerId, Principal principal);

}
