package br.com.czerwony.trabalho_sd_user_ms.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String primeiroNome,
                            @NotBlank String segundoNome,
                            @NotBlank @Email String email) {

}
