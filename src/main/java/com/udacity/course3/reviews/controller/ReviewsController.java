package com.udacity.course3.reviews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.exception.IdNotFoundException;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

import java.util.List;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {
	
	@Autowired
	ReviewRepository reviewRepo;
	
	@Autowired
	ProductRepository prodRepo;

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId, @RequestBody Review review) {
    	if(prodRepo.existsById(productId))
    	{
    		return new ResponseEntity<Review>(reviewRepo.save(review), HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<String>("The given product ID does not exist!", HttpStatus.NOT_FOUND);
    	}
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
    	if(prodRepo.existsById(productId))
    	{
    		return new ResponseEntity<List<?>>(reviewRepo.findByProductId(productId), HttpStatus.OK);
    	}
    	throw new IdNotFoundException();
        
    }
}