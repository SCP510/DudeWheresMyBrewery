package com.techelevator.service;

import com.techelevator.dao.BeerReviewDao;
import com.techelevator.model.BeerReview;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Component
public class BeerReviewServiceImpl implements BeerReviewService {
    private BeerReviewDao beerReviewDao;
    public BeerReviewServiceImpl(BeerReviewDao beerReviewDao) {
        this.beerReviewDao = beerReviewDao;
    }
    @Override
    public List<BeerReview> getAllBeerReviews() {
        List<BeerReview> beers = beerReviewDao.getAllBeerReviews();
        return beers;
    }

    @Override
    public BeerReview getBeerReviewById(int beerReviewId) {
        BeerReview beerReview = beerReviewDao.getBeerReviewById(beerReviewId);
        return beerReview;
    }
    @Override
    public List<BeerReview> getReviewsByBeerId(int beerId) {
        List<BeerReview> beerReview = beerReviewDao.getReviewsByBeerId(beerId);
        return beerReview;
    }

    @Override
    public List<BeerReview> getMyBeerReviews(Principal principal) {
        List<BeerReview> beerReviews = beerReviewDao.getMyBeerReviews(principal);
        return beerReviews;
    }

    @Override
    public BeerReview createBeerReview(BeerReview newBeerReview, Principal principal) {
        BeerReview beerReview = beerReviewDao.createBeerReview(newBeerReview, principal);
        return beerReview;
    }

    @Override
    public BeerReview updateBeerReview(BeerReview updatedBeerReview, Principal principal) {
        BeerReview updatedReview = beerReviewDao.updateBeerReview(updatedBeerReview, principal);
        return updatedReview;
    }

    @Override
    public void deleteBeerReview(int beerReviewId, Principal principal) {
        beerReviewDao.deleteBeerReview(beerReviewId, principal);
    }
}
