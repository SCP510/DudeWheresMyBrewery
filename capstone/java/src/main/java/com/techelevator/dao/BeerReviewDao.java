package com.techelevator.dao;

import com.techelevator.model.BeerReview;

import java.security.Principal;
import java.util.List;

public interface BeerReviewDao {
    List<BeerReview> getAllBeerReviews();
    BeerReview getBeerReviewById (int beerReviewId);
    List<BeerReview> getReviewsByBeerId(int beerId);
    List<BeerReview> getMyBeerReviews(Principal principal);
    BeerReview createBeerReview(BeerReview newBeerReview, Principal principal);
    BeerReview updateBeerReview(BeerReview beerReview, Principal principal);
    void deleteBeerReview(int beerReviewId, Principal principal);
}
