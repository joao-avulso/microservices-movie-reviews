package br.com.czerwony.trabalho_sd_review_ms.services;

import br.com.czerwony.trabalho_sd_review_ms.producers.ReviewProducer;
import jakarta.transaction.Transactional;
import br.com.czerwony.trabalho_sd_review_ms.models.ReviewModel;
import org.springframework.stereotype.Service;
import br.com.czerwony.trabalho_sd_review_ms.repository.ReviewRepository;

import java.time.LocalDateTime;

@Service
public class ReviewService {

    final ReviewRepository reviewRepository;
    final ReviewProducer reviewProducer;

    public ReviewService(ReviewRepository reviewRepository, ReviewProducer reviewProducer) {
        this.reviewRepository = reviewRepository;
        this.reviewProducer = reviewProducer;
    }

    @Transactional
    public ReviewModel save(ReviewModel review) {
        review.setCreatedAt(LocalDateTime.now());

        if (review.getStars() < 0) review.setStars(0);
        else if (review.getStars() > 5) review.setStars(5);

        reviewProducer.publishReviewEmail(review);
        return reviewRepository.save(review);
    }
}
