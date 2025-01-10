package br.com.czerwony.trabalho_sd_user_ms.controllers;

import br.com.czerwony.trabalho_sd_user_ms.dtos.UserRecordDto;
import br.com.czerwony.trabalho_sd_user_ms.models.UserModel;
import br.com.czerwony.trabalho_sd_user_ms.repositories.UserRepository;
import br.com.czerwony.trabalho_sd_user_ms.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    final UserService userService;
    final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        var response = userService.save(userModel);

        if (response == HttpStatus.CREATED) {
            return ResponseEntity.status(response).body(userModel);
        } else if (response == HttpStatus.CONFLICT) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> userModel = userRepository.findById(id);

        if (userModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> userModel = userRepository.findById(id);

        if (userModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        userRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
}
