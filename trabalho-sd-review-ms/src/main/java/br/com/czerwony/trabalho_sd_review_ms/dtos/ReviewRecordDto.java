package br.com.czerwony.trabalho_sd_review_ms.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record ReviewRecordDto(UUID userId,
                              @NotBlank @Email String userEmail,
                              @NotBlank String movieTitle,
                              @PositiveOrZero Integer stars,
                              String content) {
}
