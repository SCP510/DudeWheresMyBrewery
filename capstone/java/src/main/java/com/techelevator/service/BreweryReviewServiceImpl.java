package com.techelevator.service;

import com.techelevator.model.BreweryReview;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;
import java.util.List;
@Service
@Component
public class BreweryReviewServiceImpl implements BreweryReviewService {

    @Override
    public BreweryReview getBreweryReviewById(int brewReviewId) {
        return null;
    }

    @Override
    public List<BreweryReview> getMyReviews(Principal principal) {
        return null;
    }

    @Override
    public BreweryReview createBreweryReview(BreweryReview newBrewReview, Principal principal) {
        return null;
    }

    @Override
    public BreweryReview updateBreweryReview(BreweryReview updatedBrewReview, Principal principal) {
        return null;
    }

    @Override
    public void deleteBreweryReview(int brewReviewId, Principal principal) {

    }
}
