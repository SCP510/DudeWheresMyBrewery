package com.techelevator.dao;

import com.techelevator.model.BrewSearchDTO;
import com.techelevator.model.Brewery;

import java.security.Principal;
import java.util.List;

public interface BreweryDao {
    List<Brewery> getAllBreweries();
    Brewery getRandomBrewery();
    Brewery getBreweryById (int breweryId);
    List<Brewery> getSavedBreweries(Principal principal);
    Brewery addBreweryToSaved(int breweryId, Principal principal);
    Brewery createBrewery(Brewery newBrewery, Principal principal);
    Brewery updateBreweryInfo(Brewery updatedBrewery, Principal principal);
    void deleteSavedBrewery(int breweryId, Principal principal);
    void deleteBrewery(int breweryId, Principal principal);

//    List<Brewery> searchBreweries(BrewSearchDTO searchTerms);
}
