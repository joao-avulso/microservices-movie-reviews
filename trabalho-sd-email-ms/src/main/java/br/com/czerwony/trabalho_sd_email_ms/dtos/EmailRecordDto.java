package br.com.czerwony.trabalho_sd_email_ms.dtos;

import java.util.UUID;

public record EmailRecordDto(UUID id,
                             String emailPara,
                             String assunto,
                             String texto) {
}
