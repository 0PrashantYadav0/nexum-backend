package com.pixels.Nexum.controller;


import com.pixels.Nexum.model.Review;
import com.pixels.Nexum.service.ReviewService;
import com.pixels.Nexum.utils.APIReturnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

@RestController
@CrossOrigin
@RequestMapping("/api/review/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    private APIReturnModel apiReturnModel;

    private Vector<Review> reviewVector;

    @PostMapping("addReview")
    public ResponseEntity<?> addReview(@RequestBody Review review) {
        apiReturnModel = new APIReturnModel();
        reviewVector = new Vector<>();

        try {
            Review newReview = new Review();
            newReview.setUserId(review.getUserId());
            newReview.setComment(review.getComment());
            newReview.setRating(review.getRating());
            newReview.setWorkerId(review.getWorkerId());
            reviewVector.add(newReview);
            this.reviewService.createReview(newReview);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setData(reviewVector);
            apiReturnModel.setMessage("Review Added Successfully !");
            apiReturnModel.setCount(1);
        } catch (Exception e) {
            e.printStackTrace();
            apiReturnModel.setStatus("fail");
            apiReturnModel.setStatusCode(404);
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);
            apiReturnModel.setData(null);
        }

        return ResponseEntity.ok(apiReturnModel);
    }

    @GetMapping("getReviewByUserId/{userId}")
    public ResponseEntity<?> getReviewByUserId(@PathVariable("userId") int userId){
        apiReturnModel = new APIReturnModel();
        reviewVector = new Vector<>();

        try {
            List<Review> review = this.reviewService.getAllReviewByUserId(userId);
            reviewVector.addAll(review);
            apiReturnModel.setData(reviewVector);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setMessage("Review Found Successfully !");
        } catch (Exception e) {
            e.printStackTrace();
            apiReturnModel.setStatus("fail");
            apiReturnModel.setStatusCode(404);
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);
        }

        return ResponseEntity.ok(apiReturnModel);
    }

    @GetMapping("getReviewByWorkerId/{workerId}")
    public ResponseEntity<?> getReviewByWorkerId(@PathVariable("workerId") int workerId){
        apiReturnModel = new APIReturnModel();
        reviewVector = new Vector<>();

        try {
            List<Review> review = this.reviewService.getReviewByWorkerId(workerId);
            reviewVector.addAll(review);
            apiReturnModel.setData(reviewVector);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setMessage("Review Found Successfully !");
        } catch (Exception e) {
            e.printStackTrace();
            apiReturnModel.setStatus("fail");
            apiReturnModel.setStatusCode(404);
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);
        }

        return ResponseEntity.ok(apiReturnModel);
    }


}
