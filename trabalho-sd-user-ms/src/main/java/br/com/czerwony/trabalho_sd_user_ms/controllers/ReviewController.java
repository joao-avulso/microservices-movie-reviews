package br.com.czerwony.trabalho_sd_user_ms.controllers;

import br.com.czerwony.trabalho_sd_user_ms.dtos.ReviewDto;
import br.com.czerwony.trabalho_sd_user_ms.dtos.ReviewRecordDto;
import br.com.czerwony.trabalho_sd_user_ms.producers.ReviewProducer;
import br.com.czerwony.trabalho_sd_user_ms.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ReviewController {

    final ReviewProducer reviewProducer;
    final UserRepository userRepository;

    public ReviewController(ReviewProducer reviewProducer, UserRepository userRepository) {
        this.reviewProducer = reviewProducer;
        this.userRepository = userRepository;
    }

    @PostMapping("/review")
    public ResponseEntity<Object> postReview(@RequestBody @Valid ReviewRecordDto reviewRecordDto) {

        if (userRepository.findByEmail(reviewRecordDto.userEmail()) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        var reviewDto = new ReviewDto();
        reviewDto.setUserId(userRepository.findByEmail(reviewRecordDto.userEmail()).getId());
        reviewDto.setUserEmail(reviewRecordDto.userEmail());
        reviewDto.setMovieTitle(reviewRecordDto.movieTitle());
        reviewDto.setStars(reviewRecordDto.stars());
        reviewDto.setContent(reviewRecordDto.content());

        reviewProducer.sendReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review sent");
    }
}
