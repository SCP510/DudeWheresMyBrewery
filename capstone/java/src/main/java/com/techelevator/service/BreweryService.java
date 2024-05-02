package com.techelevator.service;

import com.techelevator.model.BrewSearchDTO;
import com.techelevator.model.Brewery;

import java.security.Principal;
import java.util.List;

public interface BreweryService {
    Brewery getBreweryById(int breweryId);
    Brewery getRandomBrewery();
    List<Brewery> getAllBreweries();
    List<Brewery> getSavedBreweries(Principal principal);
    Brewery addBreweryToSaved(int breweryId, Principal principal);
    Brewery createBrewery(Brewery newBrewery, Principal principal);
    Brewery updateBrewery(Brewery updatedBrewery, Principal principal);
    void deleteSavedBrewery(int breweryId, Principal principal);
    void deleteBrewery(int breweryId, Principal principal);

//    List<Brewery> searchBreweries(BrewSearchDTO searchTerms);
}
