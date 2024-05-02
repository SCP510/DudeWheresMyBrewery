package com.techelevator.controller;

import com.techelevator.model.Beer;
import com.techelevator.service.BeerService;
import com.techelevator.service.BeerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;



@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class BeerController {
    private BeerService beerService;

    public BeerController(BeerServiceImpl beerService) { // constructor
        this.beerService = beerService;
    }
    @GetMapping("/beers")
    public List<Beer> getBeers() {
        List<Beer> beers = beerService.getBeers();
        if (beers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.OK, "No beers found.");
        }
        return beers;
    }
    @GetMapping("/beers/random")
    public Beer getRandomBeer() {
        Beer beer = beerService.getRandomBeer();
        if (beer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No beers found.");
        }
        return beer;
    }

    @GetMapping("/beers/{id}")
    public Beer viewBeerByID(@PathVariable("id") int beerId) { // 200 on success, 404 if no beer exists
        // return beer POJO
        Beer beer = beerService.getBeerById(beerId);
        if (beer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "Beer not found.");
        }
        return beer;
    }
    @GetMapping("/brewery/beers/{id}")
    public List<Beer> viewBeersByBrewery (@PathVariable("id") int breweryId) { // 200 on success, 204 if empty
        List<Beer> beers = beerService.getBeersByBreweryId(breweryId);
        return beers;
    }
    @GetMapping("/mybeers")
    public List<Beer> viewMySavedBeers(Principal principal) { //200 and list on success, 204 if list empty
        List<Beer> myBeers = beerService.getSavedBeers(principal);
        return myBeers;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/mybeers/{id}")
    public Beer addSavedBeer(@PathVariable("id") int beerId, Principal principal) { // 201 on success, 500 if no beer exists or duplicate entry
        Beer added = beerService.addBeerToSaved(beerId, principal);
        return added;
    }

    @PreAuthorize("hasAnyRole('ROLE_BREWER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/beers")
    public Beer createBeer(@RequestBody Beer beer, Principal principal) { // 201 on success, 500 if duplicate or failed
        Beer newBeer = beerService.createBeer(beer, principal);
        return newBeer;
    }
    @PreAuthorize("hasAnyRole('ROLE_BREWER', 'ROLE_ADMIN')")
    @PutMapping("/beers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Beer updateBeer(@PathVariable("id") int beerId, @RequestBody Beer beer, Principal principal) {
        if (beerId == beer.getBeerId()) {
            Beer updatedBeer = beerService.updateBeer(beer, principal);
            return updatedBeer;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/mybeers/{id}")// 500 on failed delete, 204 on success
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSavedBeer(@PathVariable("id") int beerId, Principal principal) {
        beerService.deleteSavedBeer(beerId, principal);
    }

    @PreAuthorize("hasAnyRole('ROLE_BREWER', 'ROLE_ADMIN')") //authorize but check founderId in SQL
    @DeleteMapping("/beers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("id") int beerID, Principal principal) {
        beerService.deleteBeer(beerID, principal);
    }

}

