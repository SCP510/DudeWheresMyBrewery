package com.techelevator.controller;

import com.techelevator.model.BrewSearchDTO;
import com.techelevator.model.Brewery;
import com.techelevator.service.BreweryService;
import com.techelevator.service.BreweryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class BreweryController {
    private BreweryService breweryService;

    public BreweryController(BreweryServiceImpl breweryService) {
        this.breweryService = breweryService;
    }

    @GetMapping("/breweries")
    public List<Brewery> viewBreweries() {
        List<Brewery> allBreweries = breweryService.getAllBreweries();
        return allBreweries;
    }
    @GetMapping("/breweries/random")
    public Brewery getRandomBrewery() {
        Brewery brewery = breweryService.getRandomBrewery();
        return brewery;
    }

    @GetMapping("/breweries/{id}")
    public Brewery viewBreweryByID(@PathVariable("id") int breweryId) { // return brewery matching brewery_id
        Brewery brewery = breweryService.getBreweryById(breweryId);
        return brewery;
    }

    @GetMapping("/mybreweries")
    public List<Brewery> viewMySavedBreweries(Principal principal) { //return saved brewery list based on principal
        List<Brewery> savedBreweries = breweryService.getSavedBreweries(principal);
        return savedBreweries;
    }
    @PreAuthorize("hasAnyRole('ROLE_BREWER', 'ROLE_ADMIN')")
    @PostMapping("/breweries")
    public Brewery createBrewery(@RequestBody Brewery breweryTest, Principal principal) {
        Brewery createdBrewery = breweryService.createBrewery(breweryTest, principal);
        return createdBrewery;
    }

    @PostMapping("/mybreweries/{id}")
    public Brewery addSavedBrewery(@PathVariable("id") int breweryId, Principal principal) { // TODO
        Brewery added = breweryService.addBreweryToSaved(breweryId, principal);
        return added;

    }

    @PreAuthorize("hasAnyRole('ROLE_BREWER', 'ROLE_ADMIN')")
    @PutMapping("/breweries/{id}")
    public Brewery updateBrewery(@PathVariable("id") int breweryId, @RequestBody Brewery updatedBrewery, Principal principal) {
        if (breweryId == updatedBrewery.getBreweryId()) {
            Brewery newBrewery = breweryService.updateBrewery(updatedBrewery, principal);
            return newBrewery;

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/mybreweries/{id}") // TODO
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSavedBrewery(@PathVariable("id") int breweryId, Principal principal) {
        breweryService.deleteSavedBrewery(breweryId, principal);
    }
    @PreAuthorize("hasAnyRole('ROLE_BREWER', 'ROLE_ADMIN')")
    @DeleteMapping("/breweries/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrewery(@PathVariable("id") int breweryId, Principal principal) {
        breweryService.deleteBrewery(breweryId, principal);
    }


//    @GetMapping("/breweries/search")
//    public List<Brewery> breweryListSearch(BrewSearchDTO searchTerms) { // receive searchDTO with name, city, state
//        // return breweries by search (name, city, state)
//        // pass SearchDTO to service to call SQL search
//        List<Brewery> filteredBreweries = breweryService.searchBreweries(searchTerms);
//        return filteredBreweries;
//    }
}
