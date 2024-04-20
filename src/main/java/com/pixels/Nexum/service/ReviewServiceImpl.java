package com.pixels.Nexum.service;

import com.pixels.Nexum.model.Review;
import com.pixels.Nexum.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review reviewModel) {
        Review review = new Review();
        review.setComment(reviewModel.getComment());
        review.setRating(reviewModel.getRating());
        review.setUserId(reviewModel.getUserId());
        return this.reviewRepository.save(reviewModel);
    }

    @Override
    public List<Review> getAllReviewByUserId(Integer userId) {
        //find all review with given userId
        List<Review> reviews = this.reviewRepository.findAll();
        List<Review> outputReviews = new ArrayList<>();
        for(Review review : reviews){
            if(review.getUserId() == userId){
                outputReviews.add(review);
            }
        }
        return outputReviews;
    }

    @Override
    public List<Review> getReviewByWorkerId(Integer workerId){
        List<Review> reviews = this.reviewRepository.findAll();
        List<Review> outputReviews = new ArrayList<>();
        for(Review review : reviews){
            if(review.getWorkerId() == workerId){
                outputReviews.add(review);
            }
        }
        return outputReviews;
    }
}
