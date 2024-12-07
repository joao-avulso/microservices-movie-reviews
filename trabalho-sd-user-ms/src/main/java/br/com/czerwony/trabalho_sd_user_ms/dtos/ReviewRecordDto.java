package br.com.czerwony.trabalho_sd_user_ms.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ReviewRecordDto(@NotBlank @Email String userEmail,
                              @NotBlank String movieTitle,
                              @PositiveOrZero Integer stars,
                              String content) {
}
