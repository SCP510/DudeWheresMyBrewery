package com.techelevator.service;

import com.techelevator.model.BreweryReview;

import java.security.Principal;
import java.util.List;

public interface BreweryReviewService {
    BreweryReview getBreweryReviewById(int brewReviewId);
    List<BreweryReview> getMyReviews(Principal principal);
    BreweryReview createBreweryReview(BreweryReview newBrewReview, Principal principal);
    BreweryReview updateBreweryReview(BreweryReview updatedBrewReview, Principal principal);
    void deleteBreweryReview(int brewReviewId, Principal principal);
}
