package br.com.czerwony.trabalho_sd_user_ms.repositories;

import br.com.czerwony.trabalho_sd_user_ms.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    UserModel findByEmail(String email);
}
