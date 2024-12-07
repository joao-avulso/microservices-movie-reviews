package br.com.czerwony.trabalho_sd_email_ms.repositories;

import br.com.czerwony.trabalho_sd_email_ms.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
