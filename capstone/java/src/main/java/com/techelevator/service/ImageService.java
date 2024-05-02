package com.techelevator.service;

import com.techelevator.model.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ImageService {
    ResponseEntity<byte[]> getBeerImage(int beerId);
    ResponseEntity<byte[]> getBreweryImage(int breweryId);
}
