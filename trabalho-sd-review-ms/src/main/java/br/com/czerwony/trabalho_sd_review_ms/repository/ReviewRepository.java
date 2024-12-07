package br.com.czerwony.trabalho_sd_review_ms.repository;

import br.com.czerwony.trabalho_sd_review_ms.models.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewModel, UUID> {

    ArrayList<ReviewModel> findByUserId(UUID userId);

    ArrayList<ReviewModel> findByMovieTitle(String movieTitle);
}
