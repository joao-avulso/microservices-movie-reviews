package br.com.czerwony.trabalho_sd_review_ms.controllers;

import br.com.czerwony.trabalho_sd_review_ms.dtos.ReviewRecordDto;
import br.com.czerwony.trabalho_sd_review_ms.models.ReviewModel;
import br.com.czerwony.trabalho_sd_review_ms.repository.ReviewRepository;
import br.com.czerwony.trabalho_sd_review_ms.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ReviewController {

    final ReviewService reviewService;
    final ReviewRepository reviewRepository;

    public ReviewController(ReviewService reviewService, ReviewRepository reviewRepository) {
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
    }

    @PostMapping("/reviews")
    public ResponseEntity<Object> postReview(@RequestBody @Valid ReviewRecordDto reviewRecordDto) {
        try {
            var reviewModel = new ReviewModel();
            BeanUtils.copyProperties(reviewRecordDto, reviewModel);
            reviewModel.setCreatedAt(LocalDateTime.now());
            reviewService.save(reviewModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(reviewModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/reviews")
    public ResponseEntity<Object> getReviews() {
        return ResponseEntity.status(HttpStatus.OK).body(reviewRepository.findAll());
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Object> getReviewById(@PathVariable(value = "id") UUID id) {
        Optional<ReviewModel> reviewModel = reviewRepository.findById(id);

        if (reviewModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(reviewModel);
    }

    @GetMapping("/reviews/user/{userId}")
    public ResponseEntity<Object> getReviewByUserId(@PathVariable(value = "userId") UUID id) {
        ArrayList<ReviewModel> reviewModels = reviewRepository.findByUserId(id);

        if (reviewModels.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(reviewModels);
    }

    @GetMapping("/reviews/movie/{movieTitle}")
    public ResponseEntity<Object> getReviewByMovieTitle(@PathVariable(value = "movieTitle") String movieTitle) {
        ArrayList<ReviewModel> reviewModels = reviewRepository.findByMovieTitle(movieTitle);

        if (reviewModels.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(reviewModels);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable(value = "id") UUID id) {
        Optional<ReviewModel> reviewModel = reviewRepository.findById(id);

        if (reviewModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
        }

        reviewRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(reviewModel);
    }

}
