package com.techelevator.service;

import com.techelevator.dao.BeerDao;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Beer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Component
public class BeerServiceImpl implements BeerService{

    // Instance Variables/Properties
    private BeerDao beerDao;



    // Constructors
    public BeerServiceImpl(BeerDao beerDao) {
        this.beerDao = beerDao;
    }



    // Methods
    public List<Beer> getBeers() {
        try {
            List<Beer> beers = beerDao.getBeers();
            return beers;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public Beer getRandomBeer() {
        try {
            Beer beer = beerDao.getRandomBeer();
            return beer;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public Beer getBeerById(int beerId) {
        try {
            Beer beer = beerDao.getBeerById(beerId);
            return beer;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public List<Beer> getBeersByBreweryId(int breweryId) {
        try {
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
        List<Beer> beers = beerDao.getBeersByBreweryId(breweryId);
        return beers;
    }

    @Override
    public List<Beer> getSavedBeers(Principal principal) {
        try {
            return beerDao.getSavedBeers(principal);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public Beer addBeerToSaved(int beerId, Principal principal) {
        try {
            Beer beer = beerDao.addBeerToSaved(beerId, principal);
            return beer;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public Beer createBeer(Beer beer, Principal principal) {
        try {
            Beer createdBeer = beerDao.createBeer(beer, principal);
            return createdBeer;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public Beer updateBeer(Beer beer, Principal principal) {
        try {
            Beer updatedBeer = beerDao.updateBeer(beer, principal);
            return updatedBeer;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public void deleteSavedBeer(int beerdId, Principal principal) {
        try {
            beerDao.deleteSavedBeer(beerdId, principal);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public void deleteBeer(int beerId, Principal principal) {
        try {
            beerDao.deleteBeer(beerId, principal);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
}
