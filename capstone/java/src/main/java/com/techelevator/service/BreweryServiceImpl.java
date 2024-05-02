package com.techelevator.service;

import com.techelevator.dao.BreweryDao;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Beer;
import com.techelevator.model.BrewSearchDTO;
import com.techelevator.model.Brewery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Component
public class BreweryServiceImpl implements BreweryService {

    // Instance Variables/Properties
    private BreweryDao breweryDao;



    // Constructors
    public BreweryServiceImpl(BreweryDao breweryDao) {
        this.breweryDao = breweryDao;
    }



    // Methods
    @Override
    public Brewery getBreweryById(int breweryId) {
        try {
            Brewery brewery = breweryDao.getBreweryById(breweryId);
            return brewery;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public Brewery getRandomBrewery() {
        try {
            Brewery brewery = breweryDao.getRandomBrewery();
            return brewery;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public List<Brewery> getAllBreweries() {

        return breweryDao.getAllBreweries();
    }

    @Override
    public List<Brewery> getSavedBreweries(Principal principal) {
        try {
            List<Brewery> breweries = breweryDao.getSavedBreweries(principal);
            return breweries;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    public Brewery addBreweryToSaved(int breweryId, Principal principal) {
        // TODO add brewery to saved
        try {
            Brewery brewery = breweryDao.addBreweryToSaved(breweryId, principal);
            return brewery;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public Brewery createBrewery(Brewery newBrewery, Principal principal) {
        try {
            Brewery brewery = breweryDao.createBrewery(newBrewery, principal);
            return brewery;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public Brewery updateBrewery(Brewery updatedBrewery, Principal principal) {
        try {
            Brewery brewery = breweryDao.updateBreweryInfo(updatedBrewery, principal);
            return brewery;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public void deleteSavedBrewery(int breweryId, Principal principal) {
        try {
            breweryDao.deleteSavedBrewery(breweryId, principal);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public void deleteBrewery(int breweryId, Principal principal) {
        try {
            breweryDao.deleteBrewery(breweryId, principal);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }


//    @Override
//    public List<Brewery> searchBreweries(BrewSearchDTO searchTerms) {
//        return null;
//    }
}
