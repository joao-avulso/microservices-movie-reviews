package br.com.czerwony.trabalho_sd_user_ms.services;

import br.com.czerwony.trabalho_sd_user_ms.models.UserModel;
import br.com.czerwony.trabalho_sd_user_ms.producers.UserProducer;
import br.com.czerwony.trabalho_sd_user_ms.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public HttpStatus save(UserModel userModel) {
        try {
            if (userRepository.findByEmail(userModel.getEmail()) != null) {
                return HttpStatus.CONFLICT;
            }
            userModel = userRepository.save(userModel);
            userProducer.publishMessageEmail(userModel);
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.CREATED;
    }
}
