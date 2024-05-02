package com.techelevator.service;

import com.techelevator.model.BeerReview;

import java.security.Principal;
import java.util.List;

public interface BeerReviewService {
    List<BeerReview> getAllBeerReviews();
    public BeerReview getBeerReviewById(int beerReviewId);
    List<BeerReview> getReviewsByBeerId(int beerId);
    public List<BeerReview> getMyBeerReviews(Principal principal);
    public BeerReview createBeerReview(BeerReview newBeerReview, Principal principal);
    public BeerReview updateBeerReview(BeerReview updatedBeerReview, Principal principal);
    public void deleteBeerReview(int beerReviewId, Principal principal);
}
