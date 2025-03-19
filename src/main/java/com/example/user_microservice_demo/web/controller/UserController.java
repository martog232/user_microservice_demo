package com.example.user_microservice_demo.web.controller;

import com.example.user_microservice_demo.client.CarServiceApiClient;
import com.example.user_microservice_demo.data.entity.User;
import com.example.user_microservice_demo.service.user.UserService;
import com.example.user_microservice_demo.web.model.CarRespModel;
import com.example.user_microservice_demo.web.model.UserReqModel;
import com.example.user_microservice_demo.web.model.UserSimpleRespModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CarServiceApiClient carServiceApiClient;
    private final RestTemplate restTemplate;

    @GetMapping("/{id}")
    ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id %d not found".formatted(id));
        }
    }

    @GetMapping
    ResponseEntity<List<UserSimpleRespModel>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/by-county")
    ResponseEntity<List<UserSimpleRespModel>> findAllByCountry(@RequestParam String countryTerm) {
        return ResponseEntity.ok(userService.findByCountry(countryTerm));
    }

    @GetMapping("/by-name")
    ResponseEntity<List<UserSimpleRespModel>> findAllByName(@RequestParam String userNameTerm) {
        return ResponseEntity.ok(userService.findByName(userNameTerm));
    }

    @PostMapping
    public ResponseEntity<UserSimpleRespModel> createUser(@RequestBody UserReqModel user) {
        try {
            UserSimpleRespModel createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{userId}/cars")
    public ResponseEntity<List<CarRespModel>> getUserCars(@PathVariable Long userId) {
        return ResponseEntity.ok(carServiceApiClient.getCarsByOwner(userId));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
